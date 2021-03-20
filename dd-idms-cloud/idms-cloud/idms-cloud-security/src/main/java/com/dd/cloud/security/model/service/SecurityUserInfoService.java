package com.dd.cloud.security.model.service;


import com.dd.cloud.security.model.dao.SecurityUserInfoMapper;
import com.dd.cloud.security.model.entity.SecurityUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*****************************************************************
 * Author liuzhouyang
 * Date  2020-04-06
 ******************************************************************/
@Service
public class SecurityUserInfoService implements UserDetailsService {
    @Autowired
    private SecurityUserInfoMapper securityUserInfoMapper;

    public SecurityUserInfo getSecurityUserInfo() {
        return securityUserInfo;
    }

    public void setSecurityUserInfo(SecurityUserInfo securityUserInfo) {
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        List<String>authorityList=securityUserInfoMapper.queryAuthorities(securityUserInfo.getSysUser().getUserId());
        if(authorityList!=null && authorityList.size()>0){
            for(String aut:authorityList){
                grantedAuthorities.add(new SimpleGrantedAuthority(aut));
            }
        }
        securityUserInfo.setPassword(securityUserInfo.getAccountPwd());
        securityUserInfo.setAuthorities(grantedAuthorities);
        this.securityUserInfo = securityUserInfo;
    }

    private SecurityUserInfo securityUserInfo;

    @Override
    public SecurityUserInfo loadUserByUsername(String username) throws UsernameNotFoundException {
        SecurityUserInfo securityUserInfo=securityUserInfoMapper.findByUserName(username);
        if(securityUserInfo==null){
            throw new UsernameNotFoundException("用户不存在");
        }
        return securityUserInfo;

    }
}
