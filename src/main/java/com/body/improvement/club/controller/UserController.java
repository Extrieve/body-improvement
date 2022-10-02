package com.body.improvement.club.controller;

import com.body.improvement.club.entity.User;
import com.body.improvement.club.service.AttachmentService;
import com.body.improvement.club.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AttachmentService attachmentService;

    @GetMapping(path = "")
    public String home() {
        return "THIS IS WORKING";
    }

    @GetMapping(path = "all", produces = "application/json")
    public ResponseEntity<Collection<User>> fetchAllUsers(){
        return userService.getAllUsers();
    }


    @GetMapping(path = "user/{username}")
    public ResponseEntity<User> fetchUserByUsername(@PathVariable String username){
        return userService.getUserByUsername(username);
    }

    @PostMapping(path = "user/save", consumes = "application/json", produces = "application/json")
    public ResponseEntity<User> saveUser(@RequestBody User user){
        return userService.saveUser(user);
    }

}
