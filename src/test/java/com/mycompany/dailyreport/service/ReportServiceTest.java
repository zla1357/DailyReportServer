package com.mycompany.dailyreport.service;

import com.mycompany.dailyreport.domain.Report;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

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
        Assertions.assertThat(findReport).isEqualTo(report);
    }
}