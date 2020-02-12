package com.tfg.services;

import com.tfg.models.FileRef;
import com.tfg.models.security.User;
import com.tfg.repositories.FileRefRepository;
import com.tfg.repositories.RdfRefRepository;
import com.tfg.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class FileUploadService {

    @Autowired
    private StorageService storageService;

    @Autowired
    private FileRefRepository fileRefRepository;

    @Autowired
    private UserRepository userRepository;


    public void saveCsvFileToDatabase(String username, File file) {
        User user = userRepository.findByUsername(username);
        FileRef fileRef = new FileRef();
        fileRef.setFile(file);
        fileRef.setOriginalName(file.getName());
        fileRef.setUser(user);
        fileRefRepository.save(fileRef);
    }
}
