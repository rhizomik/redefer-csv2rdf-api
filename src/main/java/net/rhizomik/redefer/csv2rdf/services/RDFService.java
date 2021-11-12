package net.rhizomik.redefer.csv2rdf.services;

import net.rhizomik.redefer.csv2rdf.exceptions.GeneralException;
import net.rhizomik.redefer.csv2rdf.models.security.User;
import net.rhizomik.redefer.csv2rdf.repositories.FileRefRepository;
import net.rhizomik.redefer.csv2rdf.repositories.RDFRequestRepository;
import net.rhizomik.redefer.csv2rdf.repositories.RdfRefRepository;
import net.rhizomik.redefer.csv2rdf.utils.CsvReader;

import net.rhizomik.redefer.csv2rdf.models.*;
import org.apache.commons.io.FileUtils;
import org.apache.jena.datatypes.xsd.XSDDatatype;
import org.apache.jena.rdf.model.*;
import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


@Service
public class RDFService {

    @Value("file.upload-dir")
    private String provisionalPath;

    @Autowired
    private RdfRefRepository rdfRefRepository;

    @Autowired
    private FileRefRepository fileRefRepository;

    @Autowired
    private RDFRequestRepository rdfRequestRepository;

    /**
     * Creates the RDF given a filerEF
     * @param fileRef the fileRef which has the info about the file
     * @param request the request containing the parameters to create the RDF
     * @return A model representing the RDF
     * @throws IOException if the file fails to get retrieved
     * @throws GeneralException if there is a problem with the datatype
     */
    public Model createRDFUser(FileRef fileRef, RDFRequest request) throws IOException, GeneralException {
        File file = new File(provisionalPath + File.separator + fileRef.getOriginalName());
        FileUtils.writeByteArrayToFile(file, fileRef.getFile());
        return createRDF(file, request);
    }

    /**
     * Creates the RDF given a filerEF
     * @param file the file to be transformed
     * @param request the request containing the parameters to create the RDF
     * @return A model representing the RDF
     * @throws IOException if the file fails to get retrieved
     * @throws GeneralException if there is a problem with the datatype
     */
    public Model createRDF(File file, RDFRequest request) throws IOException, GeneralException {
        Csv csv = CsvReader.convertFileToCsv(file);
        file.delete();

        Model model = ModelFactory.createDefaultModel();
        int subjectPosition = getSubjectPosition(request.subject, csv.headers);
        String uri = request.uri;
        if(uri.endsWith("/")){
            request.setUri(uri.substring(0, uri.length() - 1));
        }
        for(int i=0; i < csv.lines.length; i++) {
            Resource r = model.createResource(request.uri + '/'+ csv.lines[i][subjectPosition].replace(" ", "%20"));
            addProperties(r, csv.lines[i], model, subjectPosition, request.types, request.dataTypes, request.uri);
        }
        return model;
    }

    /**
     * Gets all the rdf of a user
     * @param user the user to get the rdf from
     * @return a FileList that contains all the rdf and csv
     */
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


    /**
     * Transforms the model to a string
     * @param model the model to be transformed
     * @param format the format in which the transformation must be
     * @return a String with the model
     */
    public String modelToString(Model model, Lang format) {
        StringWriter out = new StringWriter();
        RDFDataMgr.write(out, model, format);

        return out.toString();
    }

    /**
     * Transforms the model to a byte array
     * @param model the model
     * @param format the format in which has to be transformed
     * @return
     */
    public byte[] modelToByte(Model model, Lang format) {
        return modelToString(model, format).getBytes();
    }

    /**
     * Returns list of strings that contains the files data
     * @param file the filename
     * @param user the user
     * @return a list of strings that contain the files data
     */
    public List<String> getRequestedFiles(String file, User user)  {
        FileRef fileRef = fileRefRepository.findByOriginalNameAndUser(file, user);
        RdfRef rdfRef = rdfRefRepository.findByFileRef(fileRef);

        ArrayList<String> list = new ArrayList<>();
        list.add(new String(fileRef.getFile()));
        list.add(new String(rdfRef.getRDFFile()));
        return list;
    }

