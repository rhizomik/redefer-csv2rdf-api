package net.rhizomik.redefer.csv2rdf.controllers;

import net.rhizomik.redefer.csv2rdf.exceptions.GeneralException;
import net.rhizomik.redefer.csv2rdf.models.security.User;
import net.rhizomik.redefer.csv2rdf.repositories.UserRepository;
import net.rhizomik.redefer.csv2rdf.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Manages the user calls
 */
@BasePathAwareController
@RequestMapping("/api")
public class IdentityController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @RequestMapping("/identity")
    public @ResponseBody
    User getAuthenticatedUserIdentity() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user;
    }

    @RequestMapping(value = "/register", produces = "application/json")
    public @ResponseBody
    User registerUser(User user) throws GeneralException {
        return userService.registerNewUserAccount(user);
    }
}
