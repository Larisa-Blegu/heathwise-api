package com.healthwise.HealthwiseApp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class CityDTO {

    private String city;
    private int hospitalsNumber;
}
