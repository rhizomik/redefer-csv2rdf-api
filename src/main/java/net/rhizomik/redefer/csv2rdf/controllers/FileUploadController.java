package net.rhizomik.redefer.csv2rdf.controllers;

import java.io.File;
import java.io.IOException;
import java.util.List;

import net.rhizomik.redefer.csv2rdf.conf.RDFRequestEditor;
import net.rhizomik.redefer.csv2rdf.exceptions.GeneralException;
import net.rhizomik.redefer.csv2rdf.models.security.User;
import net.rhizomik.redefer.csv2rdf.services.FileUploadService;
import net.rhizomik.redefer.csv2rdf.services.RDFService;
import net.rhizomik.redefer.csv2rdf.services.StorageService;
import net.rhizomik.redefer.csv2rdf.models.*;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.riot.RDFLanguages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

/**
 * Manages all the file related calls
 */
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

    /**
     * RDF Transformation call, generates the rdf file
     * @param file the csv file input
     * @param request the data request
     * @return a byte array with the rdf
     * @throws IOException if there is a problem with the file manipulation
     * @throws GeneralException if there is a problem with the datatype
     */
    @PostMapping(value = "/transform")
    @ResponseBody
    public byte[] generateRDF(@RequestParam("file") MultipartFile file,
                              @RequestParam("RDFRequest") RDFRequest request) throws IOException, GeneralException {

        File saved_file = storageService.createTempFile(file);
        Model rdf = rdfService.createRDF(saved_file, request);

        byte[] returnBytes = rdfService.modelToByte(rdf, RDFLanguages.nameToLang(request.format));

        return returnBytes;
    }

    /**
     * RDF Transformation call, generates the rdf file
     * @param file the csv file input
     * @param request the data request
     * @return a byte array with the rdf
     * @throws IOException if there is a problem with the file manipulation
     */
    @PostMapping("/transform-user")
    @ResponseBody
    public byte[] handleFileUpload(@RequestParam("file") MultipartFile file,
                                   @RequestParam("RDFRequest") @Valid RDFRequest request) throws IOException, GeneralException {

        User user = getUser();
        FileRef fileRef = fileUploadService.saveCsvToDatabase(file.getBytes(), file.getOriginalFilename(), user.getUsername());
        Model rdf = rdfService.createRDFUser(fileRef, request);

        byte[] rdfBytes = rdfService.modelToByte(rdf, RDFLanguages.nameToLang(request.format));
        RdfRef rdfRef = fileUploadService.saveRDFToDatabase(rdfBytes, user.getUsername(), file.getOriginalFilename(), request.format);

        rdfService.saveRequestToDatabase(rdfRef, request);
        return rdfBytes;
    }

    /**
     * Get call. Gets all the rdf.
     * @return all the rdf given a user
     */
    @GetMapping("/get-all-transformations")
    @ResponseBody
    public FileList getAllRdfRef() {
        User user = getUser();
        if (user == null) {
            return new FileList();
        }
        return rdfService.getAllRdfRefs(user);
    }

    /**
     * Download call. Returns the files requested
     * @param file The csv file requested
     * @return a list of string. On the [0] element it contains the csv file data and on [1] the rdf file.
     */
    @PostMapping("/download-transformations")
    @ResponseBody
    public List<String> downloadFiles(@RequestParam("requestedFile") String file)  {
        User user = getUser();
        if (user == null) {
            return null;
        }
        return rdfService.getRequestedFiles(file, user);
    }

    @GetMapping("/get-request/{fileName}")
    @ResponseBody
    public RDFEditorInfoResponse getRequest(@PathVariable String fileName) {
        User user = getUser();
        return rdfService.getRDFRequest(fileName, user);
    }

    private User getUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
