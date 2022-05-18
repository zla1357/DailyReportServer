package com.mycompany.dailyreport.controller;

import com.mycompany.dailyreport.domain.Report;
import com.mycompany.dailyreport.domain.dto.ReportDTO;
import com.mycompany.dailyreport.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @PostMapping("report")
    @ResponseBody
    public String registerReport(ReportDTO reportDTO) {

        Report report = new Report(reportDTO.getAuthor(), reportDTO.getContent());
        reportService.registerReport(report);

        return "OK";
    }

    @GetMapping("report")
    @ResponseBody
    public Report getReport(Long id) {
        return reportService.getReport(id);
    }

    @GetMapping("reports")
    @ResponseBody
    public List<Report> getReportsInPeriod(String startDate, String endDate) {
        return reportService.searchReportsInPeriod(startDate, endDate);
    }
}
