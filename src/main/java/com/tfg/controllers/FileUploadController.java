package com.tfg.controllers;

import java.io.IOException;

import com.tfg.conf.RDFRequestEditor;
import com.tfg.exceptions.GeneralException;
import com.tfg.exceptions.StorageFileNotFoundException;
import com.tfg.models.RDFRequest;
import com.tfg.services.RDFService;
import com.tfg.services.StorageService;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.riot.RDFLanguages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileUploadController {

    @Autowired
    private StorageService storageService;

    @Autowired
    private RDFService rdfService;


    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(RDFRequest.class, new RDFRequestEditor());
    }
    // PROVISIONAL

    /**
     * Endpoint to handle file uploads and create the RDF
     * @param file file to convert
     * @return returns the model to the caller
     * @throws IOException
     * @throws GeneralException
     */
  /*  @PostMapping(value = "/upload", produces = {"application/xml"})
    @ResponseBody
    public String handleFileUpload(@RequestParam("file") MultipartFile file) throws IOException, GeneralException {
        storageService.store(file);

        List<Model> rdf = rdfService.createRDF(storageService.retrieveFile(file.getOriginalFilename()));


        if(!storageService.deleteFile(file.getOriginalFilename())){
            throw new FileNotFoundException("Error deleting the file");
        }

        return rdfService.modelToString(rdf);
    }*/

    @PostMapping(value = "/upload")
    @ResponseBody
    public byte[] generateRDF(@RequestParam("file") MultipartFile file,
                                 @RequestParam("RDFRequest") RDFRequest request) throws IOException, GeneralException {

        storageService.store(file);
        Model rdf = rdfService.createRDF(storageService.retrieveFile(file.getOriginalFilename()), request);

        byte[] returnBytes = rdfService.modelToString(rdf, RDFLanguages.nameToLang(request.format)).getBytes();
        
        return returnBytes;
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

