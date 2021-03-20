package com.dd.user.service;

import com.dd.user.dao.SysMenuDao;
import com.dd.user.entity.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*****************************************************************
* Author liuzhouyang
* Date  2020-04-06
******************************************************************/
@Service
public class MenuService {
    @Autowired
    private SysMenuDao sysMenuDao;

    public Menu get(String id){
        return sysMenuDao.get(id);
    }


    public List<Menu> findAllList() {
        return sysMenuDao.findAllList();
    }

    public int insert(Menu sysMenu) {
        return sysMenuDao.insert(sysMenu);
    }

    public int update(Menu sysMenu) {
        return sysMenuDao.update(sysMenu);
    }

    public int delete(String id) {
        return sysMenuDao.delete(id);
    }

    public List<Menu> findMenuListByRoleIds(List<Integer> roleIds){
        return  sysMenuDao.findMenuListByRoleIds(roleIds);
    }

}
