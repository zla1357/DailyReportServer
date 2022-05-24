package com.mycompany.dailyreport.service;

import com.mycompany.dailyreport.domain.ItemClass;
import com.mycompany.dailyreport.domain.MenuItem;
import com.mycompany.dailyreport.domain.dto.MenuItemDTO;
import com.mycompany.dailyreport.repository.MenuItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MenuItemService {

    private final MenuItemRepository menuItemRepository;

    public Long addMenuItem(MenuItemDTO menuItemDTO) {

        long sortSeq = getSortSeq(menuItemDTO);

        MenuItem menuItem = new MenuItem(
                menuItemDTO.getMenuName(),
                sortSeq,
                menuItemDTO.getItemClass(),
                menuItemDTO.getParent());

        menuItemRepository.save(menuItem);
        return menuItem.getId();
    }

    private Long getSortSeq(MenuItemDTO menuItemDTO) {
        long result;
        if(isMasterItem(menuItemDTO)) {
            result = menuItemRepository.getMasterMaxSortSeq() + 1;
        } else {
            result = menuItemDTO.getParent().getChild().size() + 1L;
        }

        return result;
    }

    private boolean isMasterItem(MenuItemDTO menuItemDTO) {
        return menuItemDTO.getItemClass().equals(ItemClass.MASTER);
    }

    public MenuItem getMenuItem(Long id) {
        return menuItemRepository.findOne(id);
    }
}