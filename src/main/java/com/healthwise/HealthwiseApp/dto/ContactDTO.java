package com.healthwise.HealthwiseApp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class ContactDTO {

    private int id;
    private String email;
    private String doctor;
}
