//package com.dd.cloud.gateway.config;
//
//import cn.hutool.core.collection.CollUtil;
//import lombok.AllArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import reactor.core.publisher.Mono;
//
//import java.util.List;
//
//@Slf4j
//@Configuration
//@AllArgsConstructor
//public class RateLimiterConfiguration {
//    @Bean
//    public KeyResolver principalNameKeyResolver(){
//        return exchange -> {
//            List<String> authorization = exchange.getRequest().getHeaders().get(CommonConstants.AUTHORIZATION);
//            if(CollUtil.isNotEmpty(authorization)){
//                String token = authorization.get(0);
//                token = token.substring(token.indexOf(CommonConstants.PREFIX) + 1,token.length());
//                String key = SecurityConstants.MS_OAUTH_PREFIX + CommonConstants.AUTH_USER + token;
//                byte[] principal = redisTemplate.getConnectionFactory().getConnection().get(redisTokenStoreSerializationStrategy.serialize(key));
//                if(principal != null){
//                    StoreUser principalStr = redisTokenStoreSerializationStrategy.deserialize(principal,StoreUser.class);
//                    return Mono.just(principalStr.getLimitLevel() == 0 ? CommonConstants.DEFAULT_LEVEL : String.valueOf(principalStr.getLimitLevel()));
//                }
//            }
//            return Mono.just(CommonConstants.DEFAULT_LEVEL);
//        };
//    }
//}
