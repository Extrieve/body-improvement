package com.body.improvement.club.entity;

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

    // Many to many relationship with Exercise
    @ToString.Exclude
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Collection<Exercise> exercises = new ArrayList<>();

    // Many to one relationship with User
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Override
    public int hashCode(){
        return getClass().hashCode();
    }
}
