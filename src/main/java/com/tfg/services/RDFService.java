package com.tfg.services;

import com.tfg.exceptions.GeneralException;
import com.tfg.models.*;
import com.tfg.models.security.User;
import com.tfg.repositories.FileRefRepository;
import com.tfg.repositories.RdfRefRepository;
import com.tfg.repositories.UserRepository;
import com.tfg.utils.CsvReader;

import org.apache.commons.io.FileUtils;
import org.apache.jena.rdf.model.*;
import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.zip.ZipOutputStream;


@Service
public class RDFService {

    @Autowired
    private UserRepository userRepository;

    @Value("file.upload-dir")
    private String provisionalPath;

    @Autowired
    private StorageService storageService;

    @Autowired
    private RdfRefRepository rdfRefRepository;

    @Autowired
    private FileRefRepository fileRefRepository;

    @Autowired
    private UserService userService;

    /**
     * Creates the RDF given a file
     * @param fileRef
     * @return A list of models representing the RDF
     * @throws IOException
     */
    public Model createRDFUser(FileRef fileRef, RDFRequest request) throws IOException, GeneralException {
        File file = new File(provisionalPath + File.separator + fileRef.getOriginalName());
        FileUtils.writeByteArrayToFile(file, fileRef.getFile());
        Csv csv = CsvReader.convertFileToCsv(file);
        file.delete();

        Model model = ModelFactory.createDefaultModel();
        int subjectPosition = getSubjectPosition(request.subject, csv.headers);
        for(int i=0; i < csv.lines.length; i++) {
            Resource r = model.createResource(request.uri + '/' +csv.lines[i][subjectPosition]);
            addProperties(r, csv.lines[i], model, subjectPosition, request.types, request.uri);
        }
        return model;
    }

    public Model createRDF(File file, RDFRequest request) throws IOException, GeneralException {
        Csv csv = CsvReader.convertFileToCsv(file);
        file.delete();

        Model model = ModelFactory.createDefaultModel();
        int subjectPosition = getSubjectPosition(request.subject, csv.headers);
        for(int i=0; i < csv.lines.length; i++) {
            Resource r = model.createResource(request.uri + '/'+ csv.lines[i][subjectPosition]);
            addProperties(r, csv.lines[i], model, subjectPosition, request.types, request.uri);
        }
        return model;
    }

    public FileList getAllRdfRefs(User user) {
        List<FileRef> fileRefs = fileRefRepository.findAllByUser(user);
        FileList fileList = new FileList();
        for(FileRef fileRef: fileRefs) {
            fileList.csvFiles.add(fileRef.getOriginalName());
            String name = fileRef.getOriginalName().substring(0, fileRef.getOriginalName().length() - 4);
            fileList.rdfFiles.add(name + "_rdf" + rdfRefRepository.findByFileRef(fileRef).getFormat());
        }
        return fileList;
    }

    public void saveRDFToDatabase(byte[] rdfBytes, String username, String fileName, String format) {
        User user = (User) userService.loadUserByUsername(username);
        FileRef fileRef = fileRefRepository.findByOriginalNameAndUser(fileName, user);

        RdfRef rdfRef = rdfRefRepository.findByFileRef(fileRef);
        if(rdfRef == null) {
            rdfRef = new RdfRef();
        }
        rdfRef.setRDFFile(rdfBytes);
        rdfRef.setFileRef(fileRef);
        rdfRef.setFormat(format);
        rdfRefRepository.save(rdfRef);
    }

    /**
     * Given a list of models, transforms it to string
     * @param model
     * @return a string
     */
    public String modelToString(Model model, Lang format) {
        StringWriter out = new StringWriter();
        RDFDataMgr.write(out, model, format);

        return out.toString();
    }

    public byte[] modelToByte(Model model, Lang format) {
        return modelToString(model, format).getBytes();
    }

    private int getSubjectPosition(String subject, String[] headers) throws GeneralException {
        for(int i=0; i<headers.length; i++) {
            if (subject.equals(headers[i])){
                return i;
            }
        }
        throw new GeneralException("Subject doesn't correspond to any of the headers");
    }

    private void addProperties(Resource r, String[] lines, Model model, int subjectPosition, List<String> types, String uri) {
        for(int j = 0; j < lines.length; j++) {
            if(j != subjectPosition){
                Property property = model.createProperty(uri + "/" + types.get(j));
                Literal value = model.createLiteral(lines[j]);
                model.add(r, property, value);
            }
        }
    }

    public List<String> getRequestedByteFiles(String file, User user)  {
        FileRef fileRef = fileRefRepository.findByOriginalNameAndUser(file, user);
        RdfRef rdfRef = rdfRefRepository.findByFileRef(fileRef);

        ArrayList<String> list = new ArrayList<>();
        list.add(new String(fileRef.getFile()));
        list.add(new String(rdfRef.getRDFFile()));
        return list;
    }
}
