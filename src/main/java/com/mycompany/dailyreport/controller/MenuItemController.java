package com.mycompany.dailyreport.controller;

import com.mycompany.dailyreport.domain.dto.MenuItemDTO;
import com.mycompany.dailyreport.domain.dto.SubMenuDTO;
import com.mycompany.dailyreport.service.MenuItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class MenuItemController {

    private final MenuItemService menuItemService;

    @GetMapping("menus")
    @ResponseBody
    public List<MenuItemDTO> getMasterMenu() {

        return menuItemService
                .getMasterMenuList()
                .stream()
                .map(MenuItemDTO::from)
                .collect(Collectors.toList());
    }

    @GetMapping("menu/sub")
    @ResponseBody
    public List<SubMenuDTO> getSubMenu(@RequestParam Long parentId) {

        return menuItemService
                .getSubMenuList(parentId)
                .stream()
                .map(SubMenuDTO::from)
                .collect(Collectors.toList());
    }


    @PostMapping("menu")
    @ResponseBody
    public String addMenuItem(@RequestBody MenuItemDTO menuItemDTO) {

        menuItemService.addMenuItem(menuItemDTO);
        return "OK";
    }

    @GetMapping("menu")
    @ResponseBody
    public SubMenuDTO getMenu(@RequestParam Long id) {
        return SubMenuDTO.from(menuItemService.getMenuItem(id));
    }
}
