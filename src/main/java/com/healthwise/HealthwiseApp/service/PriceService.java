package com.healthwise.HealthwiseApp.service;

import com.healthwise.HealthwiseApp.entity.Price;
import com.healthwise.HealthwiseApp.repository.PriceRepository;
import com.healthwise.HealthwiseApp.util.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PriceService {

    @Autowired
    private PriceRepository priceRepository;
    public Price addPrice(Price price){
        return priceRepository.save(price);
    }
    public List<Price> getAllPrices(){
        return priceRepository.findAll();
    }
    public Optional<Price> getPriceById(int id){
        return priceRepository.findById(id);
    }
    public List<Price> getPriceByDoctorId(int doctorId) {
        return priceRepository.findByDoctorId(doctorId);
    }
    public List<Price> getPriceByProcedureId(int procedureId) {
        return priceRepository.findByMedicalProcedureId(procedureId);
    }
    public Price updatePrice(Price updatedPrice) {
        Optional<Price> existingPrice = priceRepository.findById(updatedPrice.getId());
        if (existingPrice.isPresent()) {
            Price price = existingPrice.get();
            price.setPrice(updatedPrice.getPrice());
            price.setMedicalProcedure(updatedPrice.getMedicalProcedure());
            price.setDoctor(updatedPrice.getDoctor());
            return priceRepository.save(price);
        } else {
            throw new ResourceNotFoundException("Price with id: " + updatedPrice.getId() + " not found");
        }
    }
    public Boolean deletePriceById(int id) {
        if (!priceRepository.existsById(id)) {
            throw new ResourceNotFoundException("Price with ID " + id + " not found");
        }
        priceRepository.deleteById(id);
        return true;
    }
}
