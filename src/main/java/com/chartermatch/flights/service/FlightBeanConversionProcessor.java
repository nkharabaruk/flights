package com.chartermatch.flights.service;

import com.chartermatch.flights.domain.Flight;
import com.univocity.parsers.common.ParsingContext;
import com.univocity.parsers.common.processor.BeanListProcessor;
import com.univocity.parsers.common.processor.core.BeanConversionProcessor;
import com.univocity.parsers.common.processor.core.Processor;

import java.time.DayOfWeek;
import java.util.List;

public class FlightBeanConversionProcessor extends BeanConversionProcessor<Flight> implements Processor<ParsingContext> {

    private final BeanListProcessor<Flight> processor = new BeanListProcessor<>(Flight.class);

    public FlightBeanConversionProcessor() {
        super(Flight.class);
    }

    @Override
    public void processStarted(ParsingContext context) {
        processor.processStarted(context);
        super.initialize(context.headers());
    }

    @Override
    public void rowProcessed(String[] row, ParsingContext context) {
        Flight flight = createBean(row, context);
        if (flight != null) {
            for (int i = 4; i < 11; i++) {
                if ("x".equals(row[i])) {
                    int dayNum = i - 4 == 0 ? 7 : i - 4; //start week from Sunday
                    flight.getDaysOfWeek().add(DayOfWeek.of(dayNum));
                }
            }
        }
        processor.beanProcessed(flight, context);
    }

    @Override
    public void processEnded(ParsingContext context) {
        processor.processEnded(context);
    }

    public List<Flight> getBeans() {
        return processor.getBeans();
    }
}
