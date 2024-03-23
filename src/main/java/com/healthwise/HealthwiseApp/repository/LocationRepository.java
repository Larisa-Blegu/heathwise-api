package com.healthwise.HealthwiseApp.repository;

import com.healthwise.HealthwiseApp.entity.Location;
import com.healthwise.HealthwiseApp.entity.Specialization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {
    List<Location> getLocationByHospital(String hospital);
    List<Location> getLocationByCity(String city);
}
