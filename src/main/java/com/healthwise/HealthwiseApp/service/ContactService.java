package com.healthwise.HealthwiseApp.service;

import com.healthwise.HealthwiseApp.entity.Contact;
import com.healthwise.HealthwiseApp.repository.ContactRepository;
import com.healthwise.HealthwiseApp.util.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;
    public Contact addContact(Contact contact){
        return contactRepository.save(contact);
    }
    public List<Contact> getAllContacts(){
        return contactRepository.findAll();
    }
    public Optional<Contact> getContactById(int id){
        return contactRepository.findById(id);
    }
    public List<Contact> getContactByDoctorId(int doctorId) {
        return contactRepository.findByDoctorId(doctorId);
    }
    public Contact updateContact(Contact updatedContact) {
        Optional<Contact> existingContact = contactRepository.findById(updatedContact.getId());
        if (existingContact.isPresent()) {
            Contact contact = existingContact.get();
            contact.setEmail(updatedContact.getEmail());
            contact.setDoctor(updatedContact.getDoctor());
            return contactRepository.save(contact);
        } else {
            throw new ResourceNotFoundException("Contact with id: " + updatedContact.getId() + " not found");
        }
    }
    public Boolean deleteContactById(int id) {
        if (!contactRepository.existsById(id)) {
            throw new ResourceNotFoundException("Contact with ID " + id + " not found");
        }
        contactRepository.deleteById(id);
        return true;
    }
}
