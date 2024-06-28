package com.healthwise.HealthwiseApp.controller;

import com.healthwise.HealthwiseApp.dto.UserDTO;
import com.healthwise.HealthwiseApp.entity.Doctor;
import com.healthwise.HealthwiseApp.entity.Location;
import com.healthwise.HealthwiseApp.entity.Specialization;
import com.healthwise.HealthwiseApp.entity.User;
import com.healthwise.HealthwiseApp.service.DoctorService;
import com.healthwise.HealthwiseApp.service.UserService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/doctor")
@CrossOrigin(origins = "http://localhost:3000")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @PostMapping()
    public ResponseEntity<?> addDoctor(@RequestBody Doctor doctor) {
        Doctor newDoctor = doctorService.addDoctor(doctor);
        return ResponseEntity.ok("Doctor added");
    }

    @GetMapping(value = "/allDoctors")
    public ResponseEntity<List<Doctor>> getAllDoctors() {
        List<Doctor> allDoctors = doctorService.getAllDoctors();
        return ResponseEntity.ok(allDoctors);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getDoctorById(@PathVariable int id) {
        Doctor doctor = doctorService.getDoctorById(id);
        return ResponseEntity.ok(doctor);
    }

    @GetMapping(value = "/getDoctor/{partialName}")
    public ResponseEntity<?> getDoctorsByName(@PathVariable String partialName) {
        List<Doctor> allDoctors = doctorService.getDoctorsByName(partialName);
        return ResponseEntity.ok(allDoctors);
    }

    @PutMapping()
    public ResponseEntity<?> updateDoctor(@RequestBody Doctor updatedDoctor) {
        Doctor updated = doctorService.updateDoctor(updatedDoctor);
        return ResponseEntity.ok("Doctor updated");
    }

    @SneakyThrows
    @PutMapping("/avatar")
    public ResponseEntity<?> updateImage(@RequestParam int doctorId, @RequestParam("image") MultipartFile imageFile) {
        byte[] imageBytes = imageFile.getBytes();
        System.out.println("Received content type: {}" + imageFile.getContentType());
        Doctor updated = doctorService.updateImage(doctorId, imageBytes);
        return ResponseEntity.ok("Doctor image updated");
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Boolean> deleteDoctorById(@PathVariable int id) {
        Boolean isDeleted = doctorService.deleteDoctorById(id);
        return new ResponseEntity<>(isDeleted, HttpStatus.OK);
    }

    @PostMapping(value = "/specialization/{doctorId}")
    public ResponseEntity<?> addDoctorSpecialization(@PathVariable int doctorId, @RequestBody Specialization specialization) {
        doctorService.addDoctorSpecialization(doctorId, specialization);
        return ResponseEntity.ok("Doctor and specialization added");
    }

    @DeleteMapping(value = "/deleteSpecialization/{doctorId}")
    public ResponseEntity<?> deleteDoctorSpecialization(@PathVariable int doctorId, @RequestBody Specialization specialization) {
        doctorService.deleteDoctorSpecialization(doctorId, specialization);
        return ResponseEntity.ok(specialization);
    }

    @PostMapping(value = "/location/{doctorId}")
    public ResponseEntity<?> addDoctorLocation(@PathVariable int doctorId, @RequestBody Location location) {
        doctorService.addDoctorLocation(doctorId, location);
        return ResponseEntity.ok("Doctor and location added");
    }

    @DeleteMapping(value = "/deleteLocation/{doctorId}")
    public ResponseEntity<?> deleteDoctorLocation(@PathVariable int doctorId, @RequestBody Location location) {
        doctorService.deleteDoctorLocation(doctorId, location);
        return ResponseEntity.ok(location);
    }
}
