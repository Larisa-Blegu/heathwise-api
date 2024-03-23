package com.healthwise.HealthwiseApp.service;

import com.healthwise.HealthwiseApp.entity.Contact;
import com.healthwise.HealthwiseApp.entity.Doctor;
import com.healthwise.HealthwiseApp.entity.Specialization;
import com.healthwise.HealthwiseApp.repository.SpecializationRepository;
import com.healthwise.HealthwiseApp.util.exception.UserNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SpecializationService {

    @Autowired
    private SpecializationRepository specializationRepository;
    public Specialization addSpecialization(Specialization specialization){
        return specializationRepository.save(specialization);
    }
    public List<Specialization> getAllSpecializations(){
        return specializationRepository.findAll();
    }
    public Optional<Specialization> getSpecializationById(int id){
        return specializationRepository.findById(id);
    }
    public List<Specialization> getSpecializationByName(String name){
        return specializationRepository.getSpecializationByName(name);
    }
    public Specialization updateSpecialization(Specialization updatedSpecialization) {
        Optional<Specialization> existingSpecialization = specializationRepository.findById(updatedSpecialization.getId());
        if (existingSpecialization.isPresent()) {
            Specialization specialization = existingSpecialization.get();
            specialization.setName(updatedSpecialization.getName());
            specialization.setDoctors(updatedSpecialization.getDoctors());
            return specializationRepository.save(specialization);
        } else {
            throw new UserNotFoundException("Specialization with id: " + updatedSpecialization.getId() + " not found");
        }
    }
    public Boolean deleteSpecializationById(int id) {
        if (!specializationRepository.existsById(id)) {
            throw new UserNotFoundException("Specialization with ID " + id + " not found");
        }
        specializationRepository.deleteById(id);
        return true;
    }
}
