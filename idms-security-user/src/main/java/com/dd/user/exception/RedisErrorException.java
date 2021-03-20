package com.dd.user.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cache.Cache;
import org.springframework.cache.interceptor.CacheErrorHandler;

public class RedisErrorException extends Throwable implements CacheErrorHandler {
    Logger logger = LogManager.getLogger(RedisErrorException.class);

    @Override
    public void handleCacheGetError(RuntimeException exception, Cache cache, Object key) {
        logger.error("在缓存 {} 获取key :{} 出现异常 :{}",
                cache.getName(), key, exception.getMessage());
    }

    @Override
    public void handleCachePutError(RuntimeException exception, Cache cache, Object key,
                                    Object value) {
        logger.error("在缓存 {} 写入 :{} 发生异常 :{}",
                cache.getName(), key, exception.getMessage());
    }

    @Override
    public void handleCacheEvictError(RuntimeException exception, Cache cache,
                                      Object key) {
        logger.error("清除缓存 {} :{} 发生异常 :{}",
                cache.getName(), key, exception.getMessage());
    }

    @Override
    public void handleCacheClearError(RuntimeException exception, Cache cache) {
        logger.error("清理缓存 {} 出现错误 :{}", cache.getName(),
                exception.getMessage());
    }
}
