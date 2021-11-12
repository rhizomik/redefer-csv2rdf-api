package net.rhizomik.redefer.csv2rdf.repositories;

import net.rhizomik.redefer.csv2rdf.models.FileRef;
import net.rhizomik.redefer.csv2rdf.models.security.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileRefRepository extends CrudRepository<FileRef, Long> {
    /**
     * Finds the fileRef by it's originalName and user
     * @param originalName the originalName
     * @param user the username
     * @return the FileRef that matches the search
     */
    FileRef findByOriginalNameAndUser(String originalName, User user);

    /**
     * Finds all the FileRef of a user
     * @param user the user
     * @return all the fileRefs
     */
    List<FileRef> findAllByUser(User user);
}
