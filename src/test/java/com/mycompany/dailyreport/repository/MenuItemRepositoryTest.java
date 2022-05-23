package com.mycompany.dailyreport.repository;

import com.mycompany.dailyreport.domain.ItemClass;
import com.mycompany.dailyreport.domain.MenuItem;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MenuItemRepositoryTest {

    @Autowired
    MenuItemRepository menuItemRepository;
    
    @Test
    public void 메뉴아이템_등록() throws Exception {
        // given
        MenuItem menuItem = new MenuItem("업무일지", 1L, ItemClass.MASTER, null);
        
        // when
        menuItemRepository.save(menuItem);
        MenuItem savedItem = menuItemRepository.findOne(menuItem.getId());

        // then
        assertThat(savedItem.getMenuName()).isEqualTo("업무일지");
        assertThat(savedItem.getItemClass()).isEqualTo(ItemClass.MASTER);
    }
    
    @Test
    public void 순번_MAX_조회() throws Exception {
        MenuItem menuItem = new MenuItem("업무일지", 1L, ItemClass.MASTER, null);
        MenuItem menuItem2 = new MenuItem("업무일지2", 2L, ItemClass.MASTER, null);
        MenuItem menuItem3 = new MenuItem("업무일지3", 3L, ItemClass.MASTER, null);

        // when
        menuItemRepository.save(menuItem);
        menuItemRepository.save(menuItem2);
        menuItemRepository.save(menuItem3);

        Long maxSortSeq = menuItemRepository.getMasterMaxSortSeq();
        System.out.println("maxSortSeq = " + maxSortSeq);
        // then
        assertThat(maxSortSeq).isEqualTo(3L);
    }
}