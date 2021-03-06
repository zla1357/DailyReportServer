package com.mycompany.dailyreport.domain;

import com.mycompany.dailyreport.domain.dto.ReportDTO;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Report extends CommonField {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
    private String content;

    public Report(Member member, String content) {
        this.member = member;
        this.content = content;
        super.inputDate = LocalDateTime.now();
        super.updateDate = LocalDateTime.now();
    }

    public void modifyReport(String content) {
        this.content = content;
        this.updateDate = LocalDateTime.now();
    }
}
