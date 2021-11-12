package net.rhizomik.redefer.csv2rdf.services;

import net.rhizomik.redefer.csv2rdf.models.FileRef;
import net.rhizomik.redefer.csv2rdf.models.RdfRef;
import net.rhizomik.redefer.csv2rdf.models.security.User;
import net.rhizomik.redefer.csv2rdf.repositories.FileRefRepository;
import net.rhizomik.redefer.csv2rdf.repositories.RdfRefRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class FileUploadService {

    @Autowired
    private FileRefRepository fileRefRepository;

    @Autowired
    private RdfRefRepository rdfRefRepository;

    @Autowired
    private UserService userService;

    /**
     * Saves the csv to the database
     * @param bytes the byteFile
     * @param fileName the name of the file
     * @param username the username of the user
     * @return a FileRef
     */
    public FileRef saveCsvToDatabase(byte[] bytes, String fileName, String username) {
        User user = (User) userService.loadUserByUsername(username);
        FileRef fileRef = fileRefRepository.findByOriginalNameAndUser(fileName, user);
        if(fileRef == null){
            fileRef = new FileRef();
            fileRef.setFile(bytes);
            fileRef.setOriginalName(fileName);
            fileRef.setUser(user);
            return fileRefRepository.save(fileRef);
        }
        return fileRef;
    }

    /**
     * Saves the rdf to the database
     * @param rdfBytes the byteFile
     * @param username the username of the user
     * @param fileName the name of the file
     * @param format the format in which is stored
     */
    public RdfRef saveRDFToDatabase(byte[] rdfBytes, String username, String fileName, String format) {
        User user = (User) userService.loadUserByUsername(username);
        FileRef fileRef = fileRefRepository.findByOriginalNameAndUser(fileName, user);

        RdfRef rdfRef = rdfRefRepository.findByFileRef(fileRef);
        if(rdfRef == null) {
            rdfRef = new RdfRef();
        }
        rdfRef.setRDFFile(rdfBytes);
        rdfRef.setFileRef(fileRef);
        rdfRef.setFormat(format);
        return rdfRefRepository.save(rdfRef);
    }
}
