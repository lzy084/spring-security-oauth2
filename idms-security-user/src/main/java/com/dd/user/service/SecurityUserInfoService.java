package com.dd.user.service;

import com.dd.commons.utilities.RedisCacheUtilities;
import com.dd.user.dao.UserInfoDao;
import com.dd.user.entity.Menu;
import com.dd.user.entity.Role;
import com.dd.user.entity.UserInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

/*****************************************************************
 * Author liuzhouyang
 * Date  2020-04-06
 ******************************************************************/
@Service
public class SecurityUserInfoService implements UserDetailsService {
    @Autowired
    private UserInfoDao userInfoDao;
    @Autowired
    RedisCacheUtilities redisCacheUtilities;

    public UserInfo get(String id) {
        return userInfoDao.get(id);
    }


    public List<UserInfo> findAllList() {
        return userInfoDao.findAllList();
    }

    public int insert(UserInfo sysUser) {
        return userInfoDao.insert(sysUser);
    }

    public int update(UserInfo sysUser) {
        return userInfoDao.update(sysUser);
    }

    public int delete(String id) {
        return userInfoDao.delete(id);
    }

    @Override
    public UserInfo loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = userInfoDao.findByUserName(username);
        if (userInfo == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        setUserInfo(userInfo);
        redisCacheUtilities.set(username, userInfo);
        return userInfo;
    }

    private void setUserInfo(UserInfo userInfo) {
        List<Role> roles = userInfo.getRoleList();
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (Role role : roles) {
            if (StringUtils.isNotBlank(role.getRoleCode())) {
                grantedAuthorities.add(new SimpleGrantedAuthority(role.getRoleCode()));
            }
            for (Menu menu : role.getMenuList()) {
                if (StringUtils.isNotBlank(menu.getPermission())) {
                    if (!grantedAuthorities.contains(menu.getPermission())) {
                        grantedAuthorities.add(new SimpleGrantedAuthority(menu.getPermission()));
                    }
                }
            }
        }
        userInfo.setAuthorities(grantedAuthorities);
    }
}
