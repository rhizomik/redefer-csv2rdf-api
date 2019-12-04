package com.tfg.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Service
public class StorageService {

    @Autowired
    private Environment env;

    private String path = env.getProperty("file.upload-dir");

    public void store(MultipartFile file)  {
        try (OutputStream os = Files.newOutputStream(Paths.get(path))){
            os.write(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public File retrieveFile(String file) {
        return new File(path + "/" + file);
    }

}

