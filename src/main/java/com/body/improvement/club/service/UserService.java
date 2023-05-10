package com.body.improvement.club.service;

import com.body.improvement.club.entity.Exercise;
import com.body.improvement.club.entity.User;
import com.body.improvement.club.entity.Workout;
import com.body.improvement.club.error.UserNotFoundError;
import com.body.improvement.club.repository.UserRepository;
import com.body.improvement.club.utility.GeneralUtility;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


@Service
@RequiredArgsConstructor
@DynamicUpdate
@DynamicInsert
public class UserService implements ServiceDelegator{

    private final UserRepository userRepository;
    private final GeneralUtility generalUtility;
    private final Logger logger = LogManager.getLogger(UserService.class);

    public ResponseEntity<User> getUserByUsername(String username){
        logger.info("Fetching user by username: " + username);

        try{
            User user = userRepository.findUserByUsername(username);
            if (user == null){
                logger.warn("No user with username: " + username);
                throw new UserNotFoundError(username);
            }
            return ResponseEntity.ok().body(user);
        } catch (Exception e) {

            logger.error("An error occurred while fetching user by username: " + username);
            return ResponseEntity.notFound().build();

        }
    }

    public ResponseEntity<Collection<User>> getUsersByFirstNameAndLastName(String firstName, String lastName){
        logger.info("Fetching users by first name: " + firstName + " and last name: " + lastName);

        try{
            Collection<User> users = userRepository.findUserByFirstNameAndLastName(firstName, lastName);

            if (users == null){
                logger.warn("No users with first name: " + firstName + " and last name: " + lastName);
                return ResponseEntity.ok().body(null);
            }

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

            if (users == null){
                logger.warn("No users with first name: " + firstName);
                return ResponseEntity.ok().body(null);
            }

            return ResponseEntity.ok(users);
        } catch (Exception e) {
            logger.error("No users with first name: " + firstName);
            return ResponseEntity.notFound().build();
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

    @Transactional
    public ResponseEntity<Object> saveUser(User user){
        logger.info("Saving user: " + user.getUsername());
        try {
            if (user.getUsername().isEmpty() || user.getUsername().length() < 3) {
                logger.error("Username must be at least 3 characters");
                Map<String, String> error = new HashMap<>(
                        Map.of("error", "Username must be at least 3 characters")
                );
                return ResponseEntity.badRequest().body(error);
            }

            User payload = userRepository.save(user);

            // Establish relationship between user and workout
            if(!payload.getWorkouts().isEmpty()){
            payload.getWorkouts().forEach(workout -> workout.setUser(payload));
            }

            if (!payload.getExercises().isEmpty()) {
                payload.getExercises().forEach(exercise -> exercise.setUser(payload));
            }

            return ResponseEntity.status(201).body(payload);

        } catch (Exception e) {

            logger.error("Error saving user: " + user.getUsername());
            return ResponseEntity.badRequest().build();

        }

    }

    @Transactional
    public ResponseEntity<User> updateUser(User updatedUser, String username) {
        User user = userRepository.findById(username).orElse(null);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        updatedUser.getWorkouts().forEach(workout -> workout.setUser(user));
        user.updateWorkouts(updatedUser.getWorkouts());

        updatedUser.getExercises().forEach(exercise -> exercise.setUser(user));
        user.updateExercises(updatedUser.getExercises());

        BeanUtils.copyProperties(updatedUser, user, generalUtility.getNullPropertyNames(updatedUser));
        User savedUser = userRepository.save(user);
        return ResponseEntity.ok(savedUser);
    }

    @Transactional
    public ResponseEntity<Workout> saveWorkout(Workout newWorkout, String username){
        logger.info("Saving workout: " + newWorkout.getName() + " for user: " + username);
        User user = userRepository.findUserByUsername(username);

        if(user == null){
            logger.error("User not found");
            return ResponseEntity.noContent().build();
        }

        newWorkout.setUser(user);
        user.getWorkouts().add(newWorkout);

        return ResponseEntity.ok().body(newWorkout);
    }


    @Transactional
    public ResponseEntity<Exercise> saveExercise(Exercise newExercise, String username){
        logger.info("Saving exercise: " + newExercise.getName() + " for user: " + username);
        User user = userRepository.findUserByUsername(username);

        if(user == null){
            logger.error("User not found");
            return ResponseEntity.badRequest().build();
        }

        newExercise.setUser(user);
        user.getExercises().add(newExercise);

        // 201 Created
        return ResponseEntity.status(201).body(newExercise);
    }

    @Transactional
    public ResponseEntity<Object> deleteUser(String username){
        logger.info("Deleting user: " + username);
        User user = userRepository.findUserByUsername(username);

        if(user == null){
            logger.error("User not found");
            return ResponseEntity.badRequest().build();
        }
        userRepository.delete(user);
        return ResponseEntity.ok().build();
    }

}
