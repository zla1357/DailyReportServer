package com.mycompany.dailyreport.domain;

import com.mycompany.dailyreport.domain.dto.MemberDTO;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends CommonField {

    @Id
    @GeneratedValue
    private Long id;
    private String name;

    public Member(MemberDTO memberDTO) {
        this.name = memberDTO.getName();
        super.inputDate = LocalDateTime.now();
        super.updateDate = LocalDateTime.now();
    }
}