package com.healthwise.HealthwiseApp.repository;

import com.healthwise.HealthwiseApp.entity.Appointment;
import com.healthwise.HealthwiseApp.entity.Contact;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

    List<Appointment> findByDoctorId(int doctorId);
    List<Appointment> findByUserId(int userId);

    @Query("SELECT a FROM Appointment a WHERE a.date BETWEEN :dateInitial AND :dateFinal")
    List<Appointment> findAppointmentsBetweenDates(LocalDateTime dateInitial, LocalDateTime dateFinal);
}
