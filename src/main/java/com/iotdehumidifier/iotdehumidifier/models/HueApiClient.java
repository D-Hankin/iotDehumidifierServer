package com.iotdehumidifier.iotdehumidifier.models;

import org.apache.hc.client5.http.async.methods.SimpleHttpRequest;
import org.apache.hc.client5.http.async.methods.SimpleHttpResponse;
import org.apache.hc.client5.http.async.methods.SimpleRequestBuilder;
import org.apache.hc.client5.http.impl.async.CloseableHttpAsyncClient;
import org.apache.hc.client5.http.impl.async.HttpAsyncClients;
import org.apache.hc.client5.http.impl.nio.PoolingAsyncClientConnectionManagerBuilder;
import org.apache.hc.client5.http.ssl.DefaultClientTlsStrategy;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.ssl.SSLContextBuilder;
import org.apache.hc.core5.ssl.TrustStrategy;

import java.io.FileInputStream;
import java.security.KeyStore;
import java.util.concurrent.Future;

import javax.net.ssl.SSLContext;
import org.springframework.stereotype.Component;

import com.iotdehumidifier.iotdehumidifier.config.SecConfig;

@Component
public class HueApiClient {

    private static final String hueApiUrl = "";
    private static final String apiKey = SecConfig.getApiKey(); 
    private static final String caCertPath = "truststore.jks";

    public String controlHueSocket(boolean action) {
        String responseString = "";
        String putData = "{\"on\":{\"on\":" + action + "}}";

        
        try {
            KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
            try (FileInputStream instream = new FileInputStream(caCertPath)) {
                trustStore.load(instream, "".toCharArray()); 
            }

            SSLContext sslContext = SSLContextBuilder.create()
                    .loadTrustMaterial(trustStore, (TrustStrategy) (chain, authType) -> true)
                    .build();

            CloseableHttpAsyncClient client = HttpAsyncClients.custom()
                    .setConnectionManager(PoolingAsyncClientConnectionManagerBuilder.create()
                        .setTlsStrategy(new DefaultClientTlsStrategy(sslContext))
                        .build()
                    )
                    .build();

            client.start();

            SimpleHttpRequest request = SimpleRequestBuilder.put(hueApiUrl)
                        .setHeader("Content-Type", "application/json")
                        .setHeader("hue-application-key", apiKey)
                        .setBody(putData, ContentType.APPLICATION_JSON)
                        .build();

            Future<SimpleHttpResponse> future = client.execute(request, null);
            SimpleHttpResponse response = future.get();
            System.out.println("Response body: " + response.getBodyText());
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
        } 

        return responseString;
    }
}


    

