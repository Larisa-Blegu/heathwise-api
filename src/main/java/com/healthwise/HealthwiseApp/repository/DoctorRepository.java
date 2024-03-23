package com.healthwise.HealthwiseApp.repository;

import com.healthwise.HealthwiseApp.entity.Doctor;
import com.healthwise.HealthwiseApp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor,Integer> {
    @Query("SELECT d from Doctor d WHERE d.fullName LIKE :partialName")
    public List<Doctor> getDoctorsByName(String partialName);
}
