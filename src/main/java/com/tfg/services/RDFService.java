package com.tfg.services;

import com.tfg.models.Csv;
import com.tfg.utils.CsvReader;


import org.apache.jena.ontology.ObjectProperty;
import org.apache.jena.rdf.model.*;
import org.springframework.stereotype.Service;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service
public class RDFService {

    public List<Model> createRDF(File file) throws Exception { //TODO implement Exceptions
        Csv csv = CsvReader.convertFileToCsv(file);
        List<Model> modelList = new ArrayList<>();
        for(int i = 1; i < csv.lines.length; i++) {
            Model model = ModelFactory.createDefaultModel();
            Resource r = model.createResource( "http://provisionalUri.com/" + i);
            addProperties(r, csv, model, i);
            modelList.add(model);
        }


        return modelList;
    }

    private void addProperties(Resource r, Csv csv, Model model, int i) {
        for(int j = 0; j < csv.lines[i].length; j++) { // if the columns have different length this will cause problems
            Property property = model.createProperty(csv.headers[j]);
            Literal value = model.createLiteral(csv.lines[i][j]);
            model.add(r, property, value);
        }

    }


/*
    public List<Model> createRDF(File file) throws Exception {
        Csv csv = CsvReader.convertFileToCsv(file);

        Property[] headersProperties = new Property[csv.headers.length];
        // initializing resources
        initializeProperties(headersProperties, csv);

        List<Model> models = initializeModels(csv, headersProperties);
        return models;
    }


    private List<Model> initializeModels(Csv csv, Property[] headersProperties){
        Model[] models = new Model[headersProperties.length];
        for (int i = 0; i < headersProperties.length; i++){
            models[i] = ModelFactory.createDefaultModel();
            Resource resource = models[i].createResource("https://provisional.uri/" + );
            for(int j = 1; j < csv.lines[i].length; j++) { //starts at 1 because 0 should be the subject info
                resource.addProperty(headersProperties[j], csv.lines[i][j]);
            }
        }
        return Arrays.asList(models);
    }

    private void initializeProperties(Resource[] headersProperties, Csv csv) {
        for (int i = 0; i < csv.headers.length; i++) {
            headersProperties[i] = getPredicate(csv.headers[i].toLowerCase());
        }
    }

    private Property getPredicate(String header) {
        return PropertiesFactory.getProperty(header);
    }
    */


}
