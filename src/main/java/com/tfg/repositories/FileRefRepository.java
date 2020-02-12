package com.tfg.repositories;

import com.tfg.models.FileRef;
import com.tfg.models.security.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.io.File;

@Repository
public interface FileRefRepository extends CrudRepository<FileRef, Long> {
    FileRef findByOriginalName(String originalName);
    FileRef findByFile(File file);
    FileRef findByUser(User user);
}
