package com.tfg.models;

import com.tfg.models.security.User;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.File;

@Entity
public class FileRef {

    private final static int size = 1024 * 1024; //1GB

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    private String originalName;

    @Column(length = size)
    @Size(max = size)
    private byte[] file;

    @ManyToOne
    private User user;

    public FileRef() {

    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
