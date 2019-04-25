package com.chartermatch.flights.domain;

import com.univocity.parsers.conversions.Conversion;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class LocalTimeConversion implements Conversion<String, LocalTime> {

    @Override
    public LocalTime execute(String input) {
        return LocalTime.parse(input);
    }

    @Override
    public String revert(LocalTime input) {
        return input.format(DateTimeFormatter.ofPattern("HH:mm"));
    }
}
