package com.healthwise.HealthwiseApp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class ReviewDTO {
    private int id;
    private int grade;
    private String text;
    private String doctor;
}
