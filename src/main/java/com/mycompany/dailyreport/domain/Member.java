package com.mycompany.dailyreport.domain;

import com.mycompany.dailyreport.domain.dto.MemberDTO;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends CommonField {

    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @Column(unique = true)
    private String accountId;

    private String password;

    public Member(MemberDTO memberDTO) {
        this.name = memberDTO.getName();
        this.accountId = memberDTO.getAccountId();
        this.password = memberDTO.getPassword();
        super.inputDate = LocalDateTime.now();
        super.updateDate = LocalDateTime.now();
    }

    public void changeMemberInfo(MemberDTO memberDTO) {
        this.name = memberDTO.getName();
        super.updateDate = LocalDateTime.now();
    }
}
