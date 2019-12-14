package com.tfg.repositories;

import com.tfg.models.Triplets;
import org.springframework.data.repository.CrudRepository;

public interface TripletsRepository extends CrudRepository<Triplets, Long> {
    Triplets findByUser(String username);
}
