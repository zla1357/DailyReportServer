package com.mycompany.dailyreport.service;

import com.mycompany.dailyreport.domain.MenuItem;
import com.mycompany.dailyreport.repository.MenuItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MenuItemService {

    private final MenuItemRepository menuItemRepository;

    public Long addMenuItem(MenuItem menuItem) {

        menuItemRepository.save(menuItem);
        return menuItem.getId();
    }
}
