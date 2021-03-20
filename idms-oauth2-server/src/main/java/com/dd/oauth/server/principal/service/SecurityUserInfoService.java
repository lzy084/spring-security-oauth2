package com.dd.oauth.server.principal.service;

import com.dd.oauth.server.principal.entity.SecurityUserInfo;
import com.dd.oauth.server.principal.dao.SecurityUserInfoMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class SecurityUserInfoService implements UserDetailsService {
    @Autowired
    SecurityUserInfoMapper userInfoMapper;
    @Override
    public SecurityUserInfo loadUserByUsername(String username) throws UsernameNotFoundException {
        SecurityUserInfo securityUserInfo=userInfoMapper.findByUserName(username);
        if(securityUserInfo==null){
            throw new UsernameNotFoundException("用户不存在");
        }
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        List<String>authorityList=userInfoMapper.queryAuthorities(securityUserInfo.getSysUser().getUserId());
        if(authorityList!=null && authorityList.size()>0){
            for(String aut:authorityList){
                grantedAuthorities.add(new SimpleGrantedAuthority(aut));
            }
        }
        securityUserInfo.setUserName(username);
        securityUserInfo.setPassword(securityUserInfo.getAccountPwd());
        securityUserInfo.setAuthorities(grantedAuthorities);
        return securityUserInfo;
    }
}
