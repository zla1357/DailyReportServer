package com.mycompany.dailyreport.service;

import com.mycompany.dailyreport.domain.Report;
import com.mycompany.dailyreport.domain.dto.ReportDTO;
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

    public List<Report> searchReportsInPeriod(String startDate, String endDate) {
        return reportRepository.findWithPeriod(new DateTimePeriod(startDate, endDate));
    }

    @Transactional
    public Long modifyReport(Long id, ReportDTO reportDTO) {

        Report report = reportRepository.findOne(id);
        report.modifyReport(reportDTO.getAuthor(), reportDTO.getContent());

        reportRepository.save(report);

        return id;
    }
}