    /**
     * Saves the request to db associated to a user
     * @param request the request
     * @return the saved request
     */
    public RDFRequest saveRequestToDatabase(RdfRef rdfRef, RDFRequest request) {
        RDFRequest dbRequest = rdfRequestRepository.findByRdfRef(rdfRef);
        if(dbRequest == null){
            dbRequest = new RDFRequest();
            dbRequest = request;
        } else {
            dbRequest.setDataTypes(request.getDataTypes());
            dbRequest.setFormat(request.getFormat());
            dbRequest.setSubject(request.getSubject());
            dbRequest.setTypes(request.getTypes());
            dbRequest.setUri(request.getUri());
        }
        request.setRdfRef(rdfRef);
        return rdfRequestRepository.save(dbRequest);
    }

    private int getSubjectPosition(String subject, String[] headers) throws GeneralException {
        for(int i=0; i<headers.length; i++) {
            if (subject.equals(headers[i])){
                return i;
            }
        }
        throw new GeneralException("Subject doesn't correspond to any of the headers");
    }

    private void addProperties(Resource r, String[] lines, Model model, int subjectPosition, List<String> types, List<DataType> dataTypes, String requestUri) throws GeneralException {
        for(int j = 0; j < lines.length; j++) {
            if(j != subjectPosition && !lines[j].equals("")){
                createTypedLiteral(model, dataTypes.get(j), r, types.get(j), lines[j], requestUri);
            }
        }
    }



    private void createTypedLiteral(Model model, DataType dataType, Resource r, String propertyUri, String value, String requestUri) throws GeneralException {
        Literal l = selectDataTypeLiteral(model, value, dataType);
        Property property = model.createProperty(propertyUri);

        if(l == null) { // This is ugly. Try to change
            value = value.replace(" ", "%20");
            Resource objectResource;
            if(isValidUri(value)){
                objectResource = model.createResource(value);
            }else{
                objectResource = model.createResource(requestUri + value);
            }
            model.add(r, property, objectResource);
        } else {
            model.addLiteral(r, property, l);
        }

    }

    private Literal selectDataTypeLiteral(Model model, String value, DataType dataType) throws GeneralException {
        switch(dataType){
            case text:
                return model.createTypedLiteral(value, XSDDatatype.XSDstring);
            case _date:
                return model.createTypedLiteral(value, XSDDatatype.XSDdate);
            case _integer:
                return model.createTypedLiteral(Integer.valueOf(value), XSDDatatype.XSDint);
            case _boolean:
                return model.createTypedLiteral(Boolean.parseBoolean(value), XSDDatatype.XSDboolean);
            case NonInteger:
                return model.createTypedLiteral((Float.valueOf(value)));
                //return model.createTypedLiteral(Double.valueOf(value), XSDDatatype.XSDdecimal);
            case resource:
                return null;
            default:
                throw new GeneralException("DataType doesn't correspond to a parsejable type");
        }

    }

    /**
     * Gets a RDFRequest
     * @param fileName name of the file
     * @param user user owner of the file
     * @return The info of the editor
     */
    public RDFEditorInfoResponse getRDFRequest(String fileName, User user) {
        FileRef fileRef = fileRefRepository.findByOriginalNameAndUser(fileName, user);
        RdfRef rdfRef = rdfRefRepository.findByFileRef(fileRef);

        RDFRequest rdfRequest = rdfRequestRepository.findByRdfRef(rdfRef);

        return new RDFEditorInfoResponse(rdfRequest, fileRef.getFile(), fileName);
    }

    private boolean isValidUri(String uri) {
        try {
            URL url = new URL(uri);
            url.toURI();
            return true;
        } catch (Exception exception) {
            return false;
        }
    }
}
