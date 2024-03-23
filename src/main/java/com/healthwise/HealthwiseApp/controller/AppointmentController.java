package com.healthwise.HealthwiseApp.controller;

import com.healthwise.HealthwiseApp.entity.Appointment;
import com.healthwise.HealthwiseApp.entity.Contact;
import com.healthwise.HealthwiseApp.service.AppointmentService;
import com.healthwise.HealthwiseApp.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/appointment")
@CrossOrigin(origins = "http://localhost:3000")
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;
    @PostMapping()
    public ResponseEntity<?> addAppointment(@RequestBody Appointment appointment){
        Appointment newAppointment = appointmentService.addAppointment(appointment);
        return ResponseEntity.ok("Appointment added");
    }
    @GetMapping(value = "/allAppointments")
    public ResponseEntity<List<Appointment>> getAllAppointments(){
        List<Appointment> allAppointments = appointmentService.getAllAppointments();
        return ResponseEntity.ok(allAppointments);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getAppointmentById(@PathVariable int id){
        Optional<Appointment> appointment = appointmentService.getAppointmentById(id);
        return ResponseEntity.ok(appointment);
    }
    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<Appointment>> getAppointmentByDoctorId(@PathVariable int doctorId) {
        List<Appointment> appointments = appointmentService.getAppointmentByDoctorId(doctorId);
        return ResponseEntity.ok(appointments);
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Appointment>> getAppointmentByUserId(@PathVariable int userId) {
        List<Appointment> appointments = appointmentService.getAppointmentByUserId(userId);
        return ResponseEntity.ok(appointments);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateAppointment(@PathVariable int id, @RequestBody Appointment updatedAppointment) {
        updatedAppointment.setId(id);
        Appointment updated = appointmentService.updateAppointment(updatedAppointment);
        return ResponseEntity.ok("Appointment updated");
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Boolean> deleteAppointmentById(@PathVariable int id) {
        Boolean isDeleted = appointmentService.deleteAppointmentById(id);
        return new ResponseEntity<>(isDeleted, HttpStatus.OK);
    }

    @PostMapping(value = "/status/{id}/{status}")
    public ResponseEntity<Boolean> changeAppointmentStatus(@PathVariable int id,@PathVariable String status){
        Boolean isChanged = appointmentService.changeAppointmentStatus(id, status);
        return new ResponseEntity<>(isChanged, HttpStatus.OK);
    }
    @PostMapping(value = "/reviewStatus/{id}/{status}")
    public ResponseEntity<Boolean> changeReviewStatus(@PathVariable int id, @PathVariable String status){
        Boolean isChanged = appointmentService.changeReviewStatus(id, status);
        return new ResponseEntity<>(isChanged, HttpStatus.OK);
    }
}
