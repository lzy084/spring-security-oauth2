package com.dd.admin.service;

import com.dd.admin.dao.SysUserOrgMapper;
import com.dd.model.user.SysUserOrg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*****************************************************************
* Author liuzhouyang
* Date  2020-10-11
******************************************************************/
@Service
public class SysUserOrgService {
    @Autowired
    private SysUserOrgMapper sysUserOrgMapper;

    public SysUserOrg get(Long id){
        return sysUserOrgMapper.get(id);
    }


    public List<SysUserOrg> findAllList() {
        return sysUserOrgMapper.findAllList();
    }

    public int insert(SysUserOrg sysUserOrg) {
        return sysUserOrgMapper.insert(sysUserOrg);
    }

    public int update(SysUserOrg sysUserOrg) {
        return sysUserOrgMapper.update(sysUserOrg);
    }

    public int delete(Long id) {
        return sysUserOrgMapper.delete(id);
    }

}
