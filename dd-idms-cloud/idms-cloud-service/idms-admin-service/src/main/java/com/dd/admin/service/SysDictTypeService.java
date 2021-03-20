package com.dd.admin.service;

import com.dd.admin.dao.SysDictTypeMapper;
import com.dd.model.user.SysDictType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*****************************************************************
* Author liuzhouyang
* Date  2020-10-11
******************************************************************/
@Service
public class SysDictTypeService {
    @Autowired
    private SysDictTypeMapper sysDictTypeMapper;

    public SysDictType get(Long id){
        return sysDictTypeMapper.get(id);
    }


    public List<SysDictType> findAllList() {
        return sysDictTypeMapper.findAllList();
    }

    public int insert(SysDictType sysDictType) {
        return sysDictTypeMapper.insert(sysDictType);
    }

    public int update(SysDictType sysDictType) {
        return sysDictTypeMapper.update(sysDictType);
    }

    public int delete(Long id) {
        return sysDictTypeMapper.delete(id);
    }

}
