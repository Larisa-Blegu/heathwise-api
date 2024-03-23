package com.healthwise.HealthwiseApp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    private String medicineDegree;

    @Column(nullable = false)
    private String description;

    @JsonIgnore
    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] image;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "DOCTOR_SPECIALIZATION",
            joinColumns = {
                @JoinColumn(name = "doctor_id", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                @JoinColumn(name = "specialization_id", referencedColumnName = "id")
            }
    )
    private List<Specialization> specializations = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "DOCTOR_LOCATION",
            joinColumns = {
                    @JoinColumn(name = "doctor_id", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "location_id", referencedColumnName = "id")
            }
    )
    private List<Location> locations = new ArrayList<>();
}
