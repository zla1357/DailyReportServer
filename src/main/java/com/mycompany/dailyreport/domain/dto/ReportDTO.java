package com.mycompany.dailyreport.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ReportDTO {

    private String author;
    private Long memberId;
    private String content;
    private LocalDate inputDate;
    private LocalDate updateDate;
}
