package com.healthwise.HealthwiseApp.repository;

import com.healthwise.HealthwiseApp.entity.Price;
import com.healthwise.HealthwiseApp.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PriceRepository extends JpaRepository<Price, Integer> {
    List<Price> findByDoctorId(int doctorId);
    List<Price> findByMedicalProcedureId(int procedureId);

}
