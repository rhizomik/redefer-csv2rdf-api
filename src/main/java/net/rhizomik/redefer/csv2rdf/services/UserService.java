package net.rhizomik.redefer.csv2rdf.services;

import net.rhizomik.redefer.csv2rdf.exceptions.GeneralException;
import net.rhizomik.redefer.csv2rdf.models.security.User;
import net.rhizomik.redefer.csv2rdf.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    /**
     * Loads the user by giving a username
     * @param username the username you want to load
     * @return the user
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findById(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    }

    /**
     * Registers a new user
     * @param user the user that you want to register
     * @return the user registered
     * @throws GeneralException if the user or email already exists
     */
    @Transactional
    public User registerNewUserAccount(User user) throws GeneralException {
        if(userRepository.findByUsername(user.getUsername()) != null) {
            throw new GeneralException("Username already exists");
        } else if(userRepository.findByEmail(user.getEmail()) != null) {
            throw new GeneralException("Email already exists");
        }
        user.encodePassword();
        return userRepository.save(user);
    }

}
