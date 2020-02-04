package com.tfg.services;

import com.tfg.exceptions.GeneralException;
import com.tfg.models.Csv;
import com.tfg.models.RDFRequest;
import com.tfg.models.Triplets;
import com.tfg.models.security.User;
import com.tfg.repositories.TripletsRepository;
import com.tfg.repositories.UserRepository;
import com.tfg.utils.CsvReader;

import org.apache.jena.rdf.model.*;
import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class RDFService {

    @Autowired
    private TripletsRepository tripletsRepository;

    @Autowired
    private UserRepository userRepository;

    private final String NS = "http://provisionalUri.com/";


    public Model createRDF(File file, RDFRequest request) throws IOException, GeneralException {
        Csv csv = CsvReader.convertFileToCsv(file);
        Model model = ModelFactory.createDefaultModel();
        int subjectPosition = getSubjectPosition(request.subject, csv.headers);
        for(int i=0; i < csv.lines.length; i++) {
            Resource r = model.createResource(request.uri + '/'+ csv.lines[i][subjectPosition]);
            addProperties(r, csv, model, i, subjectPosition, request);
        }
        return model;
    }



    /**
     * Creates the RDF given a file
     * @param file
     * @return A list of models representing the RDF
     * @throws IOException
     */
    public List<Model> createRDF(File file) throws IOException {
        Csv csv = CsvReader.convertFileToCsv(file);
        List<Model> modelList = new ArrayList<>();
        for(int i = 1; i < csv.lines.length; i++) {
            Model model = ModelFactory.createDefaultModel();
            Resource r = model.createResource( NS + i);
          //  addProperties(r, csv, model, i);
            modelList.add(model);
        }
        return modelList;
    }

    private void addProperties(Resource r, Csv csv, Model model, int i, int subjectPosition, RDFRequest request) {
        for(int j = 0; j < csv.lines[i].length; j++) { // if the columns have different length this will cause problems
            if(j != subjectPosition){
                Property property = model.createProperty(request.uri + '/' + request.types.get(j));
                Literal value = model.createLiteral(csv.lines[i][j]);
                model.add(r, property, value);
            }
        }

    }

    /**
     * Given a list of models, transforms it to char
     * @param models
     * @return a char array
     */
    public char[] modelToChar(List<Model> models) {
        char[] rdf;
        StringBuilder all_elements = new StringBuilder();
        for(Model model: models) {
            StringWriter out = new StringWriter();
            model.write(out, "RDF/XML-ABBREV");
            all_elements.append(out.toString());
            all_elements.append("\n");
        }
        rdf = all_elements.toString().toCharArray();
        return rdf;
    }

    /**
     * Given a list of models, transforms it to string
     * @param model
     * @return a string
     */
    public String modelToString(Model model, Lang format) throws GeneralException {
        StringWriter out = new StringWriter();
        RDFDataMgr.write(out, model, format);

        return out.toString();
    }

    public void saveToDatabase(List<Model> rdf, String username) {
        if(!userRepository.findByUsernameContaining(username).isEmpty()) {
            Triplets triplets = new Triplets();
            triplets.setUser(username);
            triplets.setRdf(modelToChar(rdf));
            tripletsRepository.save(triplets);
            return;
        }
        throw new UsernameNotFoundException(username);
    }

    private int getSubjectPosition(String subject, String[] headers) throws GeneralException {
        for(int i=0; i<headers.length; i++) {
            if (subject.equals(headers[i])){
                return i;
            }
        }
        throw new GeneralException("Subject doesn't correspond to any of the headers");
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
