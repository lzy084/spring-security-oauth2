package com.dd.user.dao;


import com.dd.base.model.user.Role;
import org.springframework.stereotype.Repository;
import com.dd.commons.mybatis.IBaseMapper;

import java.util.List;

/*****************************************************************
* Author liuzhouyang
* Date  2020-04-06
******************************************************************/
@Repository
public interface SysRoleDao  extends IBaseMapper<Role>{
    Role getRoleById(int id);
}