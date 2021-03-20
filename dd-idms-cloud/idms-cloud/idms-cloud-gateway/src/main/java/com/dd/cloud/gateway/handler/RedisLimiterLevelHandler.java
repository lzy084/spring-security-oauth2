package com.dd.cloud.gateway.handler;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.dd.cloud.core.common.entity.RateLimiter;
import com.dd.cloud.core.common.limter.RateLimiterLevel;
import com.dd.cloud.core.common.utilities.RedisCacheUtilities;
import com.dd.cloud.gateway.limiter.CommonConstants;
import com.dd.cloud.gateway.limiter.LimiterLevelResolver;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class RedisLimiterLevelHandler implements LimiterLevelResolver {
    @Autowired
    RedisCacheUtilities redisCacheUtilities;

    @Override
    public void save(RateLimiterLevel limiterLevel) {
        redisCacheUtilities.set(CommonConstants.REDIS_LIMIT_KEY, limiterLevel);

    }
    @Override
    public RateLimiterLevel get() {
        RateLimiterLevel rateLimiterLevel = (RateLimiterLevel) redisCacheUtilities.get(CommonConstants.REDIS_LIMIT_KEY);
        if (ObjectUtil.isNull(rateLimiterLevel) || CollUtil.isEmpty(rateLimiterLevel.getLevels())) {
            rateLimiterLevel = new RateLimiterLevel();
            List<RateLimiter> vos = new ArrayList<>();
            vos.add(RateLimiter
                    .builder()
                    .level(CommonConstants.DEFAULT_LEVEL)
                    .burstCapacity(CommonConstants.DEFAULT_LIMIT_LEVEL)
                    .replenishRate(CommonConstants.DEFAULT_LIMIT_LEVEL)
                    .limitType(CommonConstants.DEFAULT_LIMIT_TYPE)
                    .build());
            rateLimiterLevel.setLevels(vos);
        }
        return rateLimiterLevel;
    }
}
