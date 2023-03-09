package com.body.improvement.club.controller;

import com.body.improvement.club.entity.Workout;
import com.body.improvement.club.service.UserService;
import com.body.improvement.club.service.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;

@RestController
public class WorkoutController {

    private final WorkoutService workoutService;

    private final UserService userService;

    public WorkoutController(WorkoutService workoutService, UserService userService) {
        this.workoutService = workoutService;
        this.userService = userService;
    }


    @GetMapping(path = "/workout/all")
    public ResponseEntity<Collection<Workout>> fetchAllWorkouts(){
        return workoutService.getAllWorkouts();
    }


    @GetMapping(path = "/workout/find/{workoutId}")
    public ResponseEntity<Workout> fetchWorkoutById(@PathVariable String workoutId){
        return workoutService.getWorkoutById(workoutId);
    }

    @GetMapping(path = "/workout/find/name/{workoutName}")
    public ResponseEntity<Map> fetchWorkoutByName(@PathVariable String workoutName){
        return workoutService.getWorkoutByName(workoutName);
    }

    @PostMapping(path = "/workout/save/{username}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Workout> saveWorkout(@RequestBody Workout workoutToSave, @PathVariable String username){
        return userService.saveWorkout(workoutToSave, username);
    }
}
