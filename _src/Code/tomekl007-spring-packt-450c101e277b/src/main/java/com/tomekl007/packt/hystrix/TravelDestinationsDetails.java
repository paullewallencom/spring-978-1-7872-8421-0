package com.tomekl007.packt.hystrix;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Component
public class TravelDestinationsDetails {

    //call for info about a capitol
    //https://restcountries.eu/rest/v2/capital/MADRID
    //http://localhost:8080/travel/destination-details/BARCELONA - result in close circuit
    private final RestTemplate restTemplate;

    public TravelDestinationsDetails(RestTemplate rest) {
        this.restTemplate = rest;
    }

    @HystrixCommand(fallbackMethod = "reliable")
    public String getInfoAboutCity(String cityName) {
        URI uri = URI.create("https://restcountries.eu/rest/v2/capital/" + cityName);

        return this.restTemplate.getForObject(uri, String.class);
    }

    public String reliable(String cityName) {
        return "Info about city: " + cityName + " is not currently not available";
    }
}
