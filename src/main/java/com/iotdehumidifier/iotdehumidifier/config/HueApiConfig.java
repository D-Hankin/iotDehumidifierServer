package com.iotdehumidifier.iotdehumidifier.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HueApiConfig {
    @Value("${HUE_API_KEY}")
    private static String apiKey;

    public static String getApiKey() {
        return apiKey;
    }
}
