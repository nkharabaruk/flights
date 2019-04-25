package com.chartermatch.flights.domain;

import com.univocity.parsers.annotations.Convert;
import com.univocity.parsers.annotations.Parsed;
import com.univocity.parsers.annotations.Trim;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

public class Flight {

    @Trim
    @Parsed(index = 0)
    @Convert(conversionClass = LocalTimeConversion.class)
    private LocalTime departureTime;

    @Parsed(index = 1)
    private String destination;

    @Parsed(index = 2)
    private String destinationAirportIATA;

    @Parsed(index = 3)
    private String flightNo;

    private Set<DayOfWeek> daysOfWeek;

    private boolean departed;

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDestinationAirportIATA() {
        return destinationAirportIATA;
    }

    public void setDestinationAirportIATA(String destinationAirportIATA) {
        this.destinationAirportIATA = destinationAirportIATA;
    }

    public String getFlightNo() {
        return flightNo;
    }

    public void setFlightNo(String flightNo) {
        this.flightNo = flightNo;
    }

    public Set<DayOfWeek> getDaysOfWeek() {
        if (daysOfWeek == null) daysOfWeek = new HashSet<>();
        return daysOfWeek;
    }

    public void setDaysOfWeek(Set<DayOfWeek> daysOfWeek) {
        this.daysOfWeek = daysOfWeek;
    }

    public boolean isDeparted() {
        return departed;
    }

    public void setDeparted(boolean departed) {
        this.departed = departed;
    }
}
