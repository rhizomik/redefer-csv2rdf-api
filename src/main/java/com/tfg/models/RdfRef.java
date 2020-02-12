package com.tfg.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.io.File;

@Entity
public class RdfRef {

    @Id
    private long id;

    @OneToOne
    private FileRef fileRef;

    private File RDFFile;

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

    public File getRDFFile() {
        return RDFFile;
    }

    public void setRDFFile(File RDFFile) {
        this.RDFFile = RDFFile;
    }
}
