package com.dd.cloud.security;


import com.dd.cloud.core.common.utilities.RedisCacheUtilities;
import com.dd.cloud.core.common.utilities.RedisUtil;
import com.dd.cloud.security.exception.AccountDisabledException;
import com.dd.cloud.security.exception.AccountLockedException;
import com.dd.cloud.security.model.entity.SecurityUserInfo;
import com.dd.cloud.security.model.service.SecurityUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;


public class UserAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {
    @Autowired
    @Qualifier(value = "passwordEncoder")
    PasswordEncoder passwordEncoder;
    @Resource
    RedisCacheUtilities redisCacheUtilities;
    private SecurityUserInfoService securityUserInfoService;

    public SecurityUserInfoService getSecurityUserInfoService() {
        return securityUserInfoService;
    }

    public void setSecurityUserInfoService(SecurityUserInfoService securityUserInfoService) {
        this.securityUserInfoService = securityUserInfoService;
    }

    public PasswordEncoder getPasswordEncoder() {
        return passwordEncoder;
    }

    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {

        if (authentication.getCredentials() == null) {
            throw new BadCredentialsException(this.messages.getMessage("10006", "认证失败，凭证不能为空"));
        } else {
            String presentedPassword = authentication.getCredentials().toString();
            if (!passwordEncoder.matches(presentedPassword, userDetails.getPassword())) {
                throw new BadCredentialsException("密码错误");
            }
        }
    }

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = (UsernamePasswordAuthenticationToken) authentication;
        SecurityUserInfo userDetails = (SecurityUserInfo) usernamePasswordAuthenticationToken.getDetails();
        additionalAuthenticationChecks(userDetails, usernamePasswordAuthenticationToken);
        accountStatusUserDetailsChecker().check(userDetails);
        if (redisCacheUtilities.hasKey(userDetails.getAccountName())) {
            redisCacheUtilities.del(userDetails.getAccountName());
        }
        redisCacheUtilities.set(userDetails.getAccountName(), usernamePasswordAuthenticationToken.getCredentials());
        return new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        return securityUserInfoService.loadUserByUsername(username);
    }

    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class
                .isAssignableFrom(authentication));
    }

    private AccountStatusUserDetailsChecker accountStatusUserDetailsChecker() {
        AccountStatusUserDetailsChecker checker = new AccountStatusUserDetailsChecker() {
            @Override
            public void check(UserDetails user) {
                if (!user.isAccountNonLocked()) {
                    throw new AccountLockedException("账号已被锁定");
                }
                if (!user.isEnabled()) {
                    throw new AccountDisabledException("账号已被禁用");
                }
                if (!user.isAccountNonExpired()) {
                    throw new AccountExpiredException("账号已过期");
                }

                if (!user.isCredentialsNonExpired()) {
                    throw new CredentialsExpiredException("用户凭证已过期");
                }
            }
        };
        return checker;
    }
}
