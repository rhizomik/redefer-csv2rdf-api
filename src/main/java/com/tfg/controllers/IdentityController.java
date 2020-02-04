package com.tfg.controllers;

import com.tfg.exceptions.GeneralException;
import com.tfg.models.GenericResponse;
import com.tfg.models.security.User;
import com.tfg.repositories.UserRepository;
import com.tfg.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class IdentityController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @RequestMapping("/identity")
    public @ResponseBody String getAuthenticatedUserIdentity(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getUsername();
    }

    @PostMapping("/register")
    public @ResponseBody
    GenericResponse registerUser(@Valid User user, HttpServletRequest request) {
        try {
            userService.registerNewUserAccount(user);
        } catch (GeneralException e) {
            return new GenericResponse("", "User or email already exist");
        }

        return new GenericResponse("User creation success", "");
    }
}
