package com.body.improvement.club.controller;

import com.body.improvement.club.entity.Workout;
import com.body.improvement.club.service.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class WorkoutController {

    @Autowired
    private WorkoutService workoutService;


    @GetMapping(path = "/workout/all")
    public ResponseEntity<Collection<Workout>> fetchAllWorkouts(){
        return workoutService.getAllWorkouts();
    }


    @GetMapping(path = "/workout/find/{workoutId}")
    public ResponseEntity<Workout> fetchWorkoutById(@PathVariable String workoutId){
        return workoutService.getWorkoutById(workoutId);
    }

    @GetMapping(path = "/workout/find/name/{workoutName}")
    public ResponseEntity<Workout> fetchWorkoutByName(@PathVariable String workoutName){
        return workoutService.getWorkoutByName(workoutName);
    }
}
