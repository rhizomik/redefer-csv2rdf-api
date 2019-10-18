package com.tfg.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Entity
public class RDFTripletsList {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    List<RDFTriplets> triplets;

    public RDFTripletsList(){
        this.triplets = new ArrayList<>();
    }

    public RDFTripletsList(List<RDFTriplets> triplets) {
        this.triplets = triplets;
    }

    public List<RDFTriplets> getTriplets() {
        return triplets;
    }

    public void setTriplets(List<RDFTriplets> triplets) {
        this.triplets = triplets;
    }
}
