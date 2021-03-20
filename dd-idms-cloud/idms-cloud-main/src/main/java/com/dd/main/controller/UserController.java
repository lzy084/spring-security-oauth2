package com.dd.main.controller;

import com.dd.main.service.IUserService;
import com.dd.model.user.SysUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

@RequestMapping("user")
@Controller
public class UserController {
    @Resource
    IUserService userService;
    @RequestMapping("list")
    public String userList(Model model){
        List<SysUser> userInfoList= userService.getAllUser();
        model.addAttribute("userList",userInfoList);
        return "view/user/list.html";
    }
}
