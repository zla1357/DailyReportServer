package com.mycompany.dailyreport.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ReportDTO {

    private Long id;
    private Long memberId;
    private String content;
    private LocalDateTime inputDate;
    private LocalDateTime updateDate;
}
