package com.body.improvement.club.service;

import com.body.improvement.club.entity.Workout;
import com.body.improvement.club.repository.WorkoutRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class WorkoutService {


    private static final Workout NULL_WORKOUT = new Workout();
    private final WorkoutRepository workoutRepository;

    private final Logger logger = LogManager.getLogger(WorkoutService.class);

    public WorkoutService(WorkoutRepository workoutRepository) {
        this.workoutRepository = workoutRepository;
    }

    // TODO: Implement the rest of the service class.
    public ResponseEntity<Collection<Workout>> getAllWorkouts(){

        logger.info("Fetching all workout plans.");

        Collection<Workout> payload = workoutRepository.findAll();

        if(payload.size() <  1)
            logger.info("There are no workouts available.");

        return ResponseEntity.ok().body(payload);
    }


    public ResponseEntity<Workout> getWorkoutById(String workoutId){

        logger.info("Fetching workout with workout id: " + workoutId);
        try {
            return ResponseEntity.ok().body(workoutRepository.findById(workoutId).get());
        }
        catch (RuntimeException e) {
            logger.warn("No workout with id: " + workoutId);
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<Map> getWorkoutByName(String workoutName){

        logger.info("Fetching workout with workout name: " + workoutName);
        try {
            Workout payload = workoutRepository.findByName(workoutName).stream().findFirst().get();
            return ResponseEntity.ok().body(payload.toMap());
        }
        catch (RuntimeException e) {
            logger.warn("No workout with name: " + workoutName);
            Map<String, String> payload = new HashMap<>();
            payload.put("error", "No workout with name: " + workoutName);
            return ResponseEntity.ok().body(payload);
        }
    }
}
