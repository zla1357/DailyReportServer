package com.mycompany.dailyreport.repository;

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
class MenuItemRepositoryTest {

    @Autowired
    MenuItemRepository menuItemRepository;
    
    @Test
    public void 메뉴아이템_등록() throws Exception {
        // given
        MenuItem menuItem = new MenuItem(new MenuItemDTO(null, "업무일지", 1L, null, ItemClass.MASTER, null));
        
        // when
        menuItemRepository.save(menuItem);
        MenuItem savedItem = menuItemRepository.findOne(menuItem.getId());

        // then
        assertThat(savedItem.getMenuName()).isEqualTo("업무일지");
        assertThat(savedItem.getItemClass()).isEqualTo(ItemClass.MASTER);
        assertThat(savedItem.getSortSeq()).isEqualTo(1L);
    }
    
    @Test
    public void 순번_MAX_조회() throws Exception {

        MenuItem menuItem = new MenuItem(new MenuItemDTO(null, "업무일지", 1L, null, ItemClass.MASTER, null));
        MenuItem menuItem2 = new MenuItem(new MenuItemDTO(null, "업무일지2", 2L, null, ItemClass.MASTER, null));
        MenuItem menuItem3 = new MenuItem(new MenuItemDTO(null, "업무일지3", 3L, null, ItemClass.MASTER, null));

        // when
        menuItemRepository.save(menuItem);
        menuItemRepository.save(menuItem2);
        menuItemRepository.save(menuItem3);

        Long maxSortSeq = menuItemRepository.getMasterMaxSortSeq();
        System.out.println("maxSortSeq = " + maxSortSeq);
        // then
        assertThat(maxSortSeq).isEqualTo(3L);
    }

    @Test
    public void Master_메뉴리스트_조회() throws Exception {
        // given
        MenuItem menuItem = new MenuItem(new MenuItemDTO(null, "업무일지", 1L, null, ItemClass.MASTER, null));
        MenuItem menuItem2 = new MenuItem(new MenuItemDTO(null, "업무일지2", 2L, null, ItemClass.MASTER, null));
        MenuItem menuItem3 = new MenuItem(new MenuItemDTO(null, "업무일지3", 3L, null, ItemClass.MASTER, null));

        menuItemRepository.save(menuItem);
        menuItemRepository.save(menuItem2);
        menuItemRepository.save(menuItem3);
        List<MenuItem> items = new ArrayList<MenuItem>();
        items.add(menuItem);
        items.add(menuItem2);
        items.add(menuItem3);

        // when
        List<MenuItem> masterMenuList = menuItemRepository.getMasterMenuList();

        // then
        assertThat(items).isEqualTo(masterMenuList);
    }

    @Test
    public void Sub_메뉴리스트_조회() throws Exception {
        // given
        MenuItem masterMenuItem = new MenuItem(new MenuItemDTO(null, "업무일지", 1L, null, ItemClass.MASTER, null));
        MenuItem subMenuItem = new MenuItem(new MenuItemDTO(null, "업무일지 작성", 1L, masterMenuItem, ItemClass.SUB, null));
        MenuItem subMenuItem2 = new MenuItem(new MenuItemDTO(null, "업무일지 조회", 2L, masterMenuItem, ItemClass.SUB, null));

        menuItemRepository.save(masterMenuItem);
        menuItemRepository.save(subMenuItem);
        menuItemRepository.save(subMenuItem2);
        List<MenuItem> items = new ArrayList<MenuItem>();
        items.add(subMenuItem);
        items.add(subMenuItem2);

        // when
        List<MenuItem> subMenuList = menuItemRepository.getSubMenuList(masterMenuItem.getId());

        // then
        assertThat(items).isEqualTo(subMenuList);
    }
}