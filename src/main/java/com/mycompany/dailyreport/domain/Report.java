package com.mycompany.dailyreport.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Report {

    @Id
    @GeneratedValue
    public Long id;

    public String author;
    public String content;
}
