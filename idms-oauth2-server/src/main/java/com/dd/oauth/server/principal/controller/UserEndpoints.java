package com.dd.oauth.server.principal.controller;

import com.dd.oauth.server.principal.entity.SecurityUserInfo;
import com.dd.oauth.server.principal.service.SecurityUserInfoService;
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
    @RequestMapping(value = "user")
    public SecurityUserInfo userInfo() {
        String userName=SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        OAuth2Authentication oAuth2Authentication= (OAuth2Authentication)SecurityContextHolder.getContext().getAuthentication();
        OAuth2Request oAuth2Request= oAuth2Authentication.getOAuth2Request();
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=(UsernamePasswordAuthenticationToken)oAuth2Authentication.getUserAuthentication();
        SecurityUserInfo userInfo = securityUserInfoService.loadUserByUsername(userName);
        return userInfo;
    }
}
