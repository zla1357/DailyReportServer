package com.mycompany.dailyreport.domain;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class CommonField {

    protected LocalDateTime inputDate;
    protected LocalDateTime updateTime;
}
