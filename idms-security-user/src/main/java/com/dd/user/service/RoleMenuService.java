package com.dd.user.service;

import com.dd.user.dao.RoleMenuDao;
import com.dd.user.entity.RoleMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*****************************************************************
* Author liuzhouyang
* Date  2020-04-06
******************************************************************/
@Service
public class RoleMenuService {
    @Autowired
    private RoleMenuDao roleMenuDao;

    public RoleMenu get(String id){
        return roleMenuDao.get(id);
    }


    public List<RoleMenu> findAllList() {
        return roleMenuDao.findAllList();
    }

    public int insert(RoleMenu roleMenu) {
        return roleMenuDao.insert(roleMenu);
    }

    public int update(RoleMenu roleMenu) {
        return roleMenuDao.update(roleMenu);
    }

    public int delete(String id) {
        return roleMenuDao.delete(id);
    }

}
