package com.body.improvement.club.service;

import com.body.improvement.club.entity.Workout;
import com.body.improvement.club.repository.WorkoutRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class WorkoutService {


    private static final Workout NULL_WORKOUT = new Workout();
    @Autowired
    private WorkoutRepository workoutRepository;

    private final Logger logger = LogManager.getLogger(WorkoutService.class);

    // TODO: Implement the rest of the service class.
    public ResponseEntity<Collection<Workout>> fetchAllWorkouts(){

        logger.info("Fetching all workout plans.");

        Collection<Workout> payload = workoutRepository.findAll();

        if(payload.size() <  1)
            logger.info("There are no workouts available.");

        return ResponseEntity.ok().body(payload);
    }


    public ResponseEntity<Workout> fetchWorkoutById(String workoutId){

        logger.info("Fetching workout with workout id: " + workoutId);
        try {
            return ResponseEntity.ok().body(workoutRepository.findById(workoutId).get());
        }
        catch (RuntimeException e) {
            logger.warn("No workout with id: " + workoutId);
            return ResponseEntity.badRequest().body(NULL_WORKOUT);
        }
    }
}
