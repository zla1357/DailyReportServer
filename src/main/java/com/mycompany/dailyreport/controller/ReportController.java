package com.mycompany.dailyreport.controller;

import com.mycompany.dailyreport.domain.Member;
import com.mycompany.dailyreport.domain.Report;
import com.mycompany.dailyreport.domain.dto.ReportDTO;
import com.mycompany.dailyreport.service.MemberService;
import com.mycompany.dailyreport.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;
    private final MemberService memberService;
    private final ModelMapper modelMapper = new ModelMapper();

    @PostMapping("report")
    @ResponseBody
    public String registerReport(@RequestBody ReportDTO reportDTO) {

        Member registerMember = memberService.getMember(reportDTO.getMemberId());
        reportService.registerReport(new Report(reportDTO, registerMember));

        return "OK";
    }

    @GetMapping("report/{id}")
    @ResponseBody
    public ReportDTO getReport(@PathVariable("id") Long id) {

        Report report = reportService.getReport(id);

        return modelMapper.map(report, ReportDTO.class);
    }

    @GetMapping("reports")
    @ResponseBody
    public List<ReportDTO> getReportsInPeriod(@RequestParam String startDate, @RequestParam String endDate) {

        return reportService
                .searchReportsInPeriod(startDate, endDate)
                .stream()
                .map((e) -> modelMapper.map(e, ReportDTO.class))
                .collect(Collectors.toList());
    }

    @PutMapping("report/{id}")
    @ResponseBody
    public String modifyReport(@PathVariable("id") Long id, @RequestBody ReportDTO reportDTO) {

        reportService.modifyReport(id, reportDTO);

        return "OK";
    }
}
