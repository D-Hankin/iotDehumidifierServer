package com.iotdehumidifier.iotdehumidifier.models;

public class LoginDto {
    
    private String email;
    private String password;

    public LoginDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }
    
    public String getPassword() {
        return password;
    }

    
}
