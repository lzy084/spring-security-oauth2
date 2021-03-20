package com.dd.main.service.impl;

import com.dd.cloud.core.common.json.JsonResult;
import com.dd.main.dto.MenuDto;
import com.dd.main.service.IMenuService;
import com.dd.main.service.feign.IFeignMenuService;
import com.dd.model.user.SysMenu;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MenuService implements IMenuService {
    private static final Long ROOT_MENU_ID =Long.parseLong("0");

    @Autowired
    private IFeignMenuService iFeignMenuService;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public Map<String,Object> getMenuByUserId(Long userId) {
        List<SysMenu> menus= iFeignMenuService.getMenuByUserId(userId);
        List<SysMenu> menuList =generatorTreeMenu(menus);
        List<MenuDto> menuDtos=modelMapper.map(menuList,new TypeToken<List<MenuDto>>(){}.getType());
        Map<String,Object> map=new HashMap<>();
        map.put("menuInfo",menuDtos);
//        map.put("homeInfo",getHomeInfo());
//        map.put("logoInfo",getLogoInfo());
        return map;
    }

    private List<SysMenu> generatorTreeMenu(List<SysMenu> menus){
        List<SysMenu> menuList = new ArrayList<>();
        if(menus!=null && menus.size()>0){
            List<SysMenu> topMenu = menus.stream().filter(n->n.getParentId().equals(ROOT_MENU_ID)).collect(Collectors.toList());
            for (SysMenu menu : topMenu) {
                List<SysMenu> childMenu = getMenuByParentId(menus, menu.getMenuId());
                menu.setChild(childMenu);
                menuList.add(menu);
            }
        }
        return menuList;
    }

    @Override
    public List<SysMenu> menuList() {
        //List <Menu> menus=
        return iFeignMenuService.menuList();
    }

    @Override
    public SysMenu getById(Long id) {
        return iFeignMenuService.getById(id);
    }

    @Override
    public JsonResult save(SysMenu menu) {
        return iFeignMenuService.save(menu);
    }

    private static List<SysMenu> getMenuByParentId(List<SysMenu> menuUtilsList, Long id) {
        List<SysMenu> childMenu = new ArrayList<>();
        for (int i = 0; i < menuUtilsList.size(); i++) {
            SysMenu utils = menuUtilsList.get(i);
            if (utils.getParentId().equals(id)) {
                childMenu.add(menuUtilsList.get(i));
            }
        }
        for (SysMenu m : childMenu) {
            m.setChild(getMenuByParentId(menuUtilsList, m.getMenuId()));
        }
        if (childMenu.size() == 0) {
            return childMenu;
        }
        return childMenu;
    }
//    public HomeInfo getHomeInfo(){
//        return iFeignMenuService.homeInfo();
//    }
//    public LogoInfo getLogoInfo(){
//        return iFeignMenuService.logoInfo();
//    }
    public JsonResult del(Long id){
     return iFeignMenuService.del(id);
    }
}
