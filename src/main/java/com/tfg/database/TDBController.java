package com.tfg.database;

import org.apache.jena.query.Dataset;
import org.apache.jena.query.ReadWrite;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.tdb.TDBFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TDBController {

    private Dataset dataset;

    @Autowired
    public TDBController(@Value("${dataset-dir}") String directory) {
        dataset = TDBFactory.createDataset(directory);
    }


    /**
     * Adds a model in the database
     * @param uri uri of the model
     * @param model model to be added
     */
    public void addModel(String uri, Model model) {
        // Blocks the DB for the writing
        dataset.begin(ReadWrite.WRITE);

        dataset.addNamedModel(uri, model);

        //Unlocks the DB
        dataset.end();
    }

    /**
     * Returns a model from the database
     * @param uri uri of the model
     * @return Model populated if found, empty model if not found
     */
    public Model getModel(String uri) {
        dataset.begin(ReadWrite.READ);

        return dataset.getNamedModel(uri);
    }



}
