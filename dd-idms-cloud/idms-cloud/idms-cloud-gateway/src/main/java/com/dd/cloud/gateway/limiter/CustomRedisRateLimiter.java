package com.dd.cloud.gateway.limiter;

import com.dd.cloud.core.common.entity.RateLimiter;
import com.dd.cloud.core.common.limter.RateLimiterLevel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.ratelimit.AbstractRateLimiter;
import org.springframework.cloud.gateway.support.ConfigurationService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.constraints.Min;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Slf4j
public class CustomRedisRateLimiter extends AbstractRateLimiter<CustomRedisRateLimiter.Config> implements ApplicationContextAware {


    @Autowired
    private LimiterLevelResolver limiterLevelResolver;
    /**
     * Redis Rate Limiter property name.
     */
    public static final String CONFIGURATION_PROPERTY_NAME = "redis-rate-limiter";

    /**
     * Redis Script name.
     */
    public static final String REDIS_SCRIPT_NAME = "redisRequestRateLimiterScript";

    /**
     * Remaining Rate Limit header name.
     */
    public static final String REMAINING_HEADER = "X-RateLimit-Remaining";

    /**
     * Replenish Rate Limit header name.
     */
    public static final String REPLENISH_RATE_HEADER = "X-RateLimit-Replenish-Rate";

    /**
     * Burst Capacity header name.
     */
    public static final String BURST_CAPACITY_HEADER = "X-RateLimit-Burst-Capacity";

    /**
     * Requested Tokens header name.
     */
    public static final String REQUESTED_TOKENS_HEADER = "X-RateLimit-Requested-Tokens";

    private ReactiveStringRedisTemplate redisTemplate;

    private RedisScript<List<Long>> script;

    private AtomicBoolean initialized = new AtomicBoolean(false);

    private Config defaultConfig;

    // configuration properties
    /**
     * Whether or not to include headers containing rate limiter information, defaults to
     * true.
     */
    private boolean includeHeaders = true;

    /**
     * The name of the header that returns number of remaining requests during the current
     * second.
     */
    private String remainingHeader = REMAINING_HEADER;

    /**
     * The name of the header that returns the replenish rate configuration.
     */
    private String replenishRateHeader = REPLENISH_RATE_HEADER;

    /**
     * The name of the header that returns the burst capacity configuration.
     */
    private String burstCapacityHeader = BURST_CAPACITY_HEADER;

    /**
     * The name of the header that returns the requested tokens configuration.
     */
    private String requestedTokensHeader = REQUESTED_TOKENS_HEADER;


    public CustomRedisRateLimiter(ReactiveStringRedisTemplate redisTemplate,
                                  RedisScript<List<Long>> script, ConfigurationService configurationService) {
        super(CustomRedisRateLimiter.Config.class, CONFIGURATION_PROPERTY_NAME, configurationService);
        this.redisTemplate = redisTemplate;
        this.script = script;
        this.initialized.compareAndSet(false, true);
    }

    public CustomRedisRateLimiter(int defaultReplenishRate, int defaultBurstCapacity) {
        super(CustomRedisRateLimiter.Config.class, CONFIGURATION_PROPERTY_NAME, (ConfigurationService) null);
        this.defaultConfig = new Config()
                .setBurstCapacity(defaultBurstCapacity)
                .setReplenishRate(defaultReplenishRate);

    }

