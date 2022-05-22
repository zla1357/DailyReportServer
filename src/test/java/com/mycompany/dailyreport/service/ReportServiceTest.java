package com.mycompany.dailyreport.service;

import com.mycompany.dailyreport.domain.Member;
import com.mycompany.dailyreport.domain.ModelMapperUtils;
import com.mycompany.dailyreport.domain.Report;
import com.mycompany.dailyreport.domain.dto.MemberDTO;
import com.mycompany.dailyreport.domain.dto.ReportDTO;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
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
    private ReportService reportService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private EntityManager em;

    private final  ModelMapper modelMapper = new ModelMapper();

    @Test
    public void 업무일지_등록() throws Exception {
        // given

        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setName("김효중");
        Member member = modelMapper.map(memberDTO, Member.class);
        memberService.registerMember(member);

        ReportDTO reportDTO = new ReportDTO();
        reportDTO.setContent("일했다");
        reportDTO.setMember(memberDTO);
        Report report = new Report(member, reportDTO.getContent());

        // when
        Long registerReportId = reportService.registerReport(report);

        // then
        Report findReport = reportService.getReport(registerReportId);
        assertThat(findReport).isEqualTo(report);
    }

    @Test
    public void 기간내_업무일지_조회() throws Exception {
        // given
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setName("김효중");
        Member member = ModelMapperUtils.getModelMapper().map(memberDTO, Member.class);
        memberService.registerMember(member);

        ReportDTO reportDTO = new ReportDTO();
        reportDTO.setContent("일했다");
        reportDTO.setMember(memberDTO);

        Report report = new Report(member, reportDTO.getContent());
        reportService.registerReport(report);

        // when
        String startPeriod = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        String endPeriod = LocalDateTime.now().plusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        List<Report> reports = reportService.searchReportsInPeriod(startPeriod, endPeriod);

        // then
        assertThat(reports.contains(report)).isEqualTo(true);
    }

    @Test
    public void 업무일지_수정() throws Exception {
        // given

        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setName("김효중");
        Member member = new Member(memberDTO);
        memberService.registerMember(member);

        ReportDTO reportDTO = new ReportDTO();
        reportDTO.setContent("일했다");
        reportDTO.setMember(memberDTO);

        Long saveId = reportService.registerReport(new Report(member, reportDTO.getContent()));
        Report report1 = reportService.getReport(saveId);
        em.flush();
        em.clear();

        ReportDTO reportDTO2 = new ReportDTO();
        reportDTO.setContent("잘했다");

        // when
        Long modifyId = reportService.modifyReport(saveId, reportDTO2);
        Report report2 = reportService.getReport(modifyId);

        // then
        assertThat(report1.getId()).isEqualTo(report2.getId());
        assertThat(report1.getContent()).isNotEqualTo(report2.getContent());
    }
}