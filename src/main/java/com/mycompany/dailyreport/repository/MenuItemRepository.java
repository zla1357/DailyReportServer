package com.mycompany.dailyreport.repository;

import com.mycompany.dailyreport.domain.MenuItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MenuItemRepository {

    private final EntityManager em;

    public void save(MenuItem menuItem) {
        em.persist(menuItem);
    }

    public MenuItem findOne(Long id) {
        return em.find(MenuItem.class, id);
    }
}
