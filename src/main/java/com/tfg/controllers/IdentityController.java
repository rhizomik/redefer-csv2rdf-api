package com.tfg.controllers;

import com.tfg.exceptions.GeneralException;
import com.tfg.models.security.User;
import com.tfg.repositories.UserRepository;
import com.tfg.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;



@BasePathAwareController
@RequestMapping("/api")
public class IdentityController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @RequestMapping("/identity")
    public @ResponseBody User getAuthenticatedUserIdentity() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user;
    }

    @RequestMapping(value = "/register", produces = "application/json")
    public @ResponseBody
    User registerUser(User user) throws GeneralException {
        User registeredUser = userService.registerNewUserAccount(user);
        return registeredUser;
    }
}
