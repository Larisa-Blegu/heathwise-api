package com.healthwise.HealthwiseApp.repository;

import com.healthwise.HealthwiseApp.entity.Location;
import com.healthwise.HealthwiseApp.entity.MedicalProcedure;
import com.healthwise.HealthwiseApp.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicalProcedureRepository extends JpaRepository<MedicalProcedure, Integer> {
    List<MedicalProcedure> getProcedureByName(String name);
    List<MedicalProcedure> getProcedureByCategory(String category);
    List<MedicalProcedure> findBySpecializationId(int doctorId);

}
