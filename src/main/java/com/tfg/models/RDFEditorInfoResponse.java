package com.tfg.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.ElementCollection;
import java.util.ArrayList;
import java.util.List;

public class RDFEditorInfoResponse {

    public String subject;

    public String uri;

    public String format;

    public List<String> types = new ArrayList<>();

    public List<DataType> dataTypes = new ArrayList<>();

    public RDFEditorInfoResponse(RDFRequest rdfRequest){
        this.setDataTypes(rdfRequest.dataTypes);
        this.setFormat(rdfRequest.getFormat());
        this.setSubject(rdfRequest.getSubject());
        this.setTypes(rdfRequest.getTypes());
        this.setUri(rdfRequest.getUri());
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

    public List<DataType> getDataTypes() {
        return dataTypes;
    }

    public void setDataTypes(List<DataType> dataTypes) {
        this.dataTypes = dataTypes;
    }
}
