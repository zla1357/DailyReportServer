package com.mycompany.dailyreport.domain.dto;

import com.mycompany.dailyreport.domain.ModelMapperUtils;
import com.mycompany.dailyreport.domain.Report;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ReportDTO {

    private Long id;
    private MemberDTO member;
    private String content;
    private LocalDateTime inputDate;
    private LocalDateTime updateDate;

    public static ReportDTO from(Report report) {
        return ModelMapperUtils.getModelMapper().map(report, ReportDTO.class);
    }
}
