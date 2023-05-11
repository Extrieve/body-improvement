package com.body.improvement.club.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
@Table(name = "exercises")
public class Exercise {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "exercise_id")
    private String exerciseId;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    @Lob
    private String description;

    @Column(name = "rating")
    private Double rating;

    @Column(name = "created_at")
    private ZonedDateTime zonedDateTime;

    // Many-to-many relationship with Workout
    @ToString.Exclude
    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "exercises")
    private Collection<Workout> workouts = new ArrayList<>();

    // Many-to-one relationship with User
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "username")
    @JsonIgnore
    private User user;

    @Override
    public int hashCode(){
        return getClass().hashCode();
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;
        Exercise exercise = (Exercise) obj;
        return exerciseId != null && exerciseId.equals(exercise.getExerciseId());
    }

}
