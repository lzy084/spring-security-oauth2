package com.dd.main.service.impl;

import com.dd.main.service.IUserService;
import com.dd.main.service.feign.IFeignUserService;
import com.dd.model.user.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {
    @Autowired
    IFeignUserService feignUserService;

    @Override
    public List<SysUser> getAllUser() {
        return feignUserService.getAllUser();
    }
}
