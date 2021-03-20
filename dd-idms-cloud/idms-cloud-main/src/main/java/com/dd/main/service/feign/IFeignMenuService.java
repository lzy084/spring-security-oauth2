package com.dd.main.service.feign;

import com.dd.cloud.core.common.json.JsonResult;
import com.dd.main.conf.FeignConfiguration;
import com.dd.model.user.SysMenu;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(value = "idms-gateway", path = "admin", configuration = FeignConfiguration.class)
public interface IFeignMenuService {
    @GetMapping("/menu/{userId}")
    List<SysMenu> getMenuByUserId(@PathVariable("userId") Long userId);

//    @GetMapping("menu/home")
//    HomeInfo homeInfo();
//
//    @GetMapping("menu/logon")
//    LogoInfo logoInfo();

    @GetMapping("menu/list")
    List<SysMenu> menuList();

    @GetMapping("menu/get/{id}")
    SysMenu getById(@PathVariable("id") Long id);

    @PostMapping("menu/save")
    JsonResult save(@RequestBody SysMenu menu);

    @PostMapping("menu/delete/{id}")
    JsonResult del(@PathVariable("id") Long id);
}
