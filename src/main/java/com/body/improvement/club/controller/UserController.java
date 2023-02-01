package com.body.improvement.club.controller;

import com.body.improvement.club.entity.Exercise;
import com.body.improvement.club.entity.User;
import com.body.improvement.club.service.AttachmentService;
import com.body.improvement.club.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@RestController
public class UserController {

    Logger logger = Logger.getLogger(UserController.class.getName());

    private static final User DEFAULT_USER = new User();
    private static final List<User> DEFAULT_USER_LIST = null;

    @Autowired
    private UserService userService;

    @Autowired
    private AttachmentService attachmentService;

    @GetMapping(path = "/home")
    public String home() {
        return "THIS IS WORKING";
    }

    @GetMapping(path = "/user/all", produces = "application/json")
    public ResponseEntity<Collection<User>> fetchAllUsers(){
        Collection<User> users = userService.getAllUsers().getBody();
        if (users == null){
            logger.info("No users found");
            return ResponseEntity.ok(DEFAULT_USER_LIST);
        }
        return ResponseEntity.ok(users);
    }


    @GetMapping(path = "/user/find/{username}")
    public ResponseEntity<User> fetchUserByUsername(@PathVariable String username){
        return userService.getUserByUsername(username);
    }

    @GetMapping(path = "/user/find/like/{username}")
    public ResponseEntity<Collection<User>> fetchUsersByUsernameLike(@PathVariable String username){
        return userService.getUserByUsernameContaining(username);
    }

    @GetMapping(path = "/user/find/name", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Collection<User>> fetchUsersByFirstNameAndLastName(
            @RequestBody Map<String, String> userInfo){

        String firstName = userInfo.get("firstName");
        String lastName = userInfo.get("lastName");

        return userService.getUsersByFirstNameAndLastName(firstName, lastName);
    }

    @GetMapping(path = "/user/find/name/{firstName}", produces = "application/json")
    public ResponseEntity<Collection<User>> fetchUsersByFirstNameLike(
            @PathVariable String firstName
    ){
        return userService.getUsersByFirstNameContaining(firstName);
    }

    @PostMapping(path = "/user/save", consumes = "application/json", produces = "application/json")
    public ResponseEntity<User> saveUser(@RequestBody User user){
        return userService.saveUser(user);
    }

    @PostMapping(path = "/exercise/save/{username}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Exercise> saveExercise(@RequestBody Exercise exercise, @PathVariable String username){
        return userService.saveExercise(exercise, username);
    }

    @PutMapping(path = "/user/update/", consumes = "application/json", produces = "application/json")
    public ResponseEntity<User> updateUser(@RequestBody User user){
        return userService.updateUser(user);
    }

    @DeleteMapping(path = "/user/delete/{username}")
    public ResponseEntity<Object> deleteUser(@PathVariable String username){
        return userService.deleteUser(username);
    }

}
