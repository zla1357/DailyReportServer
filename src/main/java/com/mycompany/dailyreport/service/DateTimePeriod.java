package com.mycompany.dailyreport.service;

import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class DateTimePeriod {

    private final LocalDateTime startDate;
    private final LocalDateTime endDate;

    public DateTimePeriod(String startDate, String endDate) {
        this.startDate = stringToDateTime(startDate);
        this.endDate = stringToDateTime(endDate);
    }

    private LocalDateTime stringToDateTime(String dtString) {
        return LocalDate.parse(dtString).atStartOfDay();
    }
}
