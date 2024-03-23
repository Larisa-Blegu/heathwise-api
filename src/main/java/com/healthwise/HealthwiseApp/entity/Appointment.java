package com.healthwise.HealthwiseApp.entity;

import com.healthwise.HealthwiseApp.util.enums.AppointmentReviewStatus;
import com.healthwise.HealthwiseApp.util.enums.AppointmentStatus;
import com.healthwise.HealthwiseApp.util.enums.AppointmentType;
import com.healthwise.HealthwiseApp.util.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @DateTimeFormat
    @Column(nullable = false, unique = true)
    private LocalDateTime date;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "VARCHAR(255) DEFAULT 'PHYSICAL'")
    private AppointmentType type;

    @ManyToOne
    @JoinColumn(name = "location_id", referencedColumnName = "id")
    private Location location;

    @ManyToOne
    @JoinColumn(name = "doctor_id", referencedColumnName = "id")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "procedure_id", referencedColumnName = "id")
    private MedicalProcedure medicalProcedure;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "VARCHAR(255) DEFAULT 'PENDING'")
    private AppointmentStatus status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "VARCHAR(255) DEFAULT 'FALSE'")
    private AppointmentReviewStatus reviewStatus;
}
