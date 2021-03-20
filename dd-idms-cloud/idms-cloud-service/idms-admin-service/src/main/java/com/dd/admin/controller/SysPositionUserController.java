package com.dd.admin.controller;

import com.dd.model.user.SysPositionUser;
import com.dd.admin.service.SysPositionUserService;
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
@RequestMapping(value = "/sys/position/user" ,produces = HTTP_RESPONSE_CONTENTTYPE)
public class SysPositionUserController {
    @Autowired
    private SysPositionUserService sysPositionUserService;

    @RequestMapping(value = {"/list"}, method = RequestMethod.GET)
    public JsonResult list() {
        List<SysPositionUser> sysPositionUsers = sysPositionUserService.findAllList();
        return JsonResult
                .create()
                .data(sysPositionUsers);
    }

    @RequestMapping(value = {"/get"}, method = RequestMethod.GET)
    public JsonResult get(Long id) {
        SysPositionUser sysPositionUser = sysPositionUserService.get(id);
        return JsonResult
                .create()
                .data(sysPositionUser);
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public JsonResult insert(@RequestBody SysPositionUser sysPositionUser) {
        if (sysPositionUserService.insert(sysPositionUser) > 0) {
        return JsonResult
                .create();
        } else {
                return JsonResult
                        .create()
                        .success(false);
        }
    }


    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public JsonResult update(@RequestBody SysPositionUser sysPositionUser) {
        if (sysPositionUserService.update(sysPositionUser) > 0) {
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
        if (sysPositionUserService.delete(id) > 0) {
                return JsonResult
                        .create();
        } else {
                return JsonResult
                        .create()
                        .success(false);
        }
    }

}
