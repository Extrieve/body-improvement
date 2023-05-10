package com.body.improvement.club.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
@Table(name = "users")
public class User {

    @Id
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "body_weight")
    private String bodyWeight;

    @Column(name = "height")
    private String height;

    @Column(name = "about_me", length = 1000)
    private String aboutMe;

    @ToString.Exclude
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Collection<Workout> workouts = new ArrayList<>();

    @ToString.Exclude
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Collection<Exercise> exercises = new ArrayList<>();

    @Override
    public int hashCode(){
        return getClass().hashCode();
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;
        User user = (User) obj;
        return username.equals(user.username);
    }

    public void updateWorkouts(Collection<Workout> newWorkouts) {
        // Remove any existing Workout objects that are not in the new collection
        workouts.removeIf(existingWorkout -> !newWorkouts.contains(existingWorkout));

        // Add any new Workout objects that are not in the existing collection
        newWorkouts.stream()
                .filter(newWorkout -> !workouts.contains(newWorkout))
                .forEach(workouts::add);

        // Update any existing Workout objects in the collection
        workouts.stream()
                .filter(newWorkouts::contains)
                .forEach(existingWorkout -> {
                    Workout newWorkout = newWorkouts.stream()
                            .filter(w -> w.equals(existingWorkout))
                            .findFirst().orElse(null);
                    if (newWorkout != null) {
                        existingWorkout.setName(newWorkout.getName());
                        existingWorkout.setExercises(newWorkout.getExercises());
                        // Set current User object as the owner of the Workout object
                        existingWorkout.setUser(this);
                    }
                });
    }

    public void updateExercises(Collection<Exercise> newExercises) {
        // Remove any existing Exercise objects that are not in the new collection
        exercises.removeIf(existingExercise -> !newExercises.contains(existingExercise));

        // Add any new Exercise objects that are not in the existing collection
        newExercises.stream()
                .filter(newExercise -> !exercises.contains(newExercise))
                .forEach(exercises::add);

        // Update any existing Exercise objects in the collection
        exercises.stream()
                .filter(newExercises::contains)
                .forEach(existingExercise -> {
                    Exercise newExercise = newExercises.stream()
                            .filter(e -> e.equals(existingExercise))
                            .findFirst().orElse(null);
                    if (newExercise != null) {
                        existingExercise.setName(newExercise.getName());
                        // Set current User object as the owner of the Exercise object
                        existingExercise.setUser(this);
                    }
                });
    }
}
