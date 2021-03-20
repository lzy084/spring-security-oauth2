package com.dd.admin.service;

import com.dd.admin.dao.SysRoleMenuMapper;
import com.dd.model.user.SysRoleMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*****************************************************************
* Author liuzhouyang
* Date  2020-10-11
******************************************************************/
@Service
public class SysRoleMenuService {
    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    public SysRoleMenu get(Long id){
        return sysRoleMenuMapper.get(id);
    }


    public List<SysRoleMenu> findAllList() {
        return sysRoleMenuMapper.findAllList();
    }

    public int insert(SysRoleMenu sysRoleMenu) {
        return sysRoleMenuMapper.insert(sysRoleMenu);
    }

    public int update(SysRoleMenu sysRoleMenu) {
        return sysRoleMenuMapper.update(sysRoleMenu);
    }

    public int delete(Long id) {
        return sysRoleMenuMapper.delete(id);
    }

}
