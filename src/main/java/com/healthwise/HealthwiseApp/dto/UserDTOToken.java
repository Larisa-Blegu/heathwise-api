package com.healthwise.HealthwiseApp.dto;

import com.healthwise.HealthwiseApp.util.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Objects;

@Builder
@Data
@AllArgsConstructor
public class UserDTOToken {

    private int id;
    private String password;
    private String email;
    private String phoneNumber;
    private String firstName;
    private String lastName;
    private UserRole role;

    @Override
    public int hashCode(){return Objects.hash(email,password);}
    private String token;
}
