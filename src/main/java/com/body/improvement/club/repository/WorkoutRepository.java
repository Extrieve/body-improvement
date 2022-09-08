package com.body.improvement.club.repository;

import com.body.improvement.club.entity.Workout;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkoutRepository extends JpaRepository<Workout, String> {

}
