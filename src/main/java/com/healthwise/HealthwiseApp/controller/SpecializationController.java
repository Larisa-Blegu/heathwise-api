package com.healthwise.HealthwiseApp.controller;

import com.healthwise.HealthwiseApp.entity.Contact;
import com.healthwise.HealthwiseApp.entity.Doctor;
import com.healthwise.HealthwiseApp.entity.Review;
import com.healthwise.HealthwiseApp.entity.Specialization;
import com.healthwise.HealthwiseApp.service.DoctorService;
import com.healthwise.HealthwiseApp.service.SpecializationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/specialization")
@CrossOrigin(origins = "http://localhost:3000")
public class SpecializationController {

    @Autowired
    private SpecializationService specializationService;

    @PostMapping()
    public ResponseEntity<?> addSpecialization(@RequestBody Specialization specialization) {
        Specialization newSpecialization = specializationService.addSpecialization(specialization);
        return ResponseEntity.ok("Specialization added");
    }

    @GetMapping(value = "/allSpecializations")
    public ResponseEntity<List<Specialization>> getAllSpecializations() {
        List<Specialization> allSpecialization = specializationService.getAllSpecializations();
        return ResponseEntity.ok(allSpecialization);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getSpecializationById(@PathVariable int id) {
        Optional<Specialization> specialization = specializationService.getSpecializationById(id);
        return ResponseEntity.ok(specialization);
    }

    @GetMapping(value = "/getSpecialization/{name}")
    public ResponseEntity<?> getSpecializationByName(@PathVariable String name) {
        List<Specialization> allspecializations = specializationService.getSpecializationByName(name);
        return ResponseEntity.ok(allspecializations);
    }

    @GetMapping(value = "/getSpecializations/{doctorId}")
    public ResponseEntity<List<Specialization>> getSpecializationsByDoctorId(@PathVariable int doctorId) {
        List<Specialization> allspecializations = specializationService.getSpecializationsByDoctorId(doctorId);
        return ResponseEntity.ok(allspecializations);
    }

    @PutMapping()
    public ResponseEntity<?> updateSpecialization(@RequestBody Specialization updatedSpecialization) {
        Specialization updated = specializationService.updateSpecialization(updatedSpecialization);
        return ResponseEntity.ok("Specialization updated");
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Boolean> deleteSpecializationById(@PathVariable int id) {
        Boolean isDeleted = specializationService.deleteSpecializationById(id);
        return new ResponseEntity<>(isDeleted, HttpStatus.OK);
    }
}
