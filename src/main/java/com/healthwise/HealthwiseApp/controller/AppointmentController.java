package com.healthwise.HealthwiseApp.controller;

import com.healthwise.HealthwiseApp.dto.AppointmentDTO;
import com.healthwise.HealthwiseApp.dto.ContactDTO;
import com.healthwise.HealthwiseApp.dto.UserDTO;
import com.healthwise.HealthwiseApp.entity.*;
import com.healthwise.HealthwiseApp.response.PaymentResponse;
import com.healthwise.HealthwiseApp.service.*;
import com.stripe.exception.StripeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/appointment")
@CrossOrigin(origins = "http://localhost:3000")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private LocationService locationService;
    @Autowired
    private MedicalProcedureService medicalProcedureService;
    @Autowired
    private UserService userService;
    @Autowired
    private PaymentService paymentService;

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

    @PutMapping()
    public ResponseEntity<?> updateAppointment(@RequestBody AppointmentDTO updatedAppointment) {
        List<Location> location = locationService.getLocationByHospital(updatedAppointment.getLocation());
        List<Doctor> doctor = doctorService.getDoctorsByName(updatedAppointment.getDoctor());
        List<MedicalProcedure> medicalProcedure = medicalProcedureService.getProcedureByName(updatedAppointment.getMedicalProcedure());
        User user = userService.getUserByEmail(updatedAppointment.getUser());
        Appointment newAppointment = new Appointment(updatedAppointment.getId(), updatedAppointment.getDate(),
                updatedAppointment.getType(), location.get(0), doctor.get(0),medicalProcedure.get(0) , user,
                updatedAppointment.getStatus(), updatedAppointment.getReviewStatus());
        Appointment updated = appointmentService.updateAppointment(newAppointment);
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

    @PostMapping(value = "/payment/{appointmentId}")
    public ResponseEntity<PaymentResponse> makePayment(@PathVariable int appointmentId) throws StripeException {
        PaymentResponse response = paymentService.createPaymentLink(appointmentId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/countAppointment/{dateInitial}/{dateFinal}")
    public ResponseEntity<List<Integer>> getMonthlyAppointments(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateInitial,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateFinal) {
        List<Integer> monthlyCount = appointmentService.getMonthlyAppointmentCounts(dateInitial, dateFinal);
        return new ResponseEntity<>(monthlyCount, HttpStatus.OK);
    }

    @GetMapping(value = "/todayAppointments/{dateInitial}/{dateFinal}")
    public  ResponseEntity<List<Appointment>> getAppointmentsByDate(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateInitial,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateFinal) {
        List<Appointment> appointments = appointmentService.getAppointmentsByDate(dateInitial,dateFinal);
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }
}

