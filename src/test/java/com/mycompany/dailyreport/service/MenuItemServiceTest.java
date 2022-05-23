package com.mycompany.dailyreport.service;

import com.mycompany.dailyreport.domain.ItemClass;
import com.mycompany.dailyreport.domain.MenuItem;
import com.mycompany.dailyreport.domain.dto.MenuItemDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class MenuItemServiceTest {

    @Autowired
    private MenuItemService menuItemService;

    @Test
    public void 메뉴항목_저장() throws Exception {
        // given
        MenuItemDTO menuItemDTO = new MenuItemDTO();
        menuItemDTO.setMenuName("메뉴1");
        menuItemDTO.setItemClass(ItemClass.MASTER);

        // when
        Long savedId = menuItemService.addMenuItem(menuItemDTO);
        System.out.println("savedId = " + savedId);
        MenuItem menuItem = menuItemService.getMenuItem(savedId);

        // then
        assertThat(menuItem.getId()).isEqualTo(1L);
        assertThat(menuItem.getItemClass()).isEqualTo(ItemClass.MASTER);
        assertThat(menuItem.getMenuName()).isEqualTo("메뉴1");
        assertThat(menuItem.getSortSeq()).isEqualTo(1L);
    }
}