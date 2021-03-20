package com.dd.user.service;

import com.dd.user.dao.SysUserRoleDao;
import com.dd.user.entity.UserInfo;
import com.dd.user.entity.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*****************************************************************
* Author liuzhouyang
* Date  2020-04-06
******************************************************************/
@Service
public class UserRoleService {
    @Autowired
    private SysUserRoleDao sysUserRoleDao;

    public UserRole get(String id){
        return sysUserRoleDao.get(id);
    }


    public List<UserRole> findAllList() {
        return sysUserRoleDao.findAllList();
    }

    public int insert(UserRole sysUserRole) {
        return sysUserRoleDao.insert(sysUserRole);
    }

    public int update(UserRole sysUserRole) {
        return sysUserRoleDao.update(sysUserRole);
    }

    public int delete(String id) {
        return sysUserRoleDao.delete(id);
    }



}
