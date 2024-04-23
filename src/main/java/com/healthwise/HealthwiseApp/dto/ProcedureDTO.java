package com.healthwise.HealthwiseApp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class ProcedureDTO {
    private int id;
    private String category;
    private String name;
    private String specialization;
}
