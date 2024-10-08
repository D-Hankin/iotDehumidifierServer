package com.iotdehumidifier.iotdehumidifier.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.iotdehumidifier.iotdehumidifier.models.ArduinoObject;

@Repository
public interface ArduinoRepository extends MongoRepository<ArduinoObject, String> {
    
}
