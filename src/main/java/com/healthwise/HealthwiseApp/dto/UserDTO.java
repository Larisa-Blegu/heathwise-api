package com.healthwise.HealthwiseApp.dto;

import com.healthwise.HealthwiseApp.util.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import java.util.Objects;

import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
public class UserDTO {

    private int id;
    private String password;
    private String email;
    private String phoneNumber;
    private String firstName;
    private String lastName;
    private UserRole role;

    @Override
    public int hashCode(){return Objects.hash(email,password);}
}
