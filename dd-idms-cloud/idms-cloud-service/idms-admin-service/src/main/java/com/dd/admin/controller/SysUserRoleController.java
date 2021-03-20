package com.dd.admin.controller;

import com.dd.model.user.SysUserRole;
import com.dd.admin.service.SysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import com.dd.cloud.core.common.json.JsonResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import static com.dd.cloud.core.common.constant.IRequestHttpConst.HTTP_RESPONSE_CONTENTTYPE;

/*****************************************************************
 * Author liuzhouyang
 * Date  2020-10-11
 ******************************************************************/
@RestController
@RequestMapping(value = "/sys/user/role" ,produces = HTTP_RESPONSE_CONTENTTYPE)
public class SysUserRoleController {
    @Autowired
    private SysUserRoleService sysUserRoleService;

    @RequestMapping(value = {"/list"}, method = RequestMethod.GET)
    public JsonResult list() {
        List<SysUserRole> sysUserRoles = sysUserRoleService.findAllList();
        return JsonResult
                .create()
                .data(sysUserRoles);
    }

    @RequestMapping(value = {"/get"}, method = RequestMethod.GET)
    public JsonResult get(Long id) {
        SysUserRole sysUserRole = sysUserRoleService.get(id);
        return JsonResult
                .create()
                .data(sysUserRole);
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public JsonResult insert(@RequestBody SysUserRole sysUserRole) {
        if (sysUserRoleService.insert(sysUserRole) > 0) {
        return JsonResult
                .create();
        } else {
                return JsonResult
                        .create()
                        .success(false);
        }
    }


    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public JsonResult update(@RequestBody SysUserRole sysUserRole) {
        if (sysUserRoleService.update(sysUserRole) > 0) {
                return JsonResult
                        .create();
        } else {
                    return JsonResult
                            .create()
                            .success(false);
}
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public JsonResult delete( Long id) {
        if (sysUserRoleService.delete(id) > 0) {
                return JsonResult
                        .create();
        } else {
                return JsonResult
                        .create()
                        .success(false);
        }
    }

}
