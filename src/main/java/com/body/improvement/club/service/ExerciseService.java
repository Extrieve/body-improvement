package com.body.improvement.club.service;

import com.body.improvement.club.entity.Exercise;
import com.body.improvement.club.repository.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ExerciseService {

    @Autowired
    private ExerciseRepository exerciseRepository;


    public ResponseEntity<Collection<Exercise>> getAllExercises(){
        return ResponseEntity.ok(exerciseRepository.findAll());
    }


    public ResponseEntity<Exercise> getExerciseById(String exerciseId){
        return ResponseEntity.ok(exerciseRepository.findById(exerciseId).get());
    }
}
