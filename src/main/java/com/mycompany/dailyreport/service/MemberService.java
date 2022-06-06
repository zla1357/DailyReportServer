package com.mycompany.dailyreport.service;

import com.mycompany.dailyreport.domain.Member;
import com.mycompany.dailyreport.domain.dto.MemberDTO;
import com.mycompany.dailyreport.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Long registerMember(Member member) {

        validationAccount(member);
        memberRepository.save(member);

        return member.getId();
    }

    public void validationAccount(Member member) {
        List<Member> findMembers = memberRepository.findByAccountId(member.getAccountId());
        if(findMembers.size() > 0) {
            throw new IllegalArgumentException("중복된 계정명입니다.");
        }
    }

    public Member getMember(Long id) {
        return memberRepository.findOne(id);
    }

    public Long modifyMemberInfo(MemberDTO memberDTO) {

        Member member = memberRepository.findOne(memberDTO.getId());
        member.changeMemberInfo(memberDTO);

        memberRepository.save(member);

        return memberDTO.getId();
    }
}
