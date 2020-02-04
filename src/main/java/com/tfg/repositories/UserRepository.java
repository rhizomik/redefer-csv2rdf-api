package com.tfg.repositories;

import com.tfg.models.security.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
    List<User> findByUsernameContaining(@Param("text") String text);
    User findByUsername(String username);
    User findByEmail(String email);
}
