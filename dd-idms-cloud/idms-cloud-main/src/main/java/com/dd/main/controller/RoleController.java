package com.dd.main.controller;

import com.dd.cloud.core.common.json.JsonResult;
import com.dd.main.service.IRoleService;
import com.dd.model.user.SysRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("role")
public class RoleController {
    @Autowired
    private IRoleService roleService;
    @GetMapping("index")
    public String index(Model model){
        model.addAttribute("roleList",roleService.roleList());
        return "view/role/list.html";
    }
    @GetMapping("list")
    public List<SysRole> roleList(){
        return roleService.roleList();
    }
    @GetMapping("add")
    public String add(){
        return "view/role/add.html";
    }
    @PostMapping(value = "save")
    @ResponseBody
    public JsonResult save(@RequestBody SysRole role){
       return roleService.save(role);
    }
    @PostMapping(value = "del/{id}")
    @ResponseBody
    public JsonResult delById(@PathVariable("id")Long id){
        return roleService.delById(id);
    }
    @GetMapping(value = "{id}")
    @ResponseBody
    public SysRole getById(@PathVariable("id")Long id){
       return roleService.getRoleById(id);
    }
    @PostMapping(value = "update")
    @ResponseBody
    public JsonResult update(@RequestBody SysRole role){
        return roleService.update(role);
    }
    @RequestMapping(value = "refresh")
    public String refresh(Model model) {
        model.addAttribute("roleList",roleService.roleList());
        return "view/role/list::dataTable";
    }
    @GetMapping(value="{id}/edit")
    public String edit(@PathVariable("id")Long id,Model model){
        SysRole role=roleService.getRoleById(id);
        model.addAttribute("role",role);
        return "view/role/edit.html";
    }
}
