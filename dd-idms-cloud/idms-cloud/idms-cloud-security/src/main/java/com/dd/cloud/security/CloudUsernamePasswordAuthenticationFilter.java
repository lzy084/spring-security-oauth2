package com.dd.cloud.security;


import com.dd.cloud.security.model.entity.SecurityUserInfo;
import com.dd.cloud.security.model.service.SecurityUserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Slf4j
public class CloudUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    @Autowired
    @Qualifier("passwordEncoder")
    PasswordEncoder passwordEncoder;
    @Autowired
    UserAuthenticationProvider userAuthenticationProvider;
    @Autowired
    SecurityUserInfoService securityUserInfoService;
//    public void setUserService(SecurityUserInfoService securityUserInfoService) {
//        this.securityUserInfoService = securityUserInfoService;
//    }
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (!request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException(
                    "Authentication method not supported: " + request.getMethod());
        }
        String username = obtainUsername(request);
        String password = obtainPassword(request);
        SecurityUserInfo userDetails= securityUserInfoService.loadUserByUsername(username);
        checkUserPassword(password,userDetails.getAccountPwd());
        securityUserInfoService.setSecurityUserInfo(userDetails);
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(userDetails, password,userDetails.getAuthorities());
        authRequest.setDetails(userDetails);

        return this.userAuthenticationProvider.authenticate(authRequest);
    }

    @Override
    protected String obtainUsername(HttpServletRequest request) {
        Object obj = request.getParameter(USERNAME);
        return null == obj ? "" : obj.toString();
    }
    @Override
    protected String obtainPassword(HttpServletRequest request) {
        Object obj = request.getParameter(PASSWORD);
        return null == obj ? "" : obj.toString();
    }
    private boolean checkUserPassword(String rawPassword,String encodePassword){
        boolean result=passwordEncoder.matches(rawPassword,encodePassword);
        if (!result){
            throw  new BadCredentialsException("密码错误");
        }
        return result;
    }

}
