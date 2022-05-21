package com.mycompany.dailyreport.service;

import com.mycompany.dailyreport.domain.Member;
import com.mycompany.dailyreport.domain.Report;
import com.mycompany.dailyreport.domain.dto.MemberDTO;
import com.mycompany.dailyreport.domain.dto.ReportDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class ReportServiceTest {

    @Autowired
    ReportService reportService;

    @Autowired
    MemberService memberService;

    @Autowired
    EntityManager em;

    @Test
    public void 업무일지_등록() throws Exception {
        // given

        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setName("김효중");
        Member member = new Member(memberDTO);
        memberService.registerMember(member);

        ReportDTO reportDTO = new ReportDTO();
        reportDTO.setContent("일했다");
        Report report = new Report(reportDTO, member);

        // when
        Long registerReportId = reportService.registerReport(report);

        // then
        Report findReport = reportService.getReport(registerReportId);
        assertThat(findReport).isEqualTo(report);
    }

    @Test
    public void 기간내_업무일지_조회() throws Exception {
        // given
        ReportDTO reportDTO = new ReportDTO();
        reportDTO.setContent("일했다");

        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setName("김효중");
        Member member = new Member(memberDTO);
        memberService.registerMember(member);

        Report report = new Report(reportDTO, member);
        Long saveReportId = reportService.registerReport(report);

        // when
        LocalDate endDate = LocalDate.now().plusDays(1);

        String startPeriod = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        String endPeriod = LocalDateTime.now().plusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        List<Report> reports = reportService.searchReportsInPeriod(startPeriod, endPeriod);

        // then
        assertThat(reports.contains(report)).isEqualTo(true);
    }

    @Test
    public void 업무일지_수정() throws Exception {
        // given
        ReportDTO reportDTO = new ReportDTO();
        reportDTO.setContent("일했다");

        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setName("김효중");
        Member member = new Member(memberDTO);
        memberService.registerMember(member);

        Long saveId = reportService.registerReport(new Report(reportDTO, member));
        Report report1 = reportService.getReport(saveId);
        em.flush();
        em.clear();

        ReportDTO reportDTO2 = new ReportDTO();
        reportDTO2.setAuthor("김효일");
        reportDTO.setContent("잘했다");

        // when
        Long modifyId = reportService.modifyReport(saveId, reportDTO2);
        Report report2 = reportService.getReport(modifyId);

        // then
        assertThat(report1).isNotEqualTo(report2);
    }
}