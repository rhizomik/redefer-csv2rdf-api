package com.tfg.services;

import com.tfg.models.Csv;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class RDFService {

    public List<Model> createRDF(Csv csv){
        Property[] headersProperties = new Property[csv.headers.length];
        // initializing resources
        initializeProperties(headersProperties, csv);

        List<Model> models = initializeModels(csv, headersProperties);

        return models;
    }

    private List<Model> initializeModels(Csv csv, Property[] headersProperties){
        Model[] models = new Model[csv.lines.length];
        for (int i = 0; i < csv.lines.length; i++){
            models[i] = ModelFactory.createDefaultModel();
            Resource resource = models[i].createResource(); // uri should be here
            for(int j = 1; j < csv.lines[i].length; j++) { //starts at 1 because 0 should be the subject info
                resource.addProperty(headersProperties[j], csv.lines[i][j]);
            }
        }
        return Arrays.asList(models);

    }
    private void initializeProperties(Resource[] headersProperties, Csv csv) {
        for (int i = 0; i < csv.headers.length; i++) {
            headersProperties[i] = getPredicate(csv.headers[i]);
        }
    }

    private Property getPredicate(String header) {
        return PropertiesFactory.getProperty(header);
    }
}
