package com.dd.main.controller;

import com.alibaba.fastjson.JSONObject;
import com.dd.cloud.core.common.json.JsonResult;
import com.dd.main.service.IMenuService;
import com.dd.model.user.SysMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = "menu")
public class MenuController {
    @Autowired
    IMenuService iMenuService;

    @GetMapping("{userId}")
    @ResponseBody
    public JsonResult getMenu(@PathVariable("userId") Long userId) {
        return JsonResult.create().data(iMenuService.getMenuByUserId(userId));
    }

    @GetMapping("list")
    public String list(Model model) {
        List<SysMenu> menus = iMenuService.menuList();
        model.addAttribute("menuList", JSONObject.toJSONString(menus));
        return "view/menu/list.html";
    }

    @PostMapping("save")
    public JsonResult save(@RequestBody SysMenu menu) {
        return iMenuService.save(menu);
    }

    @GetMapping("get/{id}")
    public SysMenu getById(@PathVariable("id") Long id) {
        return iMenuService.getById(id);
    }
    @GetMapping("add")
    public ModelAndView add(String id,String name){
        ModelAndView mv=new ModelAndView();
        mv.addObject("menuId",id);
        mv.addObject("menuName",name);
        mv.setViewName("view/menu/add.html");
        return mv;
    }
    @GetMapping("edit/{id}")
    public String edit(@PathVariable("id")Long id,Model model){
        SysMenu menu=iMenuService.getById(id);
        SysMenu parentMenu=iMenuService.getById(id);
        model.addAttribute("menu",menu);
        model.addAttribute("parentName",parentMenu.getMenuName());
        return "view/menu/edit.html";
    }

}
