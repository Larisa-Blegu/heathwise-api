package com.healthwise.HealthwiseApp.controller;

import com.healthwise.HealthwiseApp.dto.ContactDTO;
import com.healthwise.HealthwiseApp.entity.Contact;
import com.healthwise.HealthwiseApp.entity.Doctor;
import com.healthwise.HealthwiseApp.entity.Review;
import com.healthwise.HealthwiseApp.service.ContactService;
import com.healthwise.HealthwiseApp.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/contact")
@CrossOrigin(origins = "http://localhost:3000")
public class ContactController {

    @Autowired
    private ContactService contactService;
    @Autowired
    private DoctorService doctorService;

    @PostMapping()
    public ResponseEntity<?> addContact(@RequestBody Contact contact) {
        Contact newContact = contactService.addContact(contact);
        return ResponseEntity.ok("Contact added");
    }

    @GetMapping(value = "/allContacts")
    public ResponseEntity<List<Contact>> getAllContacts() {
        List<Contact> allContacts = contactService.getAllContacts();
        return ResponseEntity.ok(allContacts);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getContactById(@PathVariable int id) {
        Optional<Contact> contact = contactService.getContactById(id);
        return ResponseEntity.ok(contact);
    }

    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<Contact>> getContactsByDoctorId(@PathVariable int doctorId) {
        List<Contact> contacts = contactService.getContactByDoctorId(doctorId);
        return ResponseEntity.ok(contacts);
    }

    @PutMapping()
    public ResponseEntity<?> updateContact(@RequestBody ContactDTO updatedContact) {
        List<Doctor> doctor = doctorService.getDoctorsByName(updatedContact.getDoctor());
        Contact newContact = new Contact(updatedContact.getId(), updatedContact.getEmail(), doctor.get(0));
        Contact updated = contactService.updateContact(newContact);
        return ResponseEntity.ok("Contact updated");
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Boolean> deleteContactById(@PathVariable int id) {
        Boolean isDeleted = contactService.deleteContactById(id);
        return new ResponseEntity<>(isDeleted, HttpStatus.OK);
    }
}
