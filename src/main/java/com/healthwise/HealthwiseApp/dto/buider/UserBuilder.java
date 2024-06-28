package com.healthwise.HealthwiseApp.dto.buider;

import com.healthwise.HealthwiseApp.dto.UserDTO;
import com.healthwise.HealthwiseApp.dto.UserDTOToken;
import com.healthwise.HealthwiseApp.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserBuilder {

    private UserBuilder() {
    }

    public static UserDTO toUserDTO(User user) {
        return new UserDTO(
                user.getId(),
                user.getPassword(),
                user.getEmail(),
                user.getPhoneNumber(),
                user.getFirstName(),
                user.getLastName(),
                user.getRole()
        );
    }

    public static User toUserEntity(UserDTO userDTO) {
        return new User(
                userDTO.getId(),
                userDTO.getPassword(),
                userDTO.getEmail(),
                userDTO.getPhoneNumber(),
                userDTO.getFirstName(),
                userDTO.getLastName(),
                userDTO.getRole()
        );
    }

    public static UserDTOToken toUserDTOToken(User user, String token) {
        return new UserDTOToken(
                user.getId(),
                user.getPassword(),
                user.getEmail(),
                user.getPhoneNumber(),
                user.getFirstName(),
                user.getLastName(),
                user.getRole(),
                token
        );
    }}
