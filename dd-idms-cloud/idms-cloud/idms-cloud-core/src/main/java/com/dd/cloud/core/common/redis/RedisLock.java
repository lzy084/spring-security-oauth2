package com.dd.cloud.core.common.redis;

import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.connection.ReturnType;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.types.Expiration;

import java.nio.charset.Charset;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class RedisLock  implements AutoCloseable{

    private StringRedisTemplate stringRedisTemplate;
    private String key;
    private String  value;
    /* 过期时间，单位秒 */
    private int expireTime;

    public  RedisLock(StringRedisTemplate stringRedisTemplate,String key,int expireTime){
        this.stringRedisTemplate=stringRedisTemplate;
        this.key=key;
        this.value= UUID.randomUUID().toString();
        this.expireTime=expireTime;
    }
    public Boolean getLock(){
        Boolean lockStat = stringRedisTemplate.execute((RedisCallback<Boolean>) connection ->
                connection.set(key.getBytes(Charset.forName("UTF-8")), value.getBytes(Charset.forName("UTF-8")),
                        Expiration.from(expireTime, TimeUnit.SECONDS), RedisStringCommands.SetOption.SET_IF_ABSENT));
        return lockStat;
    }
    public Boolean unLock(){
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        boolean unLockStat = stringRedisTemplate.execute((RedisCallback<Boolean>)connection ->
                connection.eval(script.getBytes(), ReturnType.BOOLEAN, 1,
                        key.getBytes(Charset.forName("UTF-8")), value.getBytes(Charset.forName("UTF-8"))));
        return unLockStat;
    }

    @Override
    public void close() throws Exception {
        unLock();
    }
}
