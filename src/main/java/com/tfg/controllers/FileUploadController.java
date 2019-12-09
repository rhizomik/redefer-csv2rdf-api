package com.tfg.controllers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class FileUploadController {

    @Autowired
    private StorageService storageService;

    @Autowired
    private RDFService rdfService;

    @PostMapping("/upload")
        public List<Model> handleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) throws IOException {

        storageService.store(file);
        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");
        //TODO mirar si es millor guardar els csv i depsres transformar-los o transformar-los i borrar-los
        List<Model> rdf = rdfService.createRDF(storageService.retrieveFile(file.getName()));

        if(!storageService.deleteFile(file.getName())){
            throw new FileNotFoundException("Error deleting the file");
        }

        return rdf;
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

}

