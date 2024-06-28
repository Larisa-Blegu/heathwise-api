package com.healthwise.HealthwiseApp.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "DOCTOR_USER")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DoctorUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public DoctorUser (Doctor doctor, User user){
        this.doctor = doctor;
        this.user = user;
    }
}
