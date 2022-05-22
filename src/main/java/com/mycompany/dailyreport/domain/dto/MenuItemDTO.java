package com.mycompany.dailyreport.domain.dto;

import com.mycompany.dailyreport.domain.ItemClass;
import com.mycompany.dailyreport.domain.MenuItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MenuItemDTO {

    private String menuName;
    private Long sortSeq;
    private MenuItem parent;
    private ItemClass itemClass;
}
