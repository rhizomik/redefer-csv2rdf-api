package com.tfg.repositories;

import com.tfg.models.RDFRequest;
import com.tfg.models.RdfRef;
import com.tfg.models.security.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RDFRequestRepository extends CrudRepository<RDFRequest, Long> {
    RDFRequest findByRdfRef(RdfRef rdfRef);
}
