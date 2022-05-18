package com.mycompany.dailyreport.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Report extends CommonField {

    @Id
    @GeneratedValue
    private Long id;

    private String author;
    private String content;

    public Report(String author, String content) {
        this.author = author;
        this.content = content;
        super.inputDate = LocalDateTime.now();
        super.updateDate = LocalDateTime.now();
    }
}
