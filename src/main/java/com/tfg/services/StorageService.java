package com.tfg.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class StorageService {

    @Value("${file.upload-dir}")
    private String path;

    /**
     * Stores the File localy
     * @param file
     * @return a boolean indicating if it was possible
     */
    public boolean store(MultipartFile file)  {
        try (OutputStream os = Files.newOutputStream(Paths.get(path + file.getOriginalFilename()))) {
            os.write(file.getBytes());
            os.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Opens and returns the file
     * @param fileName
     * @return the file
     */
    public File retrieveFile(String fileName) {
        return new File(path  + fileName);
    }

    /**
     * Deletes the file
     * @param fileName
     * @return
     */
    public boolean deleteFile(String fileName) {
        File file = retrieveFile(fileName);
        return file.delete();
    }

}

