package com.dd.main.utilities;

import com.dd.cloud.security.model.entity.SecurityUserInfo;
import com.dd.model.user.SysUser;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserUtilities {
    public static SysUser userInfo(){
        SecurityUserInfo userInfo = (SecurityUserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userInfo.getSysUser();
    }
}
