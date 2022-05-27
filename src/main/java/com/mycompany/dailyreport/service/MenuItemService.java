package com.mycompany.dailyreport.service;

import com.mycompany.dailyreport.domain.ItemClass;
import com.mycompany.dailyreport.domain.MenuItem;
import com.mycompany.dailyreport.domain.dto.MenuItemDTO;
import com.mycompany.dailyreport.repository.MenuItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MenuItemService {

    private final MenuItemRepository menuItemRepository;

    @Transactional
    public Long addMenuItem(MenuItemDTO menuItemDTO) {

        long sortSeq = getSortSeq(menuItemDTO);

        menuItemDTO.setSortSeq(sortSeq);
        MenuItem menuItem = new MenuItem(menuItemDTO);

        menuItemRepository.save(menuItem);
        return menuItem.getId();
    }

    private Long getSortSeq(MenuItemDTO menuItemDTO) {
        long result;
        if(isMasterItem(menuItemDTO)) {
            result = menuItemRepository.getMasterMaxSortSeq() + 1;
        } else {
            result = menuItemRepository.getSubMaxSortSeq(menuItemDTO.getParent()) + 1;
        }

        return result;
    }

    private boolean isMasterItem(MenuItemDTO menuItemDTO) {
        return menuItemDTO.getItemClass().equals(ItemClass.MASTER);
    }

    public MenuItem getMenuItem(Long id) {
        return menuItemRepository.findOne(id);
    }

    public List<MenuItem> getMasterMenuList() {
        return menuItemRepository.getMasterMenuList();
    }

    public List<MenuItem> getSubMenuList(Long id) {

        return menuItemRepository.getSubMenuList(id);
    }
}
