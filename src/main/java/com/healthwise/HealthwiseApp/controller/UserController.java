package com.healthwise.HealthwiseApp.controller;

import com.healthwise.HealthwiseApp.dto.UserDTO;
import com.healthwise.HealthwiseApp.dto.UserDTOToken;
import com.healthwise.HealthwiseApp.dto.buider.UserBuilder;
import com.healthwise.HealthwiseApp.entity.User;
import com.healthwise.HealthwiseApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserBuilder userBuilder;
    @PostMapping()
    public ResponseEntity<?> addUser(@RequestBody UserDTO userDTO){
        User user = userBuilder.toUserEntity(userDTO);
        userService.addUser(user);
        return ResponseEntity.ok("User added");
    }
    @PostMapping(value = "/register")
    public ResponseEntity<?> register(@RequestBody UserDTO userDTO){
        return ResponseEntity.ok(userService.register(userDTO));
    }
    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@RequestBody UserDTO userDTO){
        UserDTOToken userDTOToken = userService.login(userDTO);
        if(userDTOToken != null){
            return new ResponseEntity<>(userDTOToken, HttpStatus.OK);
        }else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping(value = "/allUsers")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> allUsers = userService.getAllUsers();
        return ResponseEntity.ok(allUsers);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("id") int id) {
        UserDTO userResponseDTO = userService.getUserById(id);
        return new ResponseEntity<>(userResponseDTO, HttpStatus.OK);
    }
    @PutMapping()
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO) {
        UserDTO user = userService.updateUser(userDTO);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Boolean> deleteUserById(@PathVariable("id") int id) {
        Boolean isDeleted = userService.deleteUserById(id);
        return new ResponseEntity<>(isDeleted, HttpStatus.OK);
    }
}
