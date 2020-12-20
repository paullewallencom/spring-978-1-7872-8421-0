package com.tomekl007.packt.booking;

import com.tomekl007.packt.configuration.BookingServiceSettings;
import com.tomekl007.packt.model.Travel;
import com.tomekl007.packt.repository.TravelRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class FlightBookingService implements BookingService {
    static Logger log = Logger.getLogger(FlightBookingService.class.getName());

    private final BookingServiceSettings bookingServiceSettings;
    private final TravelRepository travelRepository;

    @Autowired
    public FlightBookingService(BookingServiceSettings bookingServiceSettings,
                                TravelRepository travelRepository) {
        this.bookingServiceSettings = bookingServiceSettings;
        this.travelRepository = travelRepository;
    }

    @Override
    public boolean book(Travel travel) {
        if (bookingServiceSettings.getSupportedDestinations().contains(travel.getDestination())) {
            travelRepository.save(travel);
            return true;
        }
        return false;
    }

    @PostConstruct
    public void init() {
        log.info("in init method");
    }

    @PreDestroy
    public void cleanup() {
        log.info("in cleanup method. Possible to release some resources");
    }
}
