package com.dd.admin.service;

import com.dd.admin.dao.SysDictValueMapper;
import com.dd.model.user.SysDictValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*****************************************************************
* Author liuzhouyang
* Date  2020-10-11
******************************************************************/
@Service
public class SysDictValueService {
    @Autowired
    private SysDictValueMapper sysDictValueMapper;

    public SysDictValue get(Long id){
        return sysDictValueMapper.get(id);
    }


    public List<SysDictValue> findAllList() {
        return sysDictValueMapper.findAllList();
    }

    public int insert(SysDictValue sysDictValue) {
        return sysDictValueMapper.insert(sysDictValue);
    }

    public int update(SysDictValue sysDictValue) {
        return sysDictValueMapper.update(sysDictValue);
    }

    public int delete(Long id) {
        return sysDictValueMapper.delete(id);
    }

}
