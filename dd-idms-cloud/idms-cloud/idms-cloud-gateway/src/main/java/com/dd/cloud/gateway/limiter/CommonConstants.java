package com.dd.cloud.gateway.limiter;

public interface CommonConstants {
    /**
     * 默认限流等级
     */
    String DEFAULT_LEVEL = "1";

    /**
     * 默认限流 流速 与 桶大小
     */
    int DEFAULT_LIMIT_LEVEL = 10;

    /**
     * 默认单位 1:秒，2:分钟，3:小时，4:天
     */
    int DEFAULT_LIMIT_TYPE = 1;

    /**
     * 前缀
     */
    String PREFIX = " ";

    /**
     * 限流缓存key
     */
    String REDIS_LIMIT_KEY = "cloud_redis_limit_key:";
}
