package com.example.demo_be.models.requests;

import lombok.Data;

//use lombok for getter e setter
@Data
public class JwtAuthRequest {
    private String username;
    private String password;

    public JwtAuthRequest() {
    }

    public JwtAuthRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }
}