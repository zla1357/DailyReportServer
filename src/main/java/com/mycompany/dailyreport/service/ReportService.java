package com.mycompany.dailyreport.service;

import com.mycompany.dailyreport.domain.Report;
import com.mycompany.dailyreport.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
