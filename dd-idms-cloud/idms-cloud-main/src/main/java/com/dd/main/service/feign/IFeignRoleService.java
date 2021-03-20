package com.dd.main.service.feign;

import com.dd.cloud.core.common.json.JsonResult;
import com.dd.main.conf.FeignConfiguration;
import com.dd.model.user.SysRole;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(value = "idms-gateway", path = "admin-service", configuration = FeignConfiguration.class)
public interface IFeignRoleService {
    @GetMapping(value = "role/{id}/user")
    List<SysRole> roleByUserId(@PathVariable("id") Long id);

    @GetMapping(value = "role/list")
    List<SysRole> roleList();

    @PostMapping(value = "role/save",consumes = "application/json")
    JsonResult save(@RequestBody SysRole role);

    @PostMapping(value = "role/delete/{id}")
    JsonResult delById(@PathVariable("id") Long id);

    @PostMapping(value = "role/update",consumes = "application/json")
    JsonResult update(@RequestBody SysRole role);

    @GetMapping(value = "role/{id}")
    SysRole getRoleById(@PathVariable("id") Long id);
}
