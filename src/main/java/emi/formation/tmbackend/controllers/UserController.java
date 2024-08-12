package emi.formation.tmbackend.controllers;


import emi.formation.tmbackend.dto.AuthResponse;
import emi.formation.tmbackend.dto.UserDTO;
import emi.formation.tmbackend.entities.User;
import emi.formation.tmbackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")

public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public User signUp(@RequestBody User user) {
        return  userService.signUpService(user);
    }


    @PostMapping("/signin")
    public User signIn(@RequestBody UserDTO signInRequest) {
        return userService.signInService(signInRequest);
    }

}
