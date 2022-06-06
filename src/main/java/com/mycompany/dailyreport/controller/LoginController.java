package com.mycompany.dailyreport.controller;

import com.mycompany.dailyreport.domain.Member;
import com.mycompany.dailyreport.domain.dto.LoginDTO;
import com.mycompany.dailyreport.domain.dto.MemberDTO;
import com.mycompany.dailyreport.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @PostMapping("/login")
    @ResponseBody
    public MemberDTO login(@RequestBody LoginDTO loginDTO) {
        MemberDTO loginMemberDTO = new MemberDTO();
        loginMemberDTO.setAccountId(loginDTO.getAccountId());
        loginMemberDTO.setPassword(loginDTO.getPassword());

        Member loginMember = loginService.login(new Member(loginMemberDTO));
        return MemberDTO.from(loginMember);
    }
}
