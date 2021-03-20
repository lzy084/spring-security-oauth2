package com.dd.admin.service;

import com.dd.admin.dao.SysAccountMapper;
import com.dd.model.user.SysAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*****************************************************************
* Author liuzhouyang
* Date  2020-10-11
******************************************************************/
@Service
public class SysAccountService {
    @Autowired
    private SysAccountMapper sysAccountMapper;

    public SysAccount get(Long id){
        return sysAccountMapper.get(id);
    }


    public List<SysAccount> findAllList() {
        return sysAccountMapper.findAllList();
    }

    public int insert(SysAccount sysAccount) {
        return sysAccountMapper.insert(sysAccount);
    }

    public int update(SysAccount sysAccount) {
        return sysAccountMapper.update(sysAccount);
    }

    public int delete(Long id) {
        return sysAccountMapper.delete(id);
    }

}
