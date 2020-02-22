package com.tfg.repositories;

import com.tfg.models.FileRef;
import com.tfg.models.security.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.List;

@Repository
public interface FileRefRepository extends CrudRepository<FileRef, Long> {
    FileRef findByOriginalNameAndUser(String originalName, User user);
    FileRef findByFile(byte[] file);
    FileRef findByUser(User user);
    List<FileRef> findAllByUser(User user);
}
