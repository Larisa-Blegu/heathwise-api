package com.healthwise.HealthwiseApp.dto;

import com.healthwise.HealthwiseApp.util.enums.AppointmentReviewStatus;
import com.healthwise.HealthwiseApp.util.enums.AppointmentStatus;
import com.healthwise.HealthwiseApp.util.enums.AppointmentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
public class AppointmentDTO {

    private int id;
    private LocalDateTime date;
    private AppointmentType type;
    private String location;
    private String doctor;
    private String medicalProcedure;
    private String user;
    private AppointmentStatus status;
    private AppointmentReviewStatus reviewStatus;
}
