package com.iotdehumidifier.iotdehumidifier.models;

public class LoginDto {
    
    private String username;
    private String password;

    public LoginDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }
    
    public String getPassword() {
        return password;
    }

    
}
