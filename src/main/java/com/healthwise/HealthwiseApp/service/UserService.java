package com.healthwise.HealthwiseApp.service;

import com.healthwise.HealthwiseApp.dto.UserDTO;
import com.healthwise.HealthwiseApp.dto.UserDTOToken;
import com.healthwise.HealthwiseApp.dto.buider.UserBuilder;
import com.healthwise.HealthwiseApp.entity.User;
import com.healthwise.HealthwiseApp.repository.UserRepository;
import com.healthwise.HealthwiseApp.util.exception.ResourceNotFoundException;
import com.healthwise.HealthwiseApp.util.ServiceUtils;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.healthwise.HealthwiseApp.util.enums.UserRole.CLIENT;

@Service
@AllArgsConstructor
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserBuilder userBuilder;
    @Autowired
    private JwtService jwtService;

    private AuthenticationManager authenticationManager;

    private final PasswordEncoder passwordEncoder;
    public UserDTO getUserByEmail(String email) {

        User userOptional = userRepository.findByEmail(email);

        UserDTO userDTO = userBuilder.toUserDTO(userOptional);

        if(userOptional != null) {
            return userDTO;
        } else {
            throw new ResourceNotFoundException(User.class.getSimpleName() + " with email: " + email);
        }
    }
    public User addUser(User user){
        return userRepository.save(user);
    }
    public UserDTOToken register(UserDTO userDTO){
        User user = userBuilder.toUserEntity(userDTO);
        user.setRole(CLIENT);
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return userBuilder.toUserDTOToken(user, jwtToken);
    }
    public UserDTOToken login(UserDTO userDTO){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                userDTO.getEmail(),
                userDTO.getPassword()
        ));
        User user = userRepository.findByEmail(userDTO.getEmail());
        if(user != null){
            var jwtToken = jwtService.generateToken(user);
            return userBuilder.toUserDTOToken(user, jwtToken);
        }else{
            throw new ResourceNotFoundException("Email or password incorect");
        }
    }
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
    public UserDTO getUserById(int id){
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()){
            throw new ResourceNotFoundException(User.class.getSimpleName() + " with id " + id);
        }
        User fetchedUser = user.get();
        return UserBuilder.toUserDTO(fetchedUser);
    }
    public UserDTO updateUser(UserDTO userDTO){
        User user = userBuilder.toUserEntity(userDTO);
        Optional<User> existingUser = userRepository.findById(user.getId());
        if(existingUser != null){
            User newUser = existingUser.get();
            ServiceUtils.updateFields(newUser, user);
            return UserBuilder.toUserDTO(userRepository.save(newUser));
        }else{
            throw new ResourceNotFoundException(User.class.getSimpleName() + " with id: " + user.getId());
        }
    }
    public Boolean deleteUserById(int id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("User with ID " + id + " not found");
        }
        userRepository.deleteById(id);
        return true;
    }
}
