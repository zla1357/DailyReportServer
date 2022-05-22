package com.mycompany.dailyreport.domain.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReportDTO {

    private Long id;
    private Long memberId;
    private String content;
    private LocalDateTime inputDate;
    private LocalDateTime updateDate;
}
