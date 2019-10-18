package com.tfg.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Csv {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
