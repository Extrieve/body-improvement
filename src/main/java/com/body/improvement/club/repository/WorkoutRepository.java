package com.body.improvement.club.repository;

import com.body.improvement.club.entity.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface WorkoutRepository extends JpaRepository<Workout, String> {

    Collection<Workout> findByName(String workoutName);

}
