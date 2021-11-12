package net.rhizomik.redefer.csv2rdf.repositories;

import net.rhizomik.redefer.csv2rdf.models.security.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

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
