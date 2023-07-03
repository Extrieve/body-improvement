package com.body.improvement.club.controller;

import com.body.improvement.club.entity.User;
import com.body.improvement.club.service.UserPageableService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/")
@CrossOrigin(origins = "http://127.0.0.1:5173/", allowedHeaders = "*")
public class UserPageableController {

    private final UserPageableService userPageableService;

     @GetMapping(path = "/all", produces = "application/json")
    public ResponseEntity<Page<User>> findAllUsers(@RequestParam int page, @RequestParam int size){
        return userPageableService.getAllUsers(page, size);
    }

    @GetMapping(path = "/find/{username}", produces = "application/json")
    public ResponseEntity<Page<User>> findUserByUsername(@PathVariable String username, @RequestParam int page, @RequestParam int size){
        return userPageableService.getUserByUsername(username, page, size);
    }

    @GetMapping(path = "/find/{firstName}", produces = "application/json")
    public ResponseEntity<Page<User>> findUserByFirstName(@PathVariable String firstName, @RequestParam int page, @RequestParam int size){
        return userPageableService.getUsersByFirstNameContaining(firstName, page, size);
    }

    @GetMapping(path = "/find/{lastName}", produces = "application/json")
    public ResponseEntity<Page<User>> findUserByLastName(@PathVariable String lastName, @RequestParam int page, @RequestParam int size){
        return userPageableService.getUsersByLastNameContaining(lastName, page, size);
    }
}
