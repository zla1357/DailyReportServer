package com.mycompany.dailyreport.domain;

import lombok.Getter;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
public abstract class CommonField {

    protected LocalDateTime inputDate;
    protected LocalDateTime updateDate;
}
