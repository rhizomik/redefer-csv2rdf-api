package com.tfg.services;

import com.tfg.exceptions.GeneralException;
import com.tfg.models.security.User;
import com.tfg.repositories.UserRepository;
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findById(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    }

    @Transactional
    public User registerNewUserAccount(User user) throws GeneralException {
        if(userRepository.findByUsername(user.getUsername()) != null) {
            throw new GeneralException("Username already exists");
        } else if(userRepository.findByEmail(user.getEmail()) != null) {
            throw new GeneralException("Email already exists");
        }

        return userRepository.save(user);
    }

}
