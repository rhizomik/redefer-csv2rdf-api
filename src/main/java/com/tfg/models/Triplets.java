package com.tfg.models;

import org.apache.jena.rdf.model.Model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Triplets {

    @Id
    @GeneratedValue
    private int id;

  //  private List<Model> models;

    private String user;

    private char[] rdf;

    public Triplets() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public char[] getRdf() {
        return rdf;
    }

    public void setRdf(char[] rdf) {
        this.rdf = rdf;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
