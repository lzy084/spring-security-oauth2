package com.dd.cloud.gateway.limiter;


import com.dd.cloud.core.common.limter.RateLimiterLevel;

public interface LimiterLevelResolver {
    default void save(RateLimiterLevel limiterLevel){}

    default RateLimiterLevel get(){
        return null;
    }
}
