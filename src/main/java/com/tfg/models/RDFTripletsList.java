package com.tfg.models;

import java.util.ArrayList;
import java.util.List;

//@Entity
public class RDFTripletsList {


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
