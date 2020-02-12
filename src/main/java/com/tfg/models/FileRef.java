package com.tfg.models;

import com.tfg.models.security.User;

import javax.persistence.*;
import java.io.File;

@Entity
public class FileRef {

    @Id
    private long id;

    private String originalName;

    private File file;

    @OneToOne
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

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
