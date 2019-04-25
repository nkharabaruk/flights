package com.chartermatch.flights.rest;

import com.chartermatch.flights.domain.Flight;
import com.chartermatch.flights.service.CsvReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.*;
import java.util.Comparator;
import java.util.stream.Stream;

@RestController
public class FlightController {

    private final CsvReader csvReader;

    @Autowired
    public FlightController(CsvReader csvReader) {
        this.csvReader = csvReader;
    }

    @CrossOrigin
    @GetMapping("flights")
    public Stream<Flight> getFlights(@RequestParam long timestamp) {
        OffsetDateTime dateTime = OffsetDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneOffset.UTC);
        DayOfWeek dayOfWeek = dateTime.getDayOfWeek();
        LocalTime localTime = LocalTime.from(dateTime);
        return csvReader.readFlightsCsv("flights.csv")
                .filter(flight -> flight.getDaysOfWeek().contains(dayOfWeek))
                .sorted(Comparator.comparing(Flight::getDepartureTime))
                .peek(flight -> flight.setDeparted(flight.getDepartureTime().isBefore(localTime)));
    }
}
