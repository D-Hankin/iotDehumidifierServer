package com.iotdehumidifier.iotdehumidifier.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.iotdehumidifier.iotdehumidifier.models.TestResponse;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@CrossOrigin("*")
public class TestController {

    @GetMapping("/test")
    public TestResponse testGeString() {
        return new TestResponse("200", "Server Working");
    }
    
    @PostMapping("/from-arduino")
    public TestResponse testPost(@RequestBody TestResponse response) {
        System.out.println(response.getStatus() + ", " + response.getMessage());

        return new TestResponse("200", "message from server");
    }
    
    
}
