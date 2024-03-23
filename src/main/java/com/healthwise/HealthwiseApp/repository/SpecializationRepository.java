package com.healthwise.HealthwiseApp.repository;

import com.healthwise.HealthwiseApp.entity.Specialization;
import com.healthwise.HealthwiseApp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpecializationRepository extends JpaRepository<Specialization, Integer> {
    List<Specialization> getSpecializationByName(String name);
}
