package com.mycompany.dailyreport.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    public MenuItem(String menuName, Long sortSeq, ItemClass itemClass, MenuItem parent) {
        this.menuName = menuName;
        this.sortSeq = sortSeq;
        this.itemClass = itemClass;
        this.parent = parent;
        this.child = new ArrayList<>();
    }
}
