package com.healthwise.HealthwiseApp.controller;

import com.healthwise.HealthwiseApp.entity.MedicalProcedure;
import com.healthwise.HealthwiseApp.entity.Price;
import com.healthwise.HealthwiseApp.entity.Review;
import com.healthwise.HealthwiseApp.service.MedicalProcedureService;
import com.healthwise.HealthwiseApp.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/price")
@CrossOrigin(origins = "http://localhost:3000")
public class PriceController {
    @Autowired
    private PriceService priceService;
    @PostMapping()
    public ResponseEntity<?> addPrice(@RequestBody Price price){
        Price newPrice = priceService.addPrice(price);
        return ResponseEntity.ok("Price added");
    }
    @GetMapping(value = "/allPrices")
    public ResponseEntity<List<Price>> getAllPrices(){
        List<Price> allPrices = priceService.getAllPrices();
        return ResponseEntity.ok(allPrices);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getPriceById(@PathVariable int id){
        Optional<Price> price = priceService.getPriceById(id);
        return ResponseEntity.ok(price);
    }
    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<Price>> getPriceByDoctorId(@PathVariable int doctorId) {
        List<Price> prices = priceService.getPriceByDoctorId(doctorId);
        return ResponseEntity.ok(prices);
    }
    @GetMapping("/procedure/{procedureId}")
    public ResponseEntity<List<Price>> getPriceByProcedureId(@PathVariable int procedureId) {
        List<Price> prices = priceService.getPriceByProcedureId(procedureId);
        return ResponseEntity.ok(prices);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updatePrice(@PathVariable int id, @RequestBody Price updatedPrice) {
        updatedPrice.setId(id);
        Price updated = priceService.updatePrice(updatedPrice);
        return ResponseEntity.ok("Price updated");
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Boolean> deletePriceById(@PathVariable int id) {
        Boolean isDeleted = priceService.deletePriceById(id);
        return new ResponseEntity<>(isDeleted, HttpStatus.OK);
    }
}
