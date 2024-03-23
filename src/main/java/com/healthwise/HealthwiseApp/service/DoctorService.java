package com.healthwise.HealthwiseApp.service;

import com.healthwise.HealthwiseApp.entity.Doctor;
import com.healthwise.HealthwiseApp.entity.Location;
import com.healthwise.HealthwiseApp.entity.Specialization;
import com.healthwise.HealthwiseApp.repository.DoctorRepository;
import com.healthwise.HealthwiseApp.repository.LocationRepository;
import com.healthwise.HealthwiseApp.repository.SpecializationRepository;
import com.healthwise.HealthwiseApp.util.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private SpecializationRepository specializationRepository;
    @Autowired
    private LocationRepository locationRepository;
    public Doctor addDoctor(Doctor doctor){
        return doctorRepository.save(doctor);
    }
    public List<Doctor> getAllDoctors(){
        return doctorRepository.findAll();
    }
    public Optional<Doctor> getDoctorById(int id){
        return doctorRepository.findById(id);
    }
    public List<Doctor> getDoctorsByName(String partialName){
        String newPartialName = "%" + partialName + "%";
        return doctorRepository.getDoctorsByName(newPartialName);
    }
    public Doctor updateDoctor(Doctor updatedDoctor) {
        Optional<Doctor> existingDoctor = doctorRepository.findById(updatedDoctor.getId());
        if (existingDoctor.isPresent()) {
            Doctor doctor = existingDoctor.get();
            doctor.setFullName(updatedDoctor.getFullName());
            doctor.setMedicineDegree(updatedDoctor.getMedicineDegree());
            doctor.setDescription(updatedDoctor.getDescription());
            doctor.setImage(updatedDoctor.getImage());
            return doctorRepository.save(doctor);
        } else {
            throw new ResourceNotFoundException("Doctor with id: " + updatedDoctor.getId() + " not found");
        }
    }
    public Boolean deleteDoctorById(int id) {
        if (!doctorRepository.existsById(id)) {
            throw new ResourceNotFoundException("Doctor with ID " + id + " not found");
        }
        doctorRepository.deleteById(id);
        return true;
    }
    public void addDoctorSpecialization(int doctorId, Specialization specialization){
        Doctor doctor = doctorRepository.getById(doctorId);
        doctor.getSpecializations().add(specialization);
        specialization.getDoctors().add(doctor);
        doctorRepository.save(doctor);
    }
    public void deleteDoctorSpecialization(int doctorId, Specialization newSpecialization) {
        Specialization specialization = specializationRepository.getById(newSpecialization.getId());
        Doctor doctor = doctorRepository.getById(doctorId);
        doctor.getSpecializations().remove(specialization);
        specialization.getDoctors().remove(doctor);
        doctorRepository.save(doctor);
    }
    public void addDoctorLocation(int doctorId, Location location){
        Doctor doctor = doctorRepository.getById(doctorId);
        doctor.getLocations().add(location);
        location.getDoctors().add(doctor);
        doctorRepository.save(doctor);
    }
    public void deleteDoctorLocation(int doctorId, Location existingLocation) {
        Location location = locationRepository.getById(existingLocation.getId());
        Doctor doctor = doctorRepository.getById(doctorId);
        doctor.getLocations().remove(location);
        location.getDoctors().remove(doctor);
        doctorRepository.save(doctor);
    }
}
