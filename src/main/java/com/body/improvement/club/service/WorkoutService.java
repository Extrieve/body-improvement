package com.body.improvement.club.service;

import com.body.improvement.club.entity.Workout;
import com.body.improvement.club.repository.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class WorkoutService {

    @Autowired
    private WorkoutRepository workoutRepository;

    // TODO: Implement the rest of the service class.
    public ResponseEntity<Collection<Workout>> fetchAllWorkouts(){
        return ResponseEntity.ok().body(null);
    }
}
