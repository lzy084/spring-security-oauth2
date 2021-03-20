package com.dd.admin.service;

import com.dd.admin.dao.SysDepartmentMapper;
import com.dd.model.user.SysDepartment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*****************************************************************
* Author liuzhouyang
* Date  2020-10-11
******************************************************************/
@Service
public class SysDepartmentService {
    @Autowired
    private SysDepartmentMapper sysDepartmentMapper;

    public SysDepartment get(Long id){
        return sysDepartmentMapper.get(id);
    }


    public List<SysDepartment> findAllList() {
        return sysDepartmentMapper.findAllList();
    }

    public int insert(SysDepartment sysDepartment) {
        return sysDepartmentMapper.insert(sysDepartment);
    }

    public int update(SysDepartment sysDepartment) {
        return sysDepartmentMapper.update(sysDepartment);
    }

    public int delete(Long id) {
        return sysDepartmentMapper.delete(id);
    }

}
