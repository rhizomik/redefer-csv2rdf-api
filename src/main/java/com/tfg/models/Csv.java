package com.tfg.models;

//@Entity
public class Csv {


    private Integer id;

    public String[] headers;

    public String[][] lines;

    public Csv(String[] headers, String[][] lines) {
        this.headers = headers;
        this.lines = lines;
    }

    public Csv() {
        this.headers = new String[] {};
        this.lines = new String[][] {};
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String[] getHeaders() {
        return headers;
    }

    public void setHeaders(String[] headers) {
        this.headers = headers;
    }

    public String[][] getLines() {
        return lines;
    }

    public void setLines(String[][] lines) {
        this.lines = lines;
    }
}
