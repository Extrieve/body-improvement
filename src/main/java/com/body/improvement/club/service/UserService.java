package com.body.improvement.club.service;

import com.body.improvement.club.entity.User;
import com.body.improvement.club.repository.UserRepository;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@Log4j
public class UserService implements ServiceDelegator{

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<User> getUserByUsername(String username){
        User payload = userRepository.findUserByUsername(username);
        return ResponseEntity.ok().body(payload);
    }

    public ResponseEntity<Collection<User>> getUsersByFirstNameAndLastName(String firstName, String lastName){
        Collection<User> payload = userRepository.findUserByFirstNameAndLastName(firstName, lastName);
        return ResponseEntity.ok().body(payload);
    }

    public ResponseEntity<Collection<User>> getUsersByFirstNameContaining(String firstName){
        Collection<User> payload = userRepository.findUserByFirstNameContaining(firstName);
        return ResponseEntity.ok().body(payload);
    }

    public ResponseEntity<Collection<User>> getAllUsers(){
        Collection<User> payload = userRepository.findAll();
        return ResponseEntity.ok(payload);
    }
}
