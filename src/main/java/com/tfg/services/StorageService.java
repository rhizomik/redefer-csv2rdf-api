package com.tfg.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

@Service
public class StorageService {

    @Value("${file.upload-dir}")
    private String CsvPath;

    @Value("file.rdf-save-dir")
    private String RDFPath;

    /**
     * Stores the File locally
     * @param file A file to store
     * @return a boolean indicating if it was possible
     */
    public File storeCSV(MultipartFile file) {
        Random random = new Random();
        // Use random number to try to avoid collisions with the same file name
        String random_id = String.valueOf(random.nextInt(10000));
        try (OutputStream os = Files.newOutputStream(Paths.get(CsvPath +
                                                                File.separator +
                                                                random_id+
                                                                file.getOriginalFilename() +
                                                                random_id))) {
            os.write(file.getBytes());
            os.close();
            return retrieveCsvFile(file.getOriginalFilename() + random_id);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Opens and returns the file
     * @param fileName the fileName
     * @return the file
     */
    public File retrieveCsvFile(String fileName) {
         File file = new File(CsvPath  + "\\" + fileName);
         if(file.exists()) {
             return file;
         }
         return null;
    }

    /**
     * Deletes the file specified by name
     * @param fileName the fileName
     * @return a boolean indicating if it was succesfuly deleted
     */
    public boolean deleteCsvFile(String fileName) {
        File file = retrieveCsvFile(fileName);
        return file.delete();
    }
}

