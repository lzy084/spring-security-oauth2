package com.dd.admin.service;

import com.dd.admin.dao.SysUserMapper;
import com.dd.model.user.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*****************************************************************
* Author liuzhouyang
* Date  2020-10-11
******************************************************************/
@Service
public class SysUserService {
    @Autowired
    private SysUserMapper sysUserMapper;

    public SysUser get(Long id){
        return sysUserMapper.get(id);
    }


    public List<SysUser> findAllList() {
        return sysUserMapper.findAllList();
    }

    public int insert(SysUser sysUser) {
        return sysUserMapper.insert(sysUser);
    }

    public int update(SysUser sysUser) {
        return sysUserMapper.update(sysUser);
    }

    public int delete(Long id) {
        return sysUserMapper.delete(id);
    }

}
