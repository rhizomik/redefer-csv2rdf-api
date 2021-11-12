package net.rhizomik.redefer.csv2rdf.models;

import com.fasterxml.jackson.annotation.JsonProperty;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class RDFRequest {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    public long id;

    @JsonProperty("subject")
    public String subject;

    @JsonProperty("uri")
    public String uri;

    @JsonProperty("format")
    public String format;

    @ElementCollection
    @JsonProperty("types")
    public List<String> types = new ArrayList<>();

    @ElementCollection
    @JsonProperty("dataTypes")
    public List<DataType> dataTypes = new ArrayList<>();

    @OneToOne
    RdfRef rdfRef;

    public RDFRequest() { }

    public RDFRequest(String subject, String uri, String format, List<String> types,  List<DataType> dataTypes) {
        this.subject = subject;
        this.uri = uri;
        this.format = format;
        this.types = types;
        this.dataTypes = dataTypes;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

    public List<DataType> getDataTypes() {
        return dataTypes;
    }

    public void setDataTypes(List<DataType> dataTypes) {
        this.dataTypes = dataTypes;
    }

    public RdfRef getRdfRef() {
        return rdfRef;
    }

    public void setRdfRef(RdfRef rdfRef) {
        this.rdfRef = rdfRef;
    }
}
