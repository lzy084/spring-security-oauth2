package com.dd.user.dao;

import com.dd.base.model.user.Menu;
import org.springframework.stereotype.Repository;
import com.dd.commons.mybatis.IBaseMapper;

import java.util.ArrayList;
import java.util.List;

/*****************************************************************
* Author liuzhouyang
* Date  2020-04-06
******************************************************************/
@Repository
public interface SysMenuDao  extends IBaseMapper<Menu>{
    List<Menu> findMenuListByRoleIds(List<Integer> roleIds);
}