package com.healthwise.HealthwiseApp.repository;

import com.healthwise.HealthwiseApp.entity.Doctor;
import com.healthwise.HealthwiseApp.entity.DoctorUser;
import com.healthwise.HealthwiseApp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorUserRepository extends JpaRepository<DoctorUser,Integer> {

    DoctorUser getDoctorUserByUser(User user);
}
