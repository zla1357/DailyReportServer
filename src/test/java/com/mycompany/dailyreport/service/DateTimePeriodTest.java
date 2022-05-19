package com.mycompany.dailyreport.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.*;

class DateTimePeriodTest {

    @Test
    public void 날짜_포맷_검사() throws Exception {

        DateTimeParseException e = assertThrows(DateTimeParseException.class, () -> {
            DateTimePeriod dateTimePeriod = new DateTimePeriod("20220101", "20220101");
        });
    }
}