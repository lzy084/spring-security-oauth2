package com.dd.admin.service;

import com.dd.admin.dao.SysPositionMapper;
import com.dd.model.user.SysPosition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*****************************************************************
* Author liuzhouyang
* Date  2020-10-11
******************************************************************/
@Service
public class SysPositionService {
    @Autowired
    private SysPositionMapper sysPositionMapper;

    public SysPosition get(Long id){
        return sysPositionMapper.get(id);
    }


    public List<SysPosition> findAllList() {
        return sysPositionMapper.findAllList();
    }

    public int insert(SysPosition sysPosition) {
        return sysPositionMapper.insert(sysPosition);
    }

    public int update(SysPosition sysPosition) {
        return sysPositionMapper.update(sysPosition);
    }

    public int delete(Long id) {
        return sysPositionMapper.delete(id);
    }

}
