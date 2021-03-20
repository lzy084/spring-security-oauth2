package com.dd.admin.service;

import com.dd.admin.dao.SysUserRoleMapper;
import com.dd.model.user.SysUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*****************************************************************
* Author liuzhouyang
* Date  2020-10-11
******************************************************************/
@Service
public class SysUserRoleService {
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    public SysUserRole get(Long id){
        return sysUserRoleMapper.get(id);
    }


    public List<SysUserRole> findAllList() {
        return sysUserRoleMapper.findAllList();
    }

    public int insert(SysUserRole sysUserRole) {
        return sysUserRoleMapper.insert(sysUserRole);
    }

    public int update(SysUserRole sysUserRole) {
        return sysUserRoleMapper.update(sysUserRole);
    }

    public int delete(Long id) {
        return sysUserRoleMapper.delete(id);
    }

}
