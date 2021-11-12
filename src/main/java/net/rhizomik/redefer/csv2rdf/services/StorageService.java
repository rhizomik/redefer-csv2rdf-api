package net.rhizomik.redefer.csv2rdf.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class StorageService {

    /**
     * Creates a tempFile
     * @param file A file to store
     * @return the Temp File
     */
    public File createTempFile(MultipartFile file) throws IOException {
        File tempFile = File.createTempFile(file.getOriginalFilename(), ".csv");
        file.transferTo(tempFile);
        return tempFile;
    }

}

