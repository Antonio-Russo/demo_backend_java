package com.example.demo_be.models.responses;

import lombok.Data;

@Data
public class JwtAuthResponse {
    private String jwt;
    public JwtAuthResponse() { }
    public JwtAuthResponse(String jwt) {
        this.jwt = jwt;
    }
}
