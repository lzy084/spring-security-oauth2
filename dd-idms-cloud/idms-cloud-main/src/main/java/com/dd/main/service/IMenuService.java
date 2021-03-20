package com.dd.main.service;

import com.dd.cloud.core.common.json.JsonResult;
import com.dd.model.user.SysMenu;

import java.util.List;
import java.util.Map;

public interface IMenuService {
    Map<String,Object> getMenuByUserId(Long userId);
    List<SysMenu> menuList();
    SysMenu getById(Long id);
    JsonResult save(SysMenu menu);
    JsonResult del(Long id);
}
