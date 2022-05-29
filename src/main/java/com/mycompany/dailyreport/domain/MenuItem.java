package com.mycompany.dailyreport.domain;

import com.mycompany.dailyreport.domain.dto.MenuItemDTO;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MenuItem extends CommonField {

    @Id
    @GeneratedValue
    private Long id;

    private String menuName;
    private Long sortSeq;

    @Enumerated(EnumType.STRING)
    private ItemClass itemClass;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private MenuItem parent;

    @OneToMany(mappedBy = "parent")
    private List<MenuItem> child = new ArrayList<>();

    @Embedded
    private ItemContent itemContent;

    public MenuItem(MenuItemDTO menuItemDTO) {
        this.menuName = menuItemDTO.getMenuName();
        this.sortSeq = menuItemDTO.getSortSeq();
        this.itemClass = menuItemDTO.getItemClass();
        this.parent = menuItemDTO.getParent();
        this.child = new ArrayList<>();
        this.itemContent = menuItemDTO.getItemContent();
        super.inputDate = LocalDateTime.now();
        super.updateDate = LocalDateTime.now();

        if(parent != null) {
            parent.getChild().add(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MenuItem menuItem = (MenuItem) o;
        return Objects.equals(getId(), menuItem.getId()) && Objects.equals(getMenuName(), menuItem.getMenuName()) && Objects.equals(getSortSeq(), menuItem.getSortSeq()) && getItemClass() == menuItem.getItemClass() && Objects.equals(getParent(), menuItem.getParent()) && Objects.equals(getChild(), menuItem.getChild());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getMenuName(), getSortSeq(), getItemClass(), getParent(), getChild());
    }
}
