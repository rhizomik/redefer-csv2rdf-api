package com.tfg.conf;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tfg.models.RDFRequest;

import java.beans.PropertyEditorSupport;
import java.io.IOException;


public class RDFRequestEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(String text){
        ObjectMapper mapper = new ObjectMapper();

        try {
            setValue(mapper.readValue(text, RDFRequest.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
