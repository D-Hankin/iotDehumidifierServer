package com.iotdehumidifier.iotdehumidifier.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iotdehumidifier.iotdehumidifier.models.HueApiClient;

@Service
public class HueService {
    
    @Autowired
    HueApiClient hueApiClient;

    public void setDehumidifierStatus(String humidity, boolean dehumidifierStatus) {
        if (Float.parseFloat(humidity) >= 50 && !dehumidifierStatus) {
            hueApiClient.controlHueSocket(true);
        } else if (Float.parseFloat(humidity) <= 45 && dehumidifierStatus) {
            hueApiClient.controlHueSocket(false);
        }
    }
}
