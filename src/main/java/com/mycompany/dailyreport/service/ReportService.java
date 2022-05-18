package com.mycompany.dailyreport.service;

import com.mycompany.dailyreport.domain.Report;
import com.mycompany.dailyreport.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReportService {

    private final ReportRepository reportRepository;

    @Transactional
    public Long registerReport(Report report) {
        reportRepository.save(report);

        return report.getId();
    }

    public Report getReport(Long reportId) {
        return reportRepository.findOne(reportId);
    }

    public List<Report> searchReportsInPeriod(String startDate, String endDate) {
        LocalDate periodStart = LocalDate.of(Integer.parseInt(startDate.split("-")[0]), Integer.parseInt(startDate.split("-")[1]), Integer.parseInt(startDate.split("-")[2]));
        LocalDate periodEnd = LocalDate.of(Integer.parseInt(endDate.split("-")[0]), Integer.parseInt(endDate.split("-")[1]), Integer.parseInt(endDate.split("-")[2]));

        return reportRepository.findWithPeriod(periodStart.atStartOfDay(), periodEnd.atStartOfDay());
    }
}
