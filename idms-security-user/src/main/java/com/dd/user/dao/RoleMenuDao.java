package com.dd.user.dao;



import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/*****************************************************************
* Author liuzhouyang
* Date  2020-04-06
******************************************************************/
@Repository
public interface RoleMenuDao  extends IBaseMapper<RoleMenu> {
    List<Menu> findMenuListByRoleId(@Param("roleId") Integer roleId);
}