package com.mycompany.dailyreport.controller;

import com.mycompany.dailyreport.domain.Report;
import com.mycompany.dailyreport.domain.dto.ReportDTO;
import com.mycompany.dailyreport.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @PostMapping("report/new")
    public String registerReport(ReportDTO reportDTO) {

        Report report = new Report(reportDTO.getAuthor(), reportDTO.getContent());
        reportService.registerReport(report);

        return "OK";
    }
}
