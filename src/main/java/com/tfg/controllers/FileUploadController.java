package com.tfg.controllers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.tfg.conf.RDFRequestEditor;
import com.tfg.exceptions.GeneralException;
import com.tfg.exceptions.StorageFileNotFoundException;
import com.tfg.models.FileList;
import com.tfg.models.FileRef;
import com.tfg.models.RDFRequest;
import com.tfg.models.RdfRef;
import com.tfg.models.security.User;
import com.tfg.repositories.RdfRefRepository;
import com.tfg.services.FileUploadService;
import com.tfg.services.RDFService;
import com.tfg.services.StorageService;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.riot.RDFLanguages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@Controller
@RequestMapping("/api")
public class FileUploadController {

    @Autowired
    private StorageService storageService;

    @Autowired
    private RDFService rdfService;

    @Autowired
    private FileUploadService fileUploadService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(RDFRequest.class, new RDFRequestEditor());
    }


    @PostMapping(value = "/transform")
    @ResponseBody
    public byte[] generateRDF(@RequestParam("file") MultipartFile file,
                              @RequestParam("RDFRequest") RDFRequest request) throws IOException, GeneralException {

        File saved_file = storageService.storeCSV(file);
        Model rdf = rdfService.createRDF(saved_file, request);

        byte[] returnBytes = rdfService.modelToByte(rdf, RDFLanguages.nameToLang(request.format));

        return returnBytes;
    }

    @PostMapping("/transform-user")
    @ResponseBody
    public byte[] handleFileUpload(@RequestParam("file") MultipartFile file,
                                   @RequestParam("RDFRequest") @Valid RDFRequest request) throws IOException, GeneralException {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        FileRef fileRef = fileUploadService.saveCsvToDatabase(file.getBytes(), file.getOriginalFilename(), user.getUsername());
        Model rdf = rdfService.createRDFUser(fileRef, request);

        byte[] rdfBytes = rdfService.modelToByte(rdf, RDFLanguages.nameToLang(request.format));
        rdfService.saveRDFToDatabase(rdfBytes, user.getUsername(), file.getOriginalFilename(), request.format);

        return rdfBytes;
    }

    @GetMapping("/get-all-transformations")
    @ResponseBody
    public FileList getAllRdfRef() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user == null) {
            return new FileList();
        }
        return rdfService.getAllRdfRefs(user);
    }

    @PostMapping("/download-transformations")
    @ResponseBody
    public List<String> downloadFiles(@RequestParam("requestedFile") String file)  {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user == null) {
            return null;
        }
        return rdfService.getRequestedByteFiles(file, user);
    }
}
