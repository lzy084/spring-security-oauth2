package com.dd.admin.controller;

import com.dd.model.user.SysRoleMenu;
import com.dd.admin.service.SysRoleMenuService;
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
@RequestMapping(value = "/sys/role/menu" ,produces = HTTP_RESPONSE_CONTENTTYPE)
public class SysRoleMenuController {
    @Autowired
    private SysRoleMenuService sysRoleMenuService;

    @RequestMapping(value = {"/list"}, method = RequestMethod.GET)
    public JsonResult list() {
        List<SysRoleMenu> sysRoleMenus = sysRoleMenuService.findAllList();
        return JsonResult
                .create()
                .data(sysRoleMenus);
    }

    @RequestMapping(value = {"/get"}, method = RequestMethod.GET)
    public JsonResult get(Long id) {
        SysRoleMenu sysRoleMenu = sysRoleMenuService.get(id);
        return JsonResult
                .create()
                .data(sysRoleMenu);
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public JsonResult insert(@RequestBody SysRoleMenu sysRoleMenu) {
        if (sysRoleMenuService.insert(sysRoleMenu) > 0) {
        return JsonResult
                .create();
        } else {
                return JsonResult
                        .create()
                        .success(false);
        }
    }


    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public JsonResult update(@RequestBody SysRoleMenu sysRoleMenu) {
        if (sysRoleMenuService.update(sysRoleMenu) > 0) {
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
        if (sysRoleMenuService.delete(id) > 0) {
                return JsonResult
                        .create();
        } else {
                return JsonResult
                        .create()
                        .success(false);
        }
    }

}
