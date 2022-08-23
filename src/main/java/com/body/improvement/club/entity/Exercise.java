package com.body.improvement.club.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
public class Exercise {

    @Id
    @Column(name = "exercise_id")
    private Long exerciseId;

    @Column(name = "name")
    private String name;
}
