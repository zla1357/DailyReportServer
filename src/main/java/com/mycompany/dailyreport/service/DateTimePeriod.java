package com.mycompany.dailyreport.service;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class DateTimePeriod {

    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public DateTimePeriod(String startDate, String endDate) {
        this.startDate = stringToDateTime(startDate);
        this.endDate = stringToDateTime(endDate);
    }

    private LocalDateTime stringToDateTime(String dtString) {
        return LocalDate.parse(dtString).atStartOfDay();
    }
}
