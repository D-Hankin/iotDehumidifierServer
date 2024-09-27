package com.iotdehumidifier.iotdehumidifier.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.iotdehumidifier.iotdehumidifier.models.ArduinoObject;
import com.iotdehumidifier.iotdehumidifier.repositories.TestRepository;
import com.iotdehumidifier.iotdehumidifier.services.HueService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.List;


@RestController
@CrossOrigin("*")
public class ArduinoController {

    @Autowired
    private TestRepository testRepository;

    @Autowired
    private HueService hueService;

    // @GetMapping("/test")
    // public TestResponse testGeString() {
    //     return new TestResponse("200", "Server Working");
    // }
    
    // @PostMapping("/from-arduino")
    // public TestResponse testPost(@RequestBody TestResponse response) {
    //     System.out.println(response.getStatus() + ", " + response.getMessage());

    //     return new TestResponse("200", "message from server");
    // }

    @PostMapping("/send-data")
    public ArduinoObject testMongo(@RequestBody ArduinoObject response) {
        System.out.println("humidity: " + response.getHumidity());
        System.out.println("status: " + response.isDehumidifierStatus());
        response.setTimestamp(LocalDateTime.now());
        hueService.setDehumidifierStatus(response.getHumidity(), response.isDehumidifierStatus());
        return testRepository.insert(response);
    }

    @GetMapping("/get-data")
    public List<ArduinoObject> getStats() {
        return testRepository.findAll();
    }
    
    
    
}
