package com.tfg.repositories;

import com.tfg.models.FileRef;
import com.tfg.models.RdfRef;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RdfRefRepository extends CrudRepository<RdfRef, Long> {
    RdfRef findByFileRef(FileRef fileRef);
}
