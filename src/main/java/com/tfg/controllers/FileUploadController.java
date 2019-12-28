package com.tfg.controllers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import com.tfg.exceptions.StorageFileNotFoundException;
import com.tfg.services.RDFService;
import com.tfg.services.StorageService;
import org.apache.jena.rdf.model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class FileUploadController {

    @Autowired
    private StorageService storageService;

    @Autowired
    private RDFService rdfService;


    // PROVISIONAL
    @PostMapping("/upload")
    @ResponseBody
    public String handleFileUpload(@RequestParam("file") MultipartFile file) throws IOException {
        storageService.store(file);

        List<Model> rdf = rdfService.createRDF(storageService.retrieveFile(file.getOriginalFilename()));


        if(!storageService.deleteFile(file.getOriginalFilename())){
            throw new FileNotFoundException("Error deleting the file");
        }

        return rdfService.modelToString(rdf);
    }
/*
    //WITH USERNAME
    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file, String username) throws IOException {
        storageService.store(file);

        List<Model> rdf = rdfService.createRDF(storageService.retrieveFile(file.getName()));

        rdfService.saveToDatabase(rdf, "username");

        if(!storageService.deleteFile(file.getName())){
            throw new FileNotFoundException("Error deleting the file");
        }

        return Arrays.toString(rdfService.modelToChar(rdf));
    }*/

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

}

