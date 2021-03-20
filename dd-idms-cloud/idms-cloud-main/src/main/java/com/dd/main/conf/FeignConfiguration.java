package com.dd.main.conf;


import com.alibaba.fastjson.JSONObject;
import com.dd.cloud.core.common.entity.TokenEntity;
import com.dd.cloud.core.common.utilities.RedisCacheUtilities;
import com.dd.cloud.core.common.utilities.RedisUtil;
import com.dd.cloud.security.model.entity.SecurityUserInfo;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;

@Configuration
@Slf4j
public class FeignConfiguration implements RequestInterceptor {
    @Value("${token_url}")
    String tokenUrl;
    @Value("${client_id}")
    String clientId;
    @Value("${client_secret}")
    String clientSecret;
    @Value("${scope}")
    String scope;
    @Value("${grant_type}")
    String grantType;
    @Resource
    RestTemplate template;
    @Resource
    RedisCacheUtilities redisCacheUtilities;
    private static final String AUTHORIZATION_HEADER = "Authorization";

    @Override
    public void apply(RequestTemplate requestTemplate) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        TokenEntity token = getToken();
        requestTemplate.header(AUTHORIZATION_HEADER,
                String.format("%s %s",
                        token.getTokenType(),
                        token.getAccessToken()));
    }

    private TokenEntity getToken() throws RestClientException {
        SecurityUserInfo userInfo = (SecurityUserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userInfo.getAccountName();
        String tokenKey=userInfo.getAccountId().toString()+"::token";
        if(redisCacheUtilities.hasKey(tokenKey)){
            return (TokenEntity) redisCacheUtilities.get(tokenKey);
        }
        else{
            String password =redisCacheUtilities.get(username).toString();
            String url=tokenUrl+"?"+"client_id="+clientId+"&client_secret="+clientSecret+"&grant_type="+grantType+"&scope="+scope+"&username="+username+"&password="+password;
            ResponseEntity<String> responseEntity = template.getForEntity(url, String.class);
            String body = responseEntity.getBody();
            TokenEntity tokenEntity= JSONObject.parseObject(body, TokenEntity.class);
            redisCacheUtilities.set(tokenKey,tokenEntity,30*55);
            return tokenEntity;
        }
    }
}
