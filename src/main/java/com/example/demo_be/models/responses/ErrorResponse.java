package com.example.demo_be.models.responses;

import com.fasterxml.jackson.annotation.JsonRootName;
import java.util.List;

@JsonRootName("error")
public class ErrorResponse {

    private String path;
    private String message;
    private List<String> details;

    public ErrorResponse(String path, String message , List<String> details) {
        this.path = path;
        this.message = message;
        this.details = details;
    }

    public String getPath() {
        return path;
    }

    public String getMessage() {
        return message;
    }

    public List<String> getDetails() {
        return details;
    }

}