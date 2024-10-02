package com.iotdehumidifier.iotdehumidifier.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.iotdehumidifier.iotdehumidifier.models.ArduinoObject;
import com.iotdehumidifier.iotdehumidifier.repositories.ArduinoRepository;
import com.iotdehumidifier.iotdehumidifier.services.MailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.time.LocalDateTime;
import java.util.List;


@RestController
@CrossOrigin("http://localhost:5173")
public class ArduinoController {

    @Autowired
    private ArduinoRepository arduinoRepository;

    @Autowired
    private MailService mailService;

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
        System.out.println("here: " + response.isDehumidifierStatus() + ", " + mailSent);
        response.setTimestamp(LocalDateTime.now());
        if (mailSent == false && response.isDehumidifierStatus() == true) {
            System.out.println("then here: " + response.isDehumidifierStatus());
            mailService.sendMail(response);
            mailSent = true;
        } else if (mailSent == true && response.isDehumidifierStatus() == false) {
            System.out.println("or here: " + response.isDehumidifierStatus());
            mailSent = false;
        }
        // hueService.setDehumidifierStatus(response.getHumidity(), response.isDehumidifierStatus());
        return arduinoRepository.insert(response);
    }

    @GetMapping("/get-data")
    public List<ArduinoObject> getStats() {
        return arduinoRepository.findAll();
    }
    
    
    
}
