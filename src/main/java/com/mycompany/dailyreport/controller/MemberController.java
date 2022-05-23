package com.mycompany.dailyreport.controller;

import com.mycompany.dailyreport.domain.Member;
import com.mycompany.dailyreport.domain.dto.MemberDTO;
import com.mycompany.dailyreport.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("member")
    @ResponseBody
    public String registerMember(@RequestBody MemberDTO memberDTO) {

        memberService.registerMember(new Member(memberDTO));

        return "OK";
    }

    @GetMapping("member/{id}")
    @ResponseBody
    public MemberDTO getMember(@PathVariable("id") Long id) {

        return MemberDTO.from(memberService.getMember(id));
    }
}
