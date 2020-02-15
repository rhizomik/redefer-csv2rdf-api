package com.tfg.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Size;

@Entity
public class RdfRef {

    private final static int size = 1024 * 1024; //1GB

    @Id
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
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }
}
