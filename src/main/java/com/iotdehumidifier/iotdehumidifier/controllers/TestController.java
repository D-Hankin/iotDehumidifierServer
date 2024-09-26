package com.iotdehumidifier.iotdehumidifier.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.iotdehumidifier.iotdehumidifier.models.TestMongo;
import com.iotdehumidifier.iotdehumidifier.models.TestResponse;
import com.iotdehumidifier.iotdehumidifier.repositories.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


@RestController
@CrossOrigin("*")
public class TestController {

    @Autowired
    private TestRepository testRepository;

    @GetMapping("/test")
    public TestResponse testGeString() {
        return new TestResponse("200", "Server Working");
    }
    
    @PostMapping("/from-arduino")
    public TestResponse testPost(@RequestBody TestResponse response) {
        System.out.println(response.getStatus() + ", " + response.getMessage());

        return new TestResponse("200", "message from server");
    }

    @PostMapping("/test-mongo")
    public TestMongo testMongo(@RequestBody TestMongo response) {
        System.out.println(response.getTemperature());
        return testRepository.insert(response);
    }

    @GetMapping("/client-to-db-and-back")
    public List<TestMongo> getStats() {
        return testRepository.findAll();
    }
    
    
    
}
