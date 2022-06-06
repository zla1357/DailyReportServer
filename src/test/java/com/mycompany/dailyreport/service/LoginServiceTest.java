package com.mycompany.dailyreport.service;

import com.mycompany.dailyreport.domain.Member;
import com.mycompany.dailyreport.domain.dto.MemberDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class LoginServiceTest {

    @Autowired
    LoginService loginService;

    @Autowired
    MemberService memberService;

    @Test
    public void 로그인_성공() throws Exception {
        // given
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setName("김효중");
        memberDTO.setAccountId("zla1275");
        memberDTO.setPassword("password");

        Member member = new Member(memberDTO);
        memberService.registerMember(member);

        // when
        Member loginMember = loginService.login(member);

        // then
        assertThat(loginMember.getAccountId()).isEqualTo(member.getAccountId());
    }

    @Test
    public void 계정정보_불일치() throws Exception {
        // given
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setName("김효중");
        memberDTO.setAccountId("zla1275");
        memberDTO.setPassword("password");

        Member member = new Member(memberDTO);

        // when
        memberService.registerMember(member);

        // then
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {

            MemberDTO loginDTO = new MemberDTO();
            loginDTO.setName("김효중");
            loginDTO.setAccountId("zla1234");
            loginDTO.setPassword("password");

            Member loginInfo = new Member(loginDTO);

            Member loginMember = loginService.login(loginInfo);
        });

        assertThat(thrown.getMessage()).isEqualTo("가입된 ID가 없습니다.");
    }

    @Test
    public void 비밀번호_불일치() throws Exception {
        // given
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setName("김효중");
        memberDTO.setAccountId("zla1275");
        memberDTO.setPassword("password");

        Member member = new Member(memberDTO);

        // when
        memberService.registerMember(member);

        // then
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {

            MemberDTO loginDTO = new MemberDTO();
            loginDTO.setName("김효중");
            loginDTO.setAccountId("zla1275");
            loginDTO.setPassword("pass");

            Member loginInfo = new Member(loginDTO);

            Member loginMember = loginService.login(loginInfo);
        });

        assertThat(thrown.getMessage()).isEqualTo("비밀번호가 일치하지 않습니다.");
    }
}