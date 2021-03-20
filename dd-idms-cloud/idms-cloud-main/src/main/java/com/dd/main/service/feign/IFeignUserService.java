package com.dd.main.service.feign;

import com.dd.main.conf.FeignConfiguration;
import com.dd.model.user.SysUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(value = "idms-gateway", path = "admin-service",configuration = FeignConfiguration.class)
public interface IFeignUserService {
    @GetMapping("/user")
    List<SysUser> getAllUser();
}