    @Override
    public Mono<Response> isAllowed(String routeId, String id) {
        if (!this.initialized.get()) {
            throw new IllegalStateException("RedisRateLimiter is not initialized");
        }
        if (ObjectUtils.isEmpty(limiterLevelResolver)) {
            throw new IllegalArgumentException("No Configuration found for route " + routeId);
        }
        RateLimiterLevel rateLimiterLevel = limiterLevelResolver.get();
        // 允许每秒执行多少
        int replenishRate = rateLimiterLevel
                .getLevels()
                .stream()
                .filter(rateLimiter -> rateLimiter.getLevel().equals(id))
                .findFirst()
                .map(RateLimiter::getReplenishRate)
                .orElse(CommonConstants.DEFAULT_LIMIT_LEVEL);
       // 允许多大的并发量
        int burstCapacity = rateLimiterLevel
                .getLevels()
                .stream()
                .filter(rateLimiter -> rateLimiter.getLevel().equals(id))
                .findFirst()
                .map(RateLimiter::getBurstCapacity)
                .orElse(CommonConstants.DEFAULT_LIMIT_LEVEL);

        try {
            List<String> keys = getKeys(id);
            long limitTime = getTime(rateLimiterLevel
                    .getLevels()
                    .stream()
                    .filter(rateLimiterVO -> rateLimiterVO.getLevel().equals(id))
                    .findFirst()
                    .map(RateLimiter::getLimitType)
                    .orElse(CommonConstants.DEFAULT_LIMIT_TYPE));
            List<String> scriptArgs = Arrays.asList(replenishRate + "", burstCapacity + "",limitTime + "", "1");
            Flux<List<Long>> flux = this.redisTemplate.execute(this.script, keys, scriptArgs);

            return flux.onErrorResume(throwable -> Flux.just(Arrays.asList(1L, -1L)))
                    .reduce(new ArrayList<Long>(), (longs, l) -> {
                        longs.addAll(l);
                        return longs;
                    }) .map(results -> {
                        boolean allowed = results.get(0) == 1L;
                        Long tokensLeft = results.get(1);
                        Response response = new Response(allowed, getHeaders(replenishRate , burstCapacity , tokensLeft));
                        return response;
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Mono.just(new Response(true, getHeaders(replenishRate , burstCapacity , -1L)));
    }

    public HashMap<String, String> getHeaders(Integer replenishRate, Integer burstCapacity , Long tokensLeft) {
        HashMap<String, String> headers = new HashMap<>();
        headers.put(this.remainingHeader, tokensLeft.toString());
        headers.put(this.replenishRateHeader, String.valueOf(replenishRate));
        headers.put(this.burstCapacityHeader, String.valueOf(burstCapacity));
        return headers;
    }
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (initialized.compareAndSet(false, true)) {
            this.redisTemplate = applicationContext.getBean("stringReactiveRedisTemplate",ReactiveStringRedisTemplate.class);
            this.script = applicationContext.getBean(REDIS_SCRIPT_NAME, RedisScript.class);
        }
    }
    /**
     * @Param [type] 1:秒，2:分钟，3:小时，4:天
     * @return long
     **/
    public long getTime(int type){
        long time = Instant.now().getEpochSecond();
        switch (type){
            case 1:
                break;
            case 2:
                time = time / (1000 * 60);
                break;
            case 3:
                time = time / (1000 * 60 * 60);
                break;
            case 4:
                time = time / (1000 * 60 * 60 * 24);
                break;
        }
        return time;
    }
    static List<String> getKeys(String id) {
        // use `{}` around keys to use Redis Key hash tags
        // this allows for using redis cluster

        // Make a unique key per user.
        String prefix = "request_user_rate_limiter.{" + id;

        // You need two Redis keys for Token Bucket.
        String tokenKey = prefix + "}.tokens";
        String timestampKey = prefix + "}.timestamp";
        return Arrays.asList(tokenKey, timestampKey);
    }

    @Validated
    public static class Config {
        @Min(1)
        private int replenishRate;
        @Min(1)
        private int burstCapacity = 1;

        public int getReplenishRate() {
            return replenishRate;
        }

        public Config setReplenishRate(int replenishRate) {
            this.replenishRate = replenishRate;
            return this;
        }

        public int getBurstCapacity() {
            return burstCapacity;
        }

        public Config setBurstCapacity(int burstCapacity) {
            this.burstCapacity = burstCapacity;
            return this;
        }

        @Override
        public String toString() {
            return "Config{" +
                    "replenishRate=" + replenishRate +
                    ", burstCapacity=" + burstCapacity +
                    '}';
        }
    }
}
