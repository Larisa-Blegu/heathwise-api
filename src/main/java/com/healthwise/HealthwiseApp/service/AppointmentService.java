package com.healthwise.HealthwiseApp.service;

import com.healthwise.HealthwiseApp.entity.*;
import com.healthwise.HealthwiseApp.repository.AppointmentRepository;
import com.healthwise.HealthwiseApp.util.enums.AppointmentReviewStatus;
import com.healthwise.HealthwiseApp.util.enums.AppointmentStatus;
import com.healthwise.HealthwiseApp.util.enums.AppointmentType;
import com.healthwise.HealthwiseApp.util.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private EmailSenderService emailSenderService;
    public Appointment addAppointment(Appointment appointment){
        return appointmentRepository.save(appointment);
    }
    public List<Appointment> getAllAppointments(){
        return appointmentRepository.findAll();
    }
    public Optional<Appointment> getAppointmentById(int id){
        return appointmentRepository.findById(id);
    }
    public List<Appointment> getAppointmentByDoctorId(int doctorId) {
        return appointmentRepository.findByDoctorId(doctorId);
    }
    public List<Appointment> getAppointmentByUserId(int userId) {
        return appointmentRepository.findByUserId(userId);
    }
    public Appointment updateAppointment(Appointment updatedAppointment) {
        Optional<Appointment> existingAppointment = appointmentRepository.findById(updatedAppointment.getId());
        if (existingAppointment.isPresent()) {
            Appointment appointment = existingAppointment.get();
            appointment.setDate(updatedAppointment.getDate());
            appointment.setType(updatedAppointment.getType());
            appointment.setDoctor(updatedAppointment.getDoctor());
            appointment.setLocation(updatedAppointment.getLocation());
            appointment.setMedicalProcedure(updatedAppointment.getMedicalProcedure());
            appointment.setUser(updatedAppointment.getUser());
            appointment.setReviewStatus(updatedAppointment.getReviewStatus());
            appointment.setStatus(updatedAppointment.getStatus());
            return appointmentRepository.save(appointment);
        } else {
            throw new ResourceNotFoundException("Appointment with id: " + updatedAppointment.getId() + " not found");
        }
    }
    public Boolean deleteAppointmentById(int id) {
        if (!appointmentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Appointment with ID " + id + " not found");
        }
        appointmentRepository.deleteById(id);
        return true;
    }
    public Boolean changeAppointmentStatus(int appointmentId, String status){
        Appointment appointment = appointmentRepository.getById(appointmentId);

        if(appointment != null){
            if(status.equals("APPROVED")){
                appointment.setStatus(AppointmentStatus.APPROVED);
                this.sendEmail(appointment);
            }else{
                appointment.setStatus(AppointmentStatus.REJECTED);
            }
            appointmentRepository.save(appointment);
            return true;
        }else{
            return false;
        }
    }
    public void sendEmail(Appointment appointment){
        User user = appointment.getUser();
        MedicalProcedure medicalProcedure = appointment.getMedicalProcedure();
        Doctor doctor = appointment.getDoctor();
        Location location = appointment.getLocation();
        String message = "Buna ziua! Programarea dumneavoastra a fost realizata cu succes la data de:" + appointment.getDate() + " la doctorul " +
                doctor.getFullName();
        if(appointment.getType() == AppointmentType.PHYSICAL){
              message += ". Va asteptam la adresa: " + location.getAddress() + " din orasul " + location.getCity();
        }else{
            message += "Programarea se va tine ONLINE, accesand urmatorul link, la data si ora stabilita" + "\n" + "link";
        }
        emailSenderService.sendEmail(user.getEmail(), "Programare la " + medicalProcedure.getName(), message);
    }
    public Boolean changeReviewStatus(int appointmentId, String status){
        Appointment appointment = appointmentRepository.getById(appointmentId);
        if(appointment != null){
            appointment.setReviewStatus(AppointmentReviewStatus.TRUE);
            appointmentRepository.save(appointment);
            return true;
        }else{
            return false;
        }
    }
}
