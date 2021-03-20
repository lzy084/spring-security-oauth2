package com.dd.admin.controller;

import com.dd.model.user.SysDictType;
import com.dd.admin.service.SysDictTypeService;
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
@RequestMapping(value = "/sys/dict/type" ,produces = HTTP_RESPONSE_CONTENTTYPE)
public class SysDictTypeController {
    @Autowired
    private SysDictTypeService sysDictTypeService;

    @RequestMapping(value = {"/list"}, method = RequestMethod.GET)
    public JsonResult list() {
        List<SysDictType> sysDictTypes = sysDictTypeService.findAllList();
        return JsonResult
                .create()
                .data(sysDictTypes);
    }

    @RequestMapping(value = {"/get"}, method = RequestMethod.GET)
    public JsonResult get(Long id) {
        SysDictType sysDictType = sysDictTypeService.get(id);
        return JsonResult
                .create()
                .data(sysDictType);
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public JsonResult insert(@RequestBody SysDictType sysDictType) {
        if (sysDictTypeService.insert(sysDictType) > 0) {
        return JsonResult
                .create();
        } else {
                return JsonResult
                        .create()
                        .success(false);
        }
    }


    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public JsonResult update(@RequestBody SysDictType sysDictType) {
        if (sysDictTypeService.update(sysDictType) > 0) {
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
        if (sysDictTypeService.delete(id) > 0) {
                return JsonResult
                        .create();
        } else {
                return JsonResult
                        .create()
                        .success(false);
        }
    }

}
