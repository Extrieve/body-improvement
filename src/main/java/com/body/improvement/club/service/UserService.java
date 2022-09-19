package com.body.improvement.club.service;

import com.body.improvement.club.entity.User;
import com.body.improvement.club.repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UserService implements ServiceDelegator{

    @Autowired
    private UserRepository userRepository;

    private final Logger logger = LogManager.getLogger(UserService.class);

    public ResponseEntity<User> getUserByUsername(String username){
        logger.info("Fetching user by username: " + username);

        try{
            User user = userRepository.findUserByUsername(username);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            logger.error("Error fetching user by username: " + username);
            return ResponseEntity.badRequest().build();
        }
    }

    public ResponseEntity<Collection<User>> getUsersByFirstNameAndLastName(String firstName, String lastName){
        logger.info("Fetching users by first name: " + firstName + " and last name: " + lastName);

        try{
            Collection<User> users = userRepository.findUserByFirstNameAndLastName(firstName, lastName);
            return ResponseEntity.ok(users);
        } catch (Exception e) {
            logger.error("Error fetching users by first name: " + firstName + " and last name: " + lastName);
            return ResponseEntity.badRequest().build();
        }
    }

    public ResponseEntity<Collection<User>> getUsersByFirstNameContaining(String firstName){
        logger.info("Fetching users by first name: " + firstName);

        try{
            Collection<User> users = userRepository.findUserByFirstNameContaining(firstName);
            return ResponseEntity.ok(users);
        } catch (Exception e) {
            logger.error("Error fetching users by first name: " + firstName);
            return ResponseEntity.badRequest().build();
        }

    }

    public ResponseEntity<Collection<User>> getAllUsers(){
        logger.info("Fetching all users");
        Collection<User> payload = userRepository.findAll();

        if(payload.isEmpty()){
            logger.error("No users found");
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(payload);
    }

    public ResponseEntity<Collection<User>> getUserByUsernameContaining(String username){
        logger.info("Fetching users by username: " + username);
        Collection<User> payload = userRepository.findUserByUsernameContaining(username);

        if(payload.isEmpty()){
            logger.error("No users found");
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok().body(payload);
    }
}
