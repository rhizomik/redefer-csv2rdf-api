package com.tfg.repositories;

import com.tfg.models.security.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, String> {

    /**
     * Finds a user by it's username
     * @param username the username
     * @return the user that matches the search
     */
    User findByUsername(String username);

    /**
     * Fins the user by it's email
     * @param email the email
     * @return the user that matches the search
     */
    User findByEmail(String email);
}
