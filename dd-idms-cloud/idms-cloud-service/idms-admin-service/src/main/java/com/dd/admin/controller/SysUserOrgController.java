package com.dd.admin.controller;

import com.dd.model.user.SysUserOrg;
import com.dd.admin.service.SysUserOrgService;
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
@RequestMapping(value = "/sys/user/org" ,produces = HTTP_RESPONSE_CONTENTTYPE)
public class SysUserOrgController {
    @Autowired
    private SysUserOrgService sysUserOrgService;

    @RequestMapping(value = {"/list"}, method = RequestMethod.GET)
    public JsonResult list() {
        List<SysUserOrg> sysUserOrgs = sysUserOrgService.findAllList();
        return JsonResult
                .create()
                .data(sysUserOrgs);
    }

    @RequestMapping(value = {"/get"}, method = RequestMethod.GET)
    public JsonResult get(Long id) {
        SysUserOrg sysUserOrg = sysUserOrgService.get(id);
        return JsonResult
                .create()
                .data(sysUserOrg);
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public JsonResult insert(@RequestBody SysUserOrg sysUserOrg) {
        if (sysUserOrgService.insert(sysUserOrg) > 0) {
        return JsonResult
                .create();
        } else {
                return JsonResult
                        .create()
                        .success(false);
        }
    }


    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public JsonResult update(@RequestBody SysUserOrg sysUserOrg) {
        if (sysUserOrgService.update(sysUserOrg) > 0) {
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
        if (sysUserOrgService.delete(id) > 0) {
                return JsonResult
                        .create();
        } else {
                return JsonResult
                        .create()
                        .success(false);
        }
    }

}
