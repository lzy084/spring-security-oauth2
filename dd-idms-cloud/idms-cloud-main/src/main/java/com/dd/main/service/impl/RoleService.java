package com.dd.main.service.impl;

import com.dd.cloud.core.common.json.JsonResult;
import com.dd.main.service.IRoleService;
import com.dd.main.service.feign.IFeignRoleService;
import com.dd.model.user.SysRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService implements IRoleService {
    @Autowired
    IFeignRoleService feignRoleService;

    @Override
    public List<SysRole> roleByUserId(Long id) {
        return feignRoleService.roleByUserId(id);
    }

    @Override
    public List<SysRole> roleList() {
        return feignRoleService.roleList();
    }

    @Override
    public JsonResult save(SysRole role) {
        return feignRoleService.save(role);
    }

    @Override
    public JsonResult delById(Long id) {
        return feignRoleService.delById(id);
    }

    @Override
    public JsonResult update(SysRole role) {
        return feignRoleService.update(role);
    }

    @Override
    public SysRole getRoleById(Long id) {
        return feignRoleService.getRoleById(id);
    }

}
