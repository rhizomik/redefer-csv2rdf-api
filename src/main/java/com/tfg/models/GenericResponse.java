package com.tfg.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.List;

public class GenericResponse {

    @JsonProperty("message")
    private String message;
    
    @JsonProperty("error")
    private String error;

    public GenericResponse(String message, String error) {
        this.message = message;
        this.error = error;
    }
}
