package com.tfg.models;

import com.fasterxml.jackson.annotation.JsonProperty;



import java.util.List;

public class RDFRequest {

    @JsonProperty("subject")
    public String subject;
    @JsonProperty("uri")
    public String uri;
    @JsonProperty("format")
    public String format;
    @JsonProperty("types")
    public List<String> types;
    @JsonProperty("dataTypes")
    public List<DataType> dataTypes;


  public RDFRequest() {
  }

    public RDFRequest(String subject, String uri, String format, List<String> types,  List<DataType> dataTypes) {
        this.subject = subject;
        this.uri = uri;
        this.format = format;
        this.types = types;
        this.dataTypes = dataTypes;
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
