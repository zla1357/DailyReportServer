package com.mycompany.dailyreport.repository;

import com.mycompany.dailyreport.domain.ItemClass;
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
        return em.createQuery(
                "select i " +
                        "from MenuItem i " +
                        "left join fetch i.parent " +
                        "where i.id = :id", MenuItem.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    public Long getMasterMaxSortSeq() {
        return em.createQuery(
                "select case when max(i.sortSeq) is null then 0L else max(i.sortSeq) end " +
                        "from MenuItem i " +
                        "where i.itemClass = :itemClass", Long.class)
                .setParameter("itemClass", ItemClass.MASTER)
                .getSingleResult();
    }

    public Long getSubMaxSortSeq(MenuItem parent) {
        return em.createQuery(
                        "select case when max(i.sortSeq) is null then 0L else max(i.sortSeq) end " +
                                "from MenuItem i " +
                                "where i.parent = :parent " +
                                "and i.itemClass = :itemClass", Long.class)
                .setParameter("parent", parent)
                .setParameter("itemClass", ItemClass.SUB)
                .getSingleResult();
    }

    public List<MenuItem> getMasterMenuList() {
        return em.createQuery(
                "select i " +
                        "from MenuItem i " +
                        "where i.itemClass = :itemClass", MenuItem.class)
                .setParameter("itemClass", ItemClass.MASTER)
                .getResultList();
    }

    public List<MenuItem> getSubMenuList(MenuItem parent) {
        return em.createQuery(
                "select i " +
                        "from MenuItem i " +
                        "where i.itemClass = :itemClass " +
                        "and i.parent = :parent", MenuItem.class)
                .setParameter("itemClass", ItemClass.SUB)
                .setParameter("parent", parent)
                .getResultList();
    }
}
