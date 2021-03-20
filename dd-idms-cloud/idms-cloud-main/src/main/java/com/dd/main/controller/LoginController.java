package com.dd.main.controller;

import com.dd.main.utilities.UserUtilities;
import com.dd.model.user.SysUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    @RequestMapping(value = "login")
    public String login(HttpServletRequest request){
        return "/login.html";
    }
    @RequestMapping(value = "index")
    public String index( Model model) {
       SysUser userInfo= UserUtilities.userInfo();
       model.addAttribute("user",userInfo);
        return "view/index.html";
    }
    @RequestMapping("main")
    public String main(){
        return "view/main.html";
    }
}
