package com.body.improvement.club.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
@Table(name = "workouts")
public class Workout {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "workout_id")
    private String workoutId;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    @Lob
    private String description;

    @Column(name = "rating")
    private Double rating;

    @Column(name = "created_at")
    private ZonedDateTime zonedDateTime;

    // Many-to-many relationship with Exercise
    @ToString.Exclude
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "workout_exercise",
            joinColumns = @JoinColumn(name = "workout_id"),
            inverseJoinColumns = @JoinColumn(name = "exercise_id"))
    private Collection<Exercise> exercises = new ArrayList<>();

    // Many-to-one relationship with User
    @ToString.Exclude
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "username")
    private User user;

    @Override
    public int hashCode(){
        return getClass().hashCode();
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;
        Workout workout = (Workout) obj;
        return workoutId != null && workoutId.equals(workout.getWorkoutId());
    }

    public Map<String, Object> toMap() {
        return Map.of(
                "workoutId", workoutId,
                "name", name,
                "description", description,
                "rating", rating,
                "zonedDateTime", zonedDateTime,
                "exercises", exercises,
                "user", user
        );
    }
}
