package com.healthwise.HealthwiseApp.repository;

import com.healthwise.HealthwiseApp.entity.Contact;
import com.healthwise.HealthwiseApp.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Integer> {

    List<Review> findByDoctorId(int doctorId);
}
