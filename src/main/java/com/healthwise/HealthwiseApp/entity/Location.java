package com.healthwise.HealthwiseApp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String hospital;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String adress;

    @JsonIgnore
    @ManyToMany(mappedBy = "locations", fetch = FetchType.LAZY)
    private List<Doctor> doctors = new ArrayList<>();
}