package com.iotdehumidifier.iotdehumidifier.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.iotdehumidifier.iotdehumidifier.components.JwtComponent;
import com.iotdehumidifier.iotdehumidifier.models.ArduinoObject;
import com.iotdehumidifier.iotdehumidifier.models.LoginResponse;
import com.iotdehumidifier.iotdehumidifier.repositories.ArduinoRepository;
import com.iotdehumidifier.iotdehumidifier.services.MailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@RestController
@CrossOrigin("http://localhost:5173")
public class ArduinoController {

    @Autowired
    private ArduinoRepository arduinoRepository;

    @Autowired
    private MailService mailService;

    @Autowired
    private JwtComponent jwtComponent;

    

    private boolean mailSent = false;

    // @Autowired
    // private HueService hueService;

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
        response.setTimestamp(LocalDateTime.now());
        return arduinoRepository.insert(response);
    }
    
    @GetMapping("/get-data")
    public List<ArduinoObject> getStats() {    
        List<ArduinoObject> statsList = new ArrayList<>();
        statsList = arduinoRepository.findAll();
        ArduinoObject latestObject = statsList.get(statsList.size() - 1);
        System.out.println("mailSent: " + mailSent + ". latestObject: " + latestObject.isDehumidifierStatus());
        if (mailSent == false && latestObject.isDehumidifierStatus() == true) {
            boolean success = mailService.sendMail(latestObject);
            mailSent = success;
        } else if (mailSent == true && latestObject.isDehumidifierStatus() == false) {
            mailSent = false;
        }
        return statsList;
    }
    
    
    
}
