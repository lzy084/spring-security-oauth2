package com.dd.oauth.server.configuration;

import com.dd.oauth.server.principal.entity.SecurityUserInfo;
import com.dd.oauth.server.principal.service.SecurityUserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;


@Slf4j
public class UserAuthenticationProvider extends DaoAuthenticationProvider {
    @Autowired
    SecurityUserInfoService securityUserInfoService;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public PasswordEncoder getPasswordEncoder() {
        return passwordEncoder;
    }
    @Override
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
    public UserAuthenticationProvider(){
        setUserDetailsService(securityUserInfoService);
    }


    @Override
    public Authentication authenticate(Authentication authentication){
        String username=authentication.getName();
        String password=authentication.getCredentials().toString();
        SecurityUserInfo sysAccount= securityUserInfoService.loadUserByUsername(username);
        checkPwd(password,sysAccount.getPassword());
        return createSuccessAuthentication(authentication.getPrincipal(),authentication,sysAccount);
    }
    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        log.debug("用户密码开始认证");
        if (authentication.getCredentials() == null) {
            throw new BadCredentialsException("认证失败，凭证不能为空");
        } else {
            String presentedPassword = authentication.getCredentials().toString();
            if (!passwordEncoder.matches(presentedPassword, userDetails.getPassword())) {
                throw new BadCredentialsException("密码错误");
            }
        }
    }


    private void checkPwd(String rawPassword,String encodedPassword) throws AuthenticationException {
        log.debug("用户密码开始认证");
        if (rawPassword == null) {
            throw new BadCredentialsException("认证失败，凭证不能为空");
        } else {
            if (!passwordEncoder.matches(rawPassword, encodedPassword)) {
                throw new BadCredentialsException("密码错误");
            }
        }
    }


}
