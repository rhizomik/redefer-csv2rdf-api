package net.rhizomik.redefer.csv2rdf.repositories;

import net.rhizomik.redefer.csv2rdf.models.RDFRequest;
import net.rhizomik.redefer.csv2rdf.models.RdfRef;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RDFRequestRepository extends CrudRepository<RDFRequest, Long> {
    RDFRequest findByRdfRef(RdfRef rdfRef);
}
