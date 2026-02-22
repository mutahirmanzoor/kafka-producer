package com.mutahir.kafkaproducer.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.mutahir.kafkaproducer.model.DriverLocation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class LocationPublisherService {

    private final KafkaTemplate<String , Object> kafkaTemplate;

    @Value("${spring.kafka.producer.topic.driver-location}")
    private String topic;

    private final ObjectMapper mapper = new ObjectMapper();

    public LocationPublisherService(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishLocation(DriverLocation driverLocation) {

        try{
            String key = driverLocation.getDriverId();
            String value = mapper.writeValueAsString(driverLocation);
            kafkaTemplate.send(topic,key,value);

        }
        catch (Exception e){
            throw new RuntimeException("Exception occurred while Sending the Topic");
        }
    }
}
