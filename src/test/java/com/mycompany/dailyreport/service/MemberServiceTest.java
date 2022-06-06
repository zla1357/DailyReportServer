package com.mycompany.dailyreport.service;

import com.mycompany.dailyreport.domain.Member;
import com.mycompany.dailyreport.domain.dto.MemberDTO;
import org.junit.jupiter.api.Assertions;
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
        memberDTO.setAccountId("zla1275");
        memberDTO.setPassword("password");

        Member member = new Member(memberDTO);

        // when
        Long findId = memberService.registerMember(member);

        em.flush();
        em.clear();

        // then
        Member findMember = memberService.getMember(findId);
        assertThat(findMember.getId()).isEqualTo(member.getId());
        assertThat(findMember.getName()).isEqualTo(member.getName());
        assertThat(findMember.getAccountId()).isEqualTo(member.getAccountId());
        assertThat(findMember.getPassword()).isEqualTo(member.getPassword());
    }

    @Test
    public void 사용자정보_수정() throws Exception {
        // given
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setName("김효중");

        Member member1 = new Member(memberDTO);

        Long savedId = memberService.registerMember(member1);

        em.flush();
        em.clear();

        MemberDTO memberDTO2 = new MemberDTO();
        memberDTO2.setName("김효일");
        memberDTO2.setId(savedId);

        // when
        Long modifiedId = memberService.modifyMemberInfo(memberDTO2);
        Member modifiedMember = memberService.getMember(modifiedId);

        // then
        assertThat(modifiedMember.getId()).isEqualTo(member1.getId());
        assertThat(modifiedMember.getName()).isNotEqualTo(member1.getName());
    }
    
    @Test
    public void 사용자_중복_등록_방지() throws Exception {
        // given
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setName("김효중");
        memberDTO.setAccountId("zla1275");
        memberDTO.setPassword("password");

        // when
        memberService.registerMember(new Member(memberDTO));

        // then
        Assertions.assertThrows(IllegalArgumentException.class, () ->{

            MemberDTO memberDTO2 = new MemberDTO();
            memberDTO2.setName("김효중");
            memberDTO2.setAccountId("zla1275");
            memberDTO2.setPassword("password");
            memberService.registerMember(new Member(memberDTO2));
        });
    }
}