package com.dd.admin.controller;

import com.dd.model.user.TenantInfo;
import com.dd.admin.service.TenantInfoService;
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
@RequestMapping(value = "/tenant/info" ,produces = HTTP_RESPONSE_CONTENTTYPE)
public class TenantInfoController {
    @Autowired
    private TenantInfoService tenantInfoService;

    @RequestMapping(value = {"/list"}, method = RequestMethod.GET)
    public JsonResult list() {
        List<TenantInfo> tenantInfos = tenantInfoService.findAllList();
        return JsonResult
                .create()
                .data(tenantInfos);
    }

    @RequestMapping(value = {"/get"}, method = RequestMethod.GET)
    public JsonResult get(Long id) {
        TenantInfo tenantInfo = tenantInfoService.get(id);
        return JsonResult
                .create()
                .data(tenantInfo);
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public JsonResult insert(@RequestBody TenantInfo tenantInfo) {
        if (tenantInfoService.insert(tenantInfo) > 0) {
        return JsonResult
                .create();
        } else {
                return JsonResult
                        .create()
                        .success(false);
        }
    }


    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public JsonResult update(@RequestBody TenantInfo tenantInfo) {
        if (tenantInfoService.update(tenantInfo) > 0) {
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
        if (tenantInfoService.delete(id) > 0) {
                return JsonResult
                        .create();
        } else {
                return JsonResult
                        .create()
                        .success(false);
        }
    }

}
