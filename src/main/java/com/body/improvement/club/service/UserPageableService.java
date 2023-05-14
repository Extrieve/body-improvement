package com.body.improvement.club.service;

import com.body.improvement.club.entity.User;
import com.body.improvement.club.repository.PageableUserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class UserPageableService {

    private final PageableUserRepo pageableUserRepo;
    private final Logger logger = Logger.getLogger(UserPageableService.class.getName());

    public ResponseEntity<Page<User>> getAllUsers(int page, int size){
        logger.info("Fetching all users");

        try{
            PageRequest pageRequest = PageRequest.of(page, size);
            Page<User> users = pageableUserRepo.findAll(pageRequest);

            if (users.isEmpty()){
                logger.warning("No users found");
                return ResponseEntity.ok().body(users);
            }

            // 206 is a partial content response
            return ResponseEntity.status(206).body(users);

        } catch (Exception e) {
            logger.warning("Error fetching all users");
            return ResponseEntity.badRequest().build();
        }
    }

    public ResponseEntity<Page<User>> getUserByUsername(String username, int page, int size){
        logger.info("Fetching user by username: " + username);

        try{
            PageRequest pageRequest = PageRequest.of(page, size);
            Page<User> users = pageableUserRepo.findByUsernameContaining(username, pageRequest);

            if (users.isEmpty()){
                logger.warning("No users found with username: " + username);
                return ResponseEntity.ok().body(users);
            }

            // 206 is a partial content response
            return ResponseEntity.status(206).body(users);

        } catch (Exception e) {
            logger.warning("Error fetching user by username: " + username);
            return ResponseEntity.badRequest().build();
        }
    }

    public ResponseEntity<Page<User>> getUsersByFirstNameContaining(String firstName, int page, int size){
        logger.info("Fetching users by first name: " + firstName);

        try{
            PageRequest pageRequest = PageRequest.of(page, size);
            Page<User> users = pageableUserRepo.findByFirstNameContaining(firstName, pageRequest);

            if (users.isEmpty()){
                logger.warning("No users found with first name: " + firstName);
                return ResponseEntity.ok().body(users);
            }

            // 206 is a partial content response
            return ResponseEntity.status(206).body(users);

        } catch (Exception e) {
            logger.warning("Error fetching users by first name: " + firstName);
            return ResponseEntity.badRequest().build();
        }
    }

    public ResponseEntity<Page<User>> getUsersByLastNameContaining(String lastName, int page, int size){
        logger.info("Fetching users by last name: " + lastName);

        try{
            PageRequest pageRequest = PageRequest.of(page, size);
            Page<User> users = pageableUserRepo.findByLastNameContaining(lastName, pageRequest);

            if (users.isEmpty()){
                logger.warning("No users found with last name: " + lastName);
                return ResponseEntity.ok().body(users);
            }

            // 206 is a partial content response
            return ResponseEntity.status(206).body(users);

        } catch (Exception e) {
            logger.warning("Error fetching users by last name: " + lastName);
            return ResponseEntity.badRequest().build();
        }
    }


}
