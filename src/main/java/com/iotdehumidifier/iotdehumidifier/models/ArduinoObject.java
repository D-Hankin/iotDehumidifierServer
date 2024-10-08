package com.iotdehumidifier.iotdehumidifier.models;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "stats")
public class ArduinoObject {
    
    @Id
    private String id;
    private String temperature;
    private String humidity;
    private LocalDateTime timestamp;
    private boolean dehumidifierStatus; 
    
    public ArduinoObject(String temperature, String humidity, LocalDateTime timestamp, boolean dehumidifierStatus) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.timestamp = timestamp;
        this.dehumidifierStatus = dehumidifierStatus;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isDehumidifierStatus() {
        return dehumidifierStatus;
    }

    public void setDehumidifierStatus(boolean dehumidifierStatus) {
        this.dehumidifierStatus = dehumidifierStatus;
    }

    
}
