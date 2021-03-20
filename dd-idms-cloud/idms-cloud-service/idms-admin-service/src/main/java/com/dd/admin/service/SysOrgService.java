package com.dd.admin.service;

import com.dd.admin.dao.SysOrgMapper;
import com.dd.model.user.SysOrg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*****************************************************************
* Author liuzhouyang
* Date  2020-10-11
******************************************************************/
@Service
public class SysOrgService {
    @Autowired
    private SysOrgMapper sysOrgMapper;

    public SysOrg get(Long id){
        return sysOrgMapper.get(id);
    }


    public List<SysOrg> findAllList() {
        return sysOrgMapper.findAllList();
    }

    public int insert(SysOrg sysOrg) {
        return sysOrgMapper.insert(sysOrg);
    }

    public int update(SysOrg sysOrg) {
        return sysOrgMapper.update(sysOrg);
    }

    public int delete(Long id) {
        return sysOrgMapper.delete(id);
    }

}
