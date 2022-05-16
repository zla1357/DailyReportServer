package com.mycompany.dailyreport.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Report {

    @Id
    @GeneratedValue
    public Long id;

    public String author;
    public String content;
}
