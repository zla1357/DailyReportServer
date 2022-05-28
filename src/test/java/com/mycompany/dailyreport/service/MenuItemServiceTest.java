package com.mycompany.dailyreport.service;

import com.mycompany.dailyreport.domain.ItemClass;
import com.mycompany.dailyreport.domain.MenuItem;
import com.mycompany.dailyreport.domain.dto.MenuItemDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class MenuItemServiceTest {

    @Autowired
    private MenuItemService menuItemService;

    @Test
    public void Master_항목_저장() throws Exception {
        // given
        MenuItemDTO menuItemDTO = new MenuItemDTO(null, "메뉴1", null, null, ItemClass.MASTER);

        // when
        Long savedId = menuItemService.addMenuItem(menuItemDTO);
        MenuItem menuItem = menuItemService.getMenuItem(savedId);

        // then
        assertThat(menuItem.getItemClass()).isEqualTo(ItemClass.MASTER);
        assertThat(menuItem.getMenuName()).isEqualTo("메뉴1");
        assertThat(menuItem.getSortSeq()).isEqualTo(1L);
    }

    @Test
    public void Sub_항목_저장() throws Exception {
        // given
        MenuItemDTO masterItemDTO = new MenuItemDTO(null, "메뉴1", null, null, ItemClass.MASTER);

        Long masterId = menuItemService.addMenuItem(masterItemDTO);
        MenuItem masterItem = menuItemService.getMenuItem(masterId);

        // when
        MenuItemDTO subItemDTO = new MenuItemDTO(null, "서브메뉴", null, masterItem, ItemClass.SUB);
        Long subId = menuItemService.addMenuItem(subItemDTO);
        MenuItem subItem = menuItemService.getMenuItem(subId);

        MenuItemDTO subItemDTO2 = new MenuItemDTO(null, "서브메뉴2", null, masterItem, ItemClass.SUB);
        Long subId2 = menuItemService.addMenuItem(subItemDTO2);
        MenuItem subItem2 = menuItemService.getMenuItem(subId2);

        // then
        assertThat(subItem2.getItemClass()).isEqualTo(ItemClass.SUB);
        assertThat(subItem2.getMenuName()).isEqualTo("서브메뉴2");
        assertThat(subItem2.getSortSeq()).isEqualTo(2L);
        assertThat(subItem2.getParent()).isEqualTo(masterItem);
    }

    @Test
    public void sub_항목_조회() throws Exception {
        // given
        MenuItemDTO parentDTO = new MenuItemDTO(null, "업무일지", null, null, ItemClass.MASTER);
        Long parentId = menuItemService.addMenuItem(parentDTO);
        MenuItem parent = menuItemService.getMenuItem(parentId);

        MenuItemDTO subItemDTO = new MenuItemDTO(null, "업무일지 조회", null, parent, ItemClass.SUB);
        Long sub1Id = menuItemService.addMenuItem(subItemDTO);
        MenuItem subItem1 = menuItemService.getMenuItem(sub1Id);

        MenuItemDTO parentDTO2 = new MenuItemDTO(null, "업무일지 수정", null, parent, ItemClass.SUB);
        Long sub2Id = menuItemService.addMenuItem(parentDTO2);
        MenuItem subItem2 = menuItemService.getMenuItem(sub2Id);

        List<MenuItem> subList = new ArrayList<>();
        subList.add(subItem1);
        subList.add(subItem2);

        // when
        List<MenuItem> subMenuList = menuItemService.getSubMenuList(parent.getId());

        // then
        System.out.println("subMenuList = " + subMenuList);
        System.out.println("subList = " + subList);
        assertThat(subMenuList).isEqualTo(subList);
    }
}