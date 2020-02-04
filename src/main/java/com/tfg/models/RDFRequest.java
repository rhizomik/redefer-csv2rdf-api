package com.tfg.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.web.multipart.MultipartFile;

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
  /*  private List<String> headers;
    private List<List<String>> lines;*/

  public RDFRequest() {

  }

    public RDFRequest(String subject, String uri, String format, List<String> types, List<String> headers, List<List<String>> lines) {
        this.subject = subject;
        this.uri = uri;
        this.format = format;
        this.types = types;
   /*     this.headers = headers;
        this.lines = lines;*/
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
/*
    public List<String> getHeaders() {
        return headers;
    }

    public void setHeaders(List<String> headers) {
        this.headers = headers;
    }

    public List<List<String>> getLines() {
        return lines;
    }

    public void setLines(List<List<String>> lines) {
        this.lines = lines;
    }*/
}
