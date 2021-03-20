package com.dd.cloud.core.common.utilities;

import com.alibaba.fastjson.JSONObject;
import com.dd.cloud.core.common.constant.CacheKey;
import com.dd.cloud.core.common.entity.TokenEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class TokenUtilites {
    @Value(value = "${token_url}")
    private String tokenUrl;
    @Value(value = "${grant_type}")
    private String grant_type;
    @Value(value = "${client_id}")
    private String client_id;
    @Value(value = "${client_secret}")
    private String client_secret;
    @Value(value = "${scope}")
    private String scope;
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    RedisUtil redisCacheUtilities;

    public TokenEntity getToken(String username, String password) {
        String tokenCacheKey = CacheKey.USER_TOKEN + username;
        String url = tokenUrl + "?client_id=" + client_id + "&client_secret=" + client_secret + "&grant_type=" + grant_type + "&username=" + username + "&password=" + password + "&scope=" + scope;
        String jsonToken = restTemplate.getForObject(url, String.class);
        TokenEntity tokenEntity = JSONObject.parseObject(jsonToken, TokenEntity.class);
//        redisCacheUtilities.set(tokenCacheKey, tokenEntity, 60 * 120);
        return tokenEntity;

    }
}
