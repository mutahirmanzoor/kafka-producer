package com.mutahir.kafkaproducer.service;


import com.mutahir.kafkaproducer.model.DriverLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

@Service
public class LocationPublisherService {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Value("${kafka.topic.driver-location}")
    private String topic;

    private ObjectMapper mapper = new ObjectMapper();

    public void publishLocation(DriverLocation driverLocation) {

        try{
            String key = driverLocation.getDriverId();
            String value = mapper.writeValueAsString(driverLocation);
            kafkaTemplate.send(topic,key,value);

        }
        catch (Exception e){
            throw new RuntimeException("Exception Occured while Sending the Topic");
        }
    }
}
