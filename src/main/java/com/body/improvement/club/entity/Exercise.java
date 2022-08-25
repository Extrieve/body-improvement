package com.body.improvement.club.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Exercise {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "exercise_id")
    private Long exerciseId;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    @Lob
    private String description;

    @Column(name = "rating")
    private Double rating;

    @Column(name = "created_at")
    private ZonedDateTime zonedDateTime;

    @Override
    public int hashCode(){
        return getClass().hashCode();
    }

}
