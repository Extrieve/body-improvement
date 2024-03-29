package com.body.improvement.club.controller;

import com.body.improvement.club.entity.Exercise;
import com.body.improvement.club.entity.User;
import com.body.improvement.club.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://127.0.0.1:5173/", allowedHeaders = "*")
public class UserController {

    private final UserService userService;

    @GetMapping(path = {"/", "/home"})
    public String home() {
        return "The API is up and running";
    }

    @GetMapping(path = "/user/all", produces = "application/json")
    public ResponseEntity<Collection<User>> fetchAllUsers(){
        Collection<User> users = userService.getAllUsers().getBody();
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

    @GetMapping(path = "/user/find/name", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<User>> fetchUsersByFirstNameAndLastName(
            @RequestBody Map<String, String> userInfo){

        String firstName = userInfo.get("firstName");
        String lastName = userInfo.get("lastName");

        return userService.getUsersByFirstNameAndLastName(firstName, lastName);
    }

    @GetMapping(path = "/user/find/name/{firstName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<User>> fetchUsersByFirstNameLike(
            @PathVariable String firstName
    ){
        return userService.getUsersByFirstNameContaining(firstName);
    }

    @PostMapping(path = "/user/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> saveUser(@RequestBody User user){
        return userService.saveUser(user);
    }

    @PostMapping(path = "/user/saveBatch", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> saveUsers(@RequestBody Collection<User> users){
        return userService.batchSaveUsers(users);
    }

    @PostMapping(path = "/exercise/save/{username}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Exercise> saveExercise(@RequestBody Exercise exercise, @PathVariable String username){
        return userService.saveExercise(exercise, username);
    }

    @PutMapping(path = "/user/update/{username}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable String username){
        return userService.updateUser(user, username);
    }

    @DeleteMapping(path = "/user/delete/{username}")
    public ResponseEntity<Object> deleteUser(@PathVariable String username){
        return userService.deleteUser(username);
    }

    // TODO: Implement the delete mapping for workouts and exercises

}
