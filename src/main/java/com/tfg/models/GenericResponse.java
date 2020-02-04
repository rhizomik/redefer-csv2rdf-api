package com.tfg.models;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.List;

public class GenericResponse {

    private String message;
    private String error;

    public GenericResponse(String message, String error) {
        this.message = message;
        this.error = error;
    }
}
