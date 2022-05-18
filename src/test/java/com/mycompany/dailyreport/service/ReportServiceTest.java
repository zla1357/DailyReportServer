package com.mycompany.dailyreport.service;

import com.mycompany.dailyreport.domain.Report;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class ReportServiceTest {

    @Autowired
    ReportService reportService;
    @Autowired
    EntityManager em;

    @Test
    public void 업무일지_등록() throws Exception {
        // given
        Report report = new Report("김효중", "일했다");

        // when
        Long findId = reportService.registerReport(report);

        // then
        Report findReport = reportService.getReport(findId);
        assertThat(findReport).isEqualTo(report);
    }

    @Test
    public void 기간내_업무일지_조회() throws Exception {
        // given
        Report report = new Report("김효중", "일했다");
        Long saveReportId = reportService.registerReport(report);

        // when
        LocalDate endDate = LocalDate.now().plusDays(1);

        String startPeriod = LocalDateTime.now().getYear() + "-" +
                             LocalDateTime.now().getMonthValue() + "-" +
                             LocalDateTime.now().getDayOfMonth();

        String endPeriod = endDate.getYear() + "-" +
                           endDate.getMonthValue() + "-" +
                           endDate.getDayOfMonth();

        List<Report> reports = reportService.searchReportsInPeriod(startPeriod, endPeriod);

        // then
        assertThat(reports.contains(report)).isEqualTo(true);
    }
}