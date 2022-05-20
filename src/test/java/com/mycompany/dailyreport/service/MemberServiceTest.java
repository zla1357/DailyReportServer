package com.mycompany.dailyreport.service;

import com.mycompany.dailyreport.domain.Member;
import com.mycompany.dailyreport.domain.Report;
import com.mycompany.dailyreport.domain.dto.MemberDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    EntityManager em;

    @Test
    public void 사용자_등록() throws Exception {
        // given
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setName("김효중");
        Member member = new Member(memberDTO);

        // when
        Long findId = memberService.registerMember(member);

        // then
        Member findMember = memberService.getMember(findId);
        assertThat(findMember).isEqualTo(member);
    }
}