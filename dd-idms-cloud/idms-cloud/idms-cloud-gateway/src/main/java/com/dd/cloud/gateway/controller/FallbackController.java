//package com.dd.cloud.gateway.controller;
//
//import com.dd.commons.json.JsonResult;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class FallbackController {
//    @GetMapping(value = "/fallback")
//    public JsonResult fallBack(){
//         return JsonResult.create().success(false).code("100").message("服务无法访问，请稍后在访问");
//    }
//}
