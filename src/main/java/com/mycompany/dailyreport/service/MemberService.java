package com.mycompany.dailyreport.service;

import com.mycompany.dailyreport.domain.Member;
import com.mycompany.dailyreport.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Long registerMember(Member member) {
        memberRepository.save(member);

        return member.getId();
    }

    public Member getMember(Long id) {
        return memberRepository.findOne(id);
    }
}
