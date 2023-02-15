package com.body.improvement.club.service;

import com.body.improvement.club.entity.User;
import com.body.improvement.club.repository.PageableUserRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class UserPageableService {

    private final PageableUserRepo pageableUserRepo;

    private final Logger logger = Logger.getLogger(UserPageableService.class.getName());

    public UserPageableService(PageableUserRepo pageableUserRepo) {
        this.pageableUserRepo = pageableUserRepo;
    }

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


}