package com.dd.user.service;

import com.dd.user.dao.SysRoleDao;
import com.dd.user.entity.Menu;
import com.dd.user.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/*****************************************************************
* Author liuzhouyang
* Date  2020-04-06
******************************************************************/
@Service
public class RoleService {
    @Autowired
    private SysRoleDao sysRoleDao;
    @Autowired
    private MenuService menuService;

    public Role get(int id){
        return sysRoleDao.getRoleById(id);
    }



    public List<Role> findAllList() {
        return sysRoleDao.findAllList();
    }

    public int insert(Role sysRole) {
        return sysRoleDao.insert(sysRole);
    }

    public int update(Role sysRole) {
        return sysRoleDao.update(sysRole);
    }

    public int delete(String id) {
        return sysRoleDao.delete(id);
    }


}
