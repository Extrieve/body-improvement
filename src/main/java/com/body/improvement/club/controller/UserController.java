package com.body.improvement.club.controller;

import com.body.improvement.club.entity.User;
import com.body.improvement.club.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(path = "all", produces = "application/json")
    public ResponseEntity<Collection<User>> fetchAllUsers(){
        return userService.getAllUsers();
    }


    @GetMapping(path = "user/{username}")
    public ResponseEntity<User> fetchUserByUsername(@PathVariable String username){
        return userService.getUserByUsername(username);
    }
}
