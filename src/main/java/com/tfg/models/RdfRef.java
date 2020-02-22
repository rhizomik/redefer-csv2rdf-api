package com.tfg.models;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
public class RdfRef {

    private final static int size = 1024 * 1024; //1GB

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @OneToOne
    private FileRef fileRef;

    @Column(length = size)
    @Size(max= size)
    private byte[] RDFFile;

    private String format;


    public RdfRef() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public FileRef getFileRef() {
        return fileRef;
    }

    public void setFileRef(FileRef fileRef) {
        this.fileRef = fileRef;
    }

    public byte[] getRDFFile() {
        return RDFFile;
    }

    public void setRDFFile(byte[] RDFFile) {
        this.RDFFile = RDFFile;
    }

    public String getFormat() {
        if(format.equals("RDF/XML")) {
            return ".xml";
        }else if(format.equals("RDF/JSON")) {
            return ".json";
        } else {
            return ".txt";
        }
    }

    public void setFormat(String format) {
        this.format = format;
    }
}
