package com.iotdehumidifier.iotdehumidifier.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.iotdehumidifier.iotdehumidifier.models.TestMongo;

@Repository
public interface TestRepository extends MongoRepository<TestMongo, String> {
    
}
