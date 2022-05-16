package com.mycompany.dailyreport.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Report {

    @Id
    @GeneratedValue
    private Long id;

    private String author;
    private String content;

    public Report(Long id, String author, String content) {
        this.id = id;
        this.author = author;
        this.content = content;
    }
}
