package com.iotdehumidifier.iotdehumidifier.models;

import com.iotdehumidifier.iotdehumidifier.config.SecConfig;

public class HueApiClient {

    private static final String hueApiUrl = "https://192.168.1.26/clip/v2/resource/light/c5acd120-bd09-4dee-8763-150a7321a698";
    private static final String apiKey = SecConfig.getApiKey(); 

    
}
