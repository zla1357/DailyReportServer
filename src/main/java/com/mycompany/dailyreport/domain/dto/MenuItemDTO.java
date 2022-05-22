package com.mycompany.dailyreport.domain.dto;

import com.mycompany.dailyreport.domain.ItemClass;
import com.mycompany.dailyreport.domain.MenuItem;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuItemDTO {

    private String menuName;
    private Long sortSeq;
    private MenuItem parent;
    private ItemClass itemClass;
}
