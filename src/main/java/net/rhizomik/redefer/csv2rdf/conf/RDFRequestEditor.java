package net.rhizomik.redefer.csv2rdf.conf;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.rhizomik.redefer.csv2rdf.models.RDFRequest;

import java.beans.PropertyEditorSupport;
import java.io.IOException;


public class RDFRequestEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(String text){
        ObjectMapper mapper = new ObjectMapper();

        try {
            setValue(mapper.readValue(text, RDFRequest.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
