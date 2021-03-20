package com.dd.main.service;

import com.dd.cloud.core.common.json.JsonResult;
import com.dd.model.user.SysRole;

import java.util.List;

public interface IRoleService {
    List<SysRole> roleByUserId(Long id);
    List<SysRole> roleList();
    JsonResult save(SysRole role);



    JsonResult delById( Long id);

    JsonResult update(SysRole role);

    SysRole getRoleById(Long id);

}
