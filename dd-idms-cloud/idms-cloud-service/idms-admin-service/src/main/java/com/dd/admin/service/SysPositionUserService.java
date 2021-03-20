package com.dd.admin.service;

import com.dd.admin.dao.SysPositionUserMapper;
import com.dd.model.user.SysPositionUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*****************************************************************
* Author liuzhouyang
* Date  2020-10-11
******************************************************************/
@Service
public class SysPositionUserService {
    @Autowired
    private SysPositionUserMapper sysPositionUserMapper;

    public SysPositionUser get(Long id){
        return sysPositionUserMapper.get(id);
    }


    public List<SysPositionUser> findAllList() {
        return sysPositionUserMapper.findAllList();
    }

    public int insert(SysPositionUser sysPositionUser) {
        return sysPositionUserMapper.insert(sysPositionUser);
    }

    public int update(SysPositionUser sysPositionUser) {
        return sysPositionUserMapper.update(sysPositionUser);
    }

    public int delete(Long id) {
        return sysPositionUserMapper.delete(id);
    }

}
