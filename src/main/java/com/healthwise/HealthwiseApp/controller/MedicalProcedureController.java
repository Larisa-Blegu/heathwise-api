package com.healthwise.HealthwiseApp.controller;

import com.healthwise.HealthwiseApp.dto.ContactDTO;
import com.healthwise.HealthwiseApp.dto.ProcedureDTO;
import com.healthwise.HealthwiseApp.entity.*;
import com.healthwise.HealthwiseApp.service.ContactService;
import com.healthwise.HealthwiseApp.service.MedicalProcedureService;
import com.healthwise.HealthwiseApp.service.SpecializationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/medicalProcedure")
@CrossOrigin(origins = "http://localhost:3000")
public class MedicalProcedureController {
    @Autowired
    private MedicalProcedureService medicalProcedureService;
    @Autowired
    private SpecializationService specializationService;
    @PostMapping()
    public ResponseEntity<?> addProcedure(@RequestBody MedicalProcedure medicalProcedure){
        MedicalProcedure newMedicalProcedure = medicalProcedureService.addProcedure(medicalProcedure);
        return ResponseEntity.ok("Procedure added");
    }
    @GetMapping(value = "/allProcedures")
    public ResponseEntity<List<MedicalProcedure>> getAllProcedures(){
        List<MedicalProcedure> allProcedures = medicalProcedureService.getAllProcedures();
        return ResponseEntity.ok(allProcedures);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getProcedureById(@PathVariable int id){
        Optional<MedicalProcedure> procedure = medicalProcedureService.getProcedureById(id);
        return ResponseEntity.ok(procedure);
    }
    @GetMapping(value="/name/{name}")
    public ResponseEntity<?> getProcedureByName(@PathVariable String name){
        List<MedicalProcedure> allProcedures = medicalProcedureService.getProcedureByName(name);
        return ResponseEntity.ok(allProcedures);
    }
    @GetMapping(value="/category/{category}")
    public ResponseEntity<?> getProcedureByCategory(@PathVariable String category){
        List<MedicalProcedure> allProcedures = medicalProcedureService.getProcedureByCategory(category);
        return ResponseEntity.ok(allProcedures);
    }
    @GetMapping("/specialization/{specializationId}")
    public ResponseEntity<List<MedicalProcedure>> getProcedureBySpecializationId(@PathVariable int specializationId) {
        List<MedicalProcedure> procedures = medicalProcedureService.getProcedureBySpecializationId(specializationId);
        return ResponseEntity.ok(procedures);
    }

    @PutMapping()
    public ResponseEntity<?> updateProcedure(@RequestBody ProcedureDTO updatedProcedure) {
        List<Specialization> specialization = specializationService.getSpecializationByName(updatedProcedure.getSpecialization());
        MedicalProcedure newProcedure = new MedicalProcedure(updatedProcedure.getId(), updatedProcedure.getCategory(), updatedProcedure.getName(), specialization.get(0));
        MedicalProcedure updated = medicalProcedureService.updateProcedure(newProcedure);
        return ResponseEntity.ok("Procedure updated");
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Boolean> deleteProcedureById(@PathVariable int id) {
        Boolean isDeleted = medicalProcedureService.deleteProcedureById(id);
        return new ResponseEntity<>(isDeleted, HttpStatus.OK);
    }
}
