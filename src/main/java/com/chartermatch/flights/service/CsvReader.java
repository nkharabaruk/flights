package com.chartermatch.flights.service;

import com.chartermatch.flights.domain.Flight;

import java.util.stream.Stream;

public interface CsvReader {

    Stream<Flight> readFlightsCsv(String relativePath);
}
