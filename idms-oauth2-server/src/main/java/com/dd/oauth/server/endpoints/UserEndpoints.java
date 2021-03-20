package com.dd.oauth.server.endpoints;

import com.dd.commons.utilities.RedisCacheUtilities;
import com.dd.user.entity.UserInfo;
import com.dd.user.service.SecurityUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "oauth2")
public class UserEndpoints {
    @Autowired
    SecurityUserInfoService securityUserInfoService;
    @Autowired
    RedisCacheUtilities redisCacheUtilities;

    @RequestMapping(value = "user")
    public UserInfo userInfo() {
        String userName=SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        OAuth2Authentication oAuth2Authentication= (OAuth2Authentication)SecurityContextHolder.getContext().getAuthentication();
        OAuth2Request oAuth2Request= oAuth2Authentication.getOAuth2Request();
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=(UsernamePasswordAuthenticationToken)oAuth2Authentication.getUserAuthentication();
        if (redisCacheUtilities.hasKey(userName)) {
            return (UserInfo) redisCacheUtilities.get(userName);
        } else {
            UserInfo userInfo = securityUserInfoService.loadUserByUsername(userName);
            return userInfo;
        }
    }
}
