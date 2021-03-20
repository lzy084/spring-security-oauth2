package com.dd.admin.controller;

import com.dd.model.user.SysMenu;
import com.dd.admin.service.SysMenuService;
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
@RequestMapping(value = "/sys/menu" ,produces = HTTP_RESPONSE_CONTENTTYPE)
public class SysMenuController {
    @Autowired
    private SysMenuService sysMenuService;

    @RequestMapping(value = {"/list"}, method = RequestMethod.GET)
    public JsonResult list() {
        List<SysMenu> sysMenus = sysMenuService.findAllList();
        return JsonResult
                .create()
                .data(sysMenus);
    }

    @RequestMapping(value = {"/get"}, method = RequestMethod.GET)
    public JsonResult get(Long id) {
        SysMenu sysMenu = sysMenuService.get(id);
        return JsonResult
                .create()
                .data(sysMenu);
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public JsonResult insert(@RequestBody SysMenu sysMenu) {
        if (sysMenuService.insert(sysMenu) > 0) {
        return JsonResult
                .create();
        } else {
                return JsonResult
                        .create()
                        .success(false);
        }
    }


    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public JsonResult update(@RequestBody SysMenu sysMenu) {
        if (sysMenuService.update(sysMenu) > 0) {
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
        if (sysMenuService.delete(id) > 0) {
                return JsonResult
                        .create();
        } else {
                return JsonResult
                        .create()
                        .success(false);
        }
    }

}
