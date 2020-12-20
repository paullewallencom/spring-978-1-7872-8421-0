package com.tomekl007.packt.hystrix;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.core.MediaType;

@RestController
public class DestinationDetailsController {

    @Autowired
    private TravelDestinationsDetails travelDestinationsDetails;

    //http://localhost:8080/travel/destination-details/MADRID
    @RequestMapping(value = "/travel/destination-details/{city}", produces = MediaType.APPLICATION_JSON)
    public String getInfoAboutDestination(@PathVariable final String city) {
        return travelDestinationsDetails.getInfoAboutCity(city);
    }


}
