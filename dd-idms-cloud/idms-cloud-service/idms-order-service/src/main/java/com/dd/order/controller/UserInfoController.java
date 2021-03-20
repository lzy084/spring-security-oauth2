package com.dd.order.controller;

import com.dd.cloud.core.common.json.JsonResult;
import com.dd.order.entity.UserInfo;
import com.dd.order.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.dd.cloud.core.common.constant.IRequestHttpConst.HTTP_RESPONSE_CONTENTTYPE;

/*****************************************************************
 * Author liuzhouyang
 * Date  2020-09-21
 ******************************************************************/
@RestController
@RequestMapping(value = "/userInfo" ,produces = HTTP_RESPONSE_CONTENTTYPE)
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping(value = {"/list"}, method = RequestMethod.GET)
    public JsonResult list() {
        List<UserInfo> userInfos = userInfoService.findAllList();
        return JsonResult
                .create()
                .data(userInfos);
    }

    @RequestMapping(value = {"/get"}, method = RequestMethod.GET)
    public JsonResult get(String id) {
        UserInfo userInfo = userInfoService.get(id);
        return JsonResult
                .create()
                .data(userInfo);
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public JsonResult insert(@RequestBody UserInfo userInfo) {
        if (userInfoService.insert(userInfo) > 0) {
        return JsonResult
                .create();
        } else {
                return JsonResult
                        .create()
                        .success(false);
        }
    }


    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public JsonResult update(@RequestBody UserInfo userInfo) {
        if (userInfoService.update(userInfo) > 0) {
                return JsonResult
                        .create();
        } else {
                    return JsonResult
                            .create()
                            .success(false);
}
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public JsonResult delete( String id) {
        if (userInfoService.delete(id) > 0) {
                return JsonResult
                        .create();
        } else {
                return JsonResult
                        .create()
                        .success(false);
        }
    }

}
