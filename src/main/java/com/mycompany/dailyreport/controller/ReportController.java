package com.mycompany.dailyreport.controller;

import com.mycompany.dailyreport.domain.Report;
import com.mycompany.dailyreport.domain.dto.ReportDTO;
import com.mycompany.dailyreport.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @PostMapping("report")
    @ResponseBody
    public String registerReport(@RequestBody ReportDTO reportDTO) {
        reportService.registerReport(new Report(reportDTO));

        return "OK";
    }

    @GetMapping("report/{id}")
    @ResponseBody
    public Report getReport(@PathVariable("id") Long id) {
        return reportService.getReport(id);
    }

    @GetMapping("reports")
    @ResponseBody
    public List<Report> getReportsInPeriod(@RequestParam String startDate, @RequestParam String endDate) {
        return reportService.searchReportsInPeriod(startDate, endDate);
    }

    @PostMapping("report/modify/{id}")
    @ResponseBody
    public String modifyReport(@PathVariable("id") Long id, @RequestBody ReportDTO reportDTO) {

        reportService.modifyReport(id, reportDTO);

        return "OK";
    }
}
