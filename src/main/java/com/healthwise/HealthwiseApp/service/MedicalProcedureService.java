package com.healthwise.HealthwiseApp.service;

import com.healthwise.HealthwiseApp.entity.Contact;
import com.healthwise.HealthwiseApp.entity.Location;
import com.healthwise.HealthwiseApp.entity.MedicalProcedure;
import com.healthwise.HealthwiseApp.entity.Review;
import com.healthwise.HealthwiseApp.repository.ContactRepository;
import com.healthwise.HealthwiseApp.repository.MedicalProcedureRepository;
import com.healthwise.HealthwiseApp.util.exception.UserNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MedicalProcedureService {

    @Autowired
    private MedicalProcedureRepository medicalProcedureRepository;
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

    public MedicalProcedure updateProcedure(MedicalProcedure updatedProcedure) {
        Optional<MedicalProcedure> existingProcedure = medicalProcedureRepository.findById(updatedProcedure.getId());
        if (existingProcedure.isPresent()) {
            MedicalProcedure medicalProcedure = existingProcedure.get();
            medicalProcedure.setCategory(updatedProcedure.getCategory());
            medicalProcedure.setName(updatedProcedure.getName());
            medicalProcedure.setSpecialization(updatedProcedure.getSpecialization());
            return medicalProcedureRepository.save(medicalProcedure);
        } else {
            throw new UserNotFoundException("Contact with id: " + updatedProcedure.getId() + " not found");
        }
    }
    public Boolean deleteProcedureById(int id) {
        if (!medicalProcedureRepository.existsById(id)) {
            throw new UserNotFoundException("Procedure with ID " + id + " not found");
        }
        medicalProcedureRepository.deleteById(id);
        return true;
    }
}
