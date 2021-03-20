package com.dd.admin.service;

import com.dd.admin.dao.SysRoleMapper;
import com.dd.model.user.SysRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*****************************************************************
* Author liuzhouyang
* Date  2020-10-11
******************************************************************/
@Service
public class SysRoleService {
    @Autowired
    private SysRoleMapper sysRoleMapper;

    public SysRole get(Long id){
        return sysRoleMapper.get(id);
    }


    public List<SysRole> findAllList() {
        return sysRoleMapper.findAllList();
    }

    public int insert(SysRole sysRole) {
        return sysRoleMapper.insert(sysRole);
    }

    public int update(SysRole sysRole) {
        return sysRoleMapper.update(sysRole);
    }

    public int delete(Long id) {
        return sysRoleMapper.delete(id);
    }

}
