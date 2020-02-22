package com.tfg.models;

import java.util.ArrayList;
import java.util.List;

public class FileList {

    public List<String> csvFiles;
    public List<String> rdfFiles;

    public FileList() {
        csvFiles = new ArrayList<>();
        rdfFiles = new ArrayList<>();
    }

    public List<String> getCsvFiles() {
        return csvFiles;
    }

    public void setCsvFiles(List<String> csvFiles) {
        this.csvFiles = csvFiles;
    }

    public List<String> getRdfFiles() {
        return rdfFiles;
    }

    public void setRdfFiles(List<String> rdfFiles) {
        this.rdfFiles = rdfFiles;
    }
}
