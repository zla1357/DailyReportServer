package com.mycompany.dailyreport.domain.dto;

import com.mycompany.dailyreport.domain.ItemClass;
import com.mycompany.dailyreport.domain.ItemContent;
import com.mycompany.dailyreport.domain.MenuItem;
import com.mycompany.dailyreport.domain.ModelMapperUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MenuItemDTO {

    private Long id;
    private String menuName;
    private Long sortSeq;
    private MenuItem parent;
    private ItemClass itemClass;
    private ItemContent itemContent;

    public static MenuItemDTO from(MenuItem menuItem) {
        return ModelMapperUtils.getModelMapper().map(menuItem, MenuItemDTO.class);
    }
}
