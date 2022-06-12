package com.mycompany.dailyreport.controller;

import com.mycompany.dailyreport.domain.Member;
import com.mycompany.dailyreport.domain.Report;
import com.mycompany.dailyreport.domain.dto.ReportDTO;
import com.mycompany.dailyreport.service.MemberService;
import com.mycompany.dailyreport.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;
    private final MemberService memberService;

    @PostMapping("report")
    @ResponseBody
    public ReportDTO registerReport(@RequestBody ReportDTO reportDTO) {

        Member member = memberService.getMember(reportDTO.getMember().getId());
        Report report = new Report(member, reportDTO.getContent());

        Report resultReport = reportService.getReport(reportService.registerReport(report));

        return ReportDTO.from(resultReport);
    }

    @GetMapping("report/{id}")
    @ResponseBody
    public ReportDTO getReport(@PathVariable("id") Long id) {

        return ReportDTO.from(reportService.getReport(id));
    }

    @GetMapping("reports")
    @ResponseBody
    public List<ReportDTO> getReportsInPeriod(@RequestParam String startDate, @RequestParam String endDate) {

        return reportService
                .searchReportsInPeriod(startDate, endDate)
                .stream()
                .map(ReportDTO::from)
                .collect(Collectors.toList());
    }

    @PutMapping("report/{id}")
    @ResponseBody
    public ReportDTO modifyReport(@PathVariable("id") Long id, @RequestBody ReportDTO reportDTO) {

        Long modifyReport = reportService.modifyReport(id, reportDTO);

        return ReportDTO.from(reportService.getReport(modifyReport));
    }
}
