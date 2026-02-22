package com.mutahir.kafkaproducer.controller;


import com.mutahir.kafkaproducer.model.DriverLocation;
import com.mutahir.kafkaproducer.service.LocationPublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LocationController {

    @Autowired
    private LocationPublisherService locationPublisherService;

    public String updateLocation(@RequestBody DriverLocation driverLocation){
        locationPublisherService.publishLocation(driverLocation);
        return "Location update published successfully";
    }
}
