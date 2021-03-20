package com.dd.order.controller;

import com.dd.cloud.core.common.json.JsonResult;
import com.dd.order.common.GeneChar;
import com.dd.order.entity.GoodsInfo;
import com.dd.order.service.GoodsInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.dd.cloud.core.common.constant.IRequestHttpConst.HTTP_RESPONSE_CONTENTTYPE;

/*****************************************************************
 * Author liuzhouyang
 * Date  2020-09-21
 ******************************************************************/
@RestController
@RequestMapping(value = "/goodsInfo", produces = HTTP_RESPONSE_CONTENTTYPE)
public class GoodsInfoController {
    @Autowired
    private GoodsInfoService goodsInfoService;

    @RequestMapping(value = {"/list"}, method = RequestMethod.GET)
    public JsonResult list() {
        List<GoodsInfo> goodsInfos = goodsInfoService.findAllList();
        return JsonResult
                .create()
                .data(goodsInfos);
    }

    @RequestMapping(value = {"/get"}, method = RequestMethod.GET)
    public JsonResult get(String id) {
        GoodsInfo goodsInfo = goodsInfoService.get(id);
        return JsonResult
                .create()
                .data(goodsInfo);
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public JsonResult insert() {
        GoodsInfo goodsInfo = new GoodsInfo();
        goodsInfo.setGoodsName(GeneChar.getChar());
        goodsInfo.setGoodsNum(GeneChar.getNumRandom());
        System.out.println();
        //log.debug(new Date().toString());
        if (goodsInfoService.insert(goodsInfo) > 0) {
            return JsonResult
                    .create();
        } else {
            return JsonResult
                    .create()
                    .success(false);
        }
    }


    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public JsonResult update(@RequestBody GoodsInfo goodsInfo) {
        if (goodsInfoService.update(goodsInfo) > 0) {
            return JsonResult
                    .create();
        } else {
            return JsonResult
                    .create()
                    .success(false);
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public JsonResult delete(String id) {
        if (goodsInfoService.delete(id) > 0) {
            return JsonResult
                    .create();
        } else {
            return JsonResult
                    .create()
                    .success(false);
        }
    }
    @GetMapping(value = "reduce/store")
    public JsonResult reduceStore(int id,int num){
        return goodsInfoService.reduceStore(id,num);
    }

}
