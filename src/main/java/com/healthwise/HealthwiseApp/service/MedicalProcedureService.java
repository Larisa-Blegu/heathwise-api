package com.healthwise.HealthwiseApp.service;

import com.healthwise.HealthwiseApp.entity.Doctor;
import com.healthwise.HealthwiseApp.entity.MedicalProcedure;
import com.healthwise.HealthwiseApp.entity.Specialization;
import com.healthwise.HealthwiseApp.repository.DoctorRepository;
import com.healthwise.HealthwiseApp.repository.MedicalProcedureRepository;
import com.healthwise.HealthwiseApp.util.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MedicalProcedureService {

    @Autowired
    private MedicalProcedureRepository medicalProcedureRepository;
    @Autowired
    private DoctorRepository doctorRepository;

    public MedicalProcedure addProcedure(MedicalProcedure medicalProcedure){
        return medicalProcedureRepository.save(medicalProcedure);
    }

    public List<MedicalProcedure> getAllProcedures(){
        return medicalProcedureRepository.findAll();
    }

    public Optional<MedicalProcedure> getProcedureById(int id){
        return medicalProcedureRepository.findById(id);
    }

    public List<MedicalProcedure> getProcedureByName(String name){
        return medicalProcedureRepository.getProcedureByName(name);
    }

    public List<MedicalProcedure> getProcedureByCategory(String category){
        return medicalProcedureRepository.getProcedureByCategory(category);
    }

    public List<MedicalProcedure> getProcedureBySpecializationId(int specializationId) {
        return medicalProcedureRepository.findBySpecializationId(specializationId);
    }

    public List<MedicalProcedure> getProcedureByDoctorId(String name){
        List<MedicalProcedure> medicalProcedures = new ArrayList<>();
        Doctor doctor = doctorRepository.getDoctorsByName(name).get(0);
        List<Specialization> specializations = doctor.getSpecializations();
        for (Specialization specialization : specializations) {
            medicalProcedures.addAll(medicalProcedureRepository.findBySpecializationId(specialization.getId())) ;
        }
            return medicalProcedures;
    }

    public MedicalProcedure updateProcedure(MedicalProcedure updatedProcedure) {
        Optional<MedicalProcedure> existingProcedure = medicalProcedureRepository.findById(updatedProcedure.getId());
        if (existingProcedure.isPresent()) {
            MedicalProcedure medicalProcedure = existingProcedure.get();
            medicalProcedure.setCategory(updatedProcedure.getCategory());
            medicalProcedure.setName(updatedProcedure.getName());
            medicalProcedure.setSpecialization(updatedProcedure.getSpecialization());
            return medicalProcedureRepository.save(medicalProcedure);
        } else {
            throw new ResourceNotFoundException("Procedure with id: " + updatedProcedure.getId() + " not found");
        }
    }

    public Boolean deleteProcedureById(int id) {
        if (!medicalProcedureRepository.existsById(id)) {
            throw new ResourceNotFoundException("Procedure with ID " + id + " not found");
        }
        medicalProcedureRepository.deleteById(id);
        return true;
    }
}
