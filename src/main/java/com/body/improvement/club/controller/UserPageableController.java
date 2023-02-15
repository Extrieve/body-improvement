package com.body.improvement.club.controller;

import com.body.improvement.club.entity.User;
import com.body.improvement.club.service.UserPageableService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2/")
@CrossOrigin(origins = "http://127.0.0.1:5173/", allowedHeaders = "*")
public class UserPageableController {

    private static final User DEFAULT_USER = new User();

    private final UserPageableService userPageableService;

    public UserPageableController(UserPageableService userPageableService) {
        this.userPageableService = userPageableService;
    }

     @GetMapping(path = "/all", produces = "application/json")
    public ResponseEntity<Page<User>> findAllUsers(@RequestParam int page, @RequestParam int size){
        logger.info("Fetching all users");
        return userPageableService.getAllUsers(page, size);
    }
}
