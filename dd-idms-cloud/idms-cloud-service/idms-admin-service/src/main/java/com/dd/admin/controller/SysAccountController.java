package com.dd.admin.controller;

import com.dd.admin.service.SysAccountService;
import com.dd.cloud.core.common.json.JsonResult;
import com.dd.model.user.SysAccount;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping(value = "/sys/account", produces = HTTP_RESPONSE_CONTENTTYPE)
public class SysAccountController {
    @Autowired
    private SysAccountService sysAccountService;

    @RequestMapping(value = {"/list"}, method = RequestMethod.GET)
    public JsonResult list() {
        List<SysAccount> sysAccounts = sysAccountService.findAllList();
        return JsonResult
                .create()
                .data(sysAccounts);
    }

    @RequestMapping(value = {"/get"}, method = RequestMethod.GET)
    public JsonResult get(Long id) {
        SysAccount sysAccount = sysAccountService.get(id);
        return JsonResult
                .create()
                .data(sysAccount);
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public JsonResult insert(@RequestBody SysAccount sysAccount) {
        if (sysAccountService.insert(sysAccount) > 0) {
            return JsonResult
                    .create();
        } else {
            return JsonResult
                    .create()
                    .success(false);
        }
    }


    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public JsonResult update(@RequestBody SysAccount sysAccount) {
        if (sysAccountService.update(sysAccount) > 0) {
            return JsonResult
                    .create();
        } else {
            return JsonResult
                    .create()
                    .success(false);
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public JsonResult delete(Long id) {
        if (sysAccountService.delete(id) > 0) {
            return JsonResult
                    .create();
        } else {
            return JsonResult
                    .create()
                    .success(false);
        }
    }

}
