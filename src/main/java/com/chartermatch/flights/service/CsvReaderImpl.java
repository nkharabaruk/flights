package com.chartermatch.flights.service;

import com.chartermatch.flights.domain.Flight;
import com.univocity.parsers.common.processor.core.Processor;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import com.univocity.parsers.csv.UnescapedQuoteHandling;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.stream.Stream;

@Service
public class CsvReaderImpl implements CsvReader {

    @Override
    public Stream<Flight> readFlightsCsv(String relativePath) {
        FlightBeanConversionProcessor entryProcessor = new FlightBeanConversionProcessor();
        parseFile(relativePath, entryProcessor);
        return entryProcessor.getBeans().stream();
    }

    private void parseFile(String relativePath, Processor<?> entryProcessor) {
        CsvParserSettings settings = createSettings(entryProcessor);
        CsvParser parser = new CsvParser(settings);
        File csvFile = readFile(relativePath);
        parser.parse(csvFile);
    }

    private File readFile(String relativePath) {
        File csvFile = null;
        try {
            csvFile = new ClassPathResource(relativePath).getFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return csvFile;
    }

    private CsvParserSettings createSettings(Processor<?> entryProcessor) {
        CsvParserSettings settings = new CsvParserSettings();
        settings.setDelimiterDetectionEnabled(true);
        settings.setIgnoreLeadingWhitespaces(true);
        settings.setReadInputOnSeparateThread(false);
        settings.setUnescapedQuoteHandling(UnescapedQuoteHandling.STOP_AT_CLOSING_QUOTE);
        settings.setNumberOfRowsToSkip(1);
        settings.setProcessor(entryProcessor);
        return settings;
    }
}
