package com.mycompany.dailyreport.service;

import com.mycompany.dailyreport.domain.Member;
import com.mycompany.dailyreport.repository.LoginRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final LoginRepository loginRepository;

    public Member login(Member member) {

        List<Member> loginMember = loginRepository.login(member.getAccountId());
        loginValidation(loginMember, member.getPassword());

        return loginMember.get(0);
    }

    private void loginValidation(List<Member> loginMember, String password) {
        if(loginMember.size() < 1) {
            throw new IllegalArgumentException("가입된 ID가 없습니다.");
        }
        if(!password.equals(loginMember.get(0).getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
    }
}
