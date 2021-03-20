package com.dd.admin.service;

import com.dd.admin.dao.SysMenuMapper;
import com.dd.model.user.SysMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*****************************************************************
* Author liuzhouyang
* Date  2020-10-11
******************************************************************/
@Service
public class SysMenuService {
    @Autowired
    private SysMenuMapper sysMenuMapper;

    public SysMenu get(Long id){
        return sysMenuMapper.get(id);
    }


    public List<SysMenu> findAllList() {
        return sysMenuMapper.findAllList();
    }

    public int insert(SysMenu sysMenu) {
        return sysMenuMapper.insert(sysMenu);
    }

    public int update(SysMenu sysMenu) {
        return sysMenuMapper.update(sysMenu);
    }

    public int delete(Long id) {
        return sysMenuMapper.delete(id);
    }

}
