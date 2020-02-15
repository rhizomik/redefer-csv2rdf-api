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

    @Autowired
    private UserService userService;

    public FileRef saveCsvToDatabase(byte[] bytes, String fileName, String username) {
        User user = (User) userService.loadUserByUsername(username);
        FileRef fileRef = fileRefRepository.findByOriginalNameAndUser(fileName, user);
        if(fileRef == null){
            fileRef = new FileRef();
            fileRef.setFile(bytes);
            fileRef.setOriginalName(fileName);
            fileRef.setUser(user);
            return fileRefRepository.save(fileRef);
        }
        return fileRef;
    }
}
