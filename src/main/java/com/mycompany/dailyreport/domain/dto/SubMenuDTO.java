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
public class SubMenuDTO {

    private Long id;
    private String menuName;
    private Long sortSeq;
    private ItemClass itemClass;
    private ItemContent itemContent;

    public static SubMenuDTO from(MenuItem menuItem) {
        return ModelMapperUtils.getModelMapper().map(menuItem, SubMenuDTO.class);
    }
}
