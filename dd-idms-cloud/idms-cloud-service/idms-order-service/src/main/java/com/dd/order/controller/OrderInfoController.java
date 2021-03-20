package com.dd.order.controller;

import com.dd.cloud.core.common.json.JsonResult;
import com.dd.order.entity.OrderInfo;
import com.dd.order.service.OrderInfoService;
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
@RequestMapping(value = "/orderInfo" ,produces = HTTP_RESPONSE_CONTENTTYPE)
public class OrderInfoController {
    @Autowired
    private OrderInfoService orderInfoService;

    @RequestMapping(value = {"/list"}, method = RequestMethod.GET)
    public JsonResult list() {
        List<OrderInfo> orderInfos = orderInfoService.findAllList();
        return JsonResult
                .create()
                .data(orderInfos);
    }

    @RequestMapping(value = {"/get"}, method = RequestMethod.GET)
    public JsonResult get(String id) {
        OrderInfo orderInfo = orderInfoService.get(id);
        return JsonResult
                .create()
                .data(orderInfo);
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public JsonResult insert(@RequestBody OrderInfo orderInfo) {
        if (orderInfoService.insert(orderInfo) > 0) {
        return JsonResult
                .create();
        } else {
                return JsonResult
                        .create()
                        .success(false);
        }
    }


    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public JsonResult update(@RequestBody OrderInfo orderInfo) {
        if (orderInfoService.update(orderInfo) > 0) {
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
        if (orderInfoService.delete(id) > 0) {
                return JsonResult
                        .create();
        } else {
                return JsonResult
                        .create()
                        .success(false);
        }
    }

}
