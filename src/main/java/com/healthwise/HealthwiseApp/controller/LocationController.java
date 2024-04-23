package com.healthwise.HealthwiseApp.controller;

import com.healthwise.HealthwiseApp.entity.Location;
import com.healthwise.HealthwiseApp.entity.Specialization;
import com.healthwise.HealthwiseApp.service.LocationService;
import com.healthwise.HealthwiseApp.service.SpecializationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/location")
@CrossOrigin(origins = "http://localhost:3000")
public class LocationController {
    @Autowired
    private LocationService locationService ;
    @PostMapping()
    public ResponseEntity<?> addLocation(@RequestBody Location location){
        Location newLocation = locationService.addLocation(location);
        return ResponseEntity.ok("Location added");
    }
    @GetMapping(value = "/allLocations")
    public ResponseEntity<List<Location>> getAllLocations(){
        List<Location> allLocations = locationService.getAllLocations();
        return ResponseEntity.ok(allLocations);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getLocationById(@PathVariable int id){
        Optional<Location> location = locationService.getLocationById(id);
        return ResponseEntity.ok(location);
    }
    @GetMapping(value="/hospital/{hospital}")
    public ResponseEntity<?> getLocationByHospital(@PathVariable String hospital){
        List<Location> allLocations = locationService.getLocationByHospital(hospital);
        return ResponseEntity.ok(allLocations);
    }
    @GetMapping(value="/city/{city}")
    public ResponseEntity<?> getLocationByCity(@PathVariable String city){
        List<Location> allLocations = locationService.getLocationByCity(city);
        return ResponseEntity.ok(allLocations);
    }
    @PutMapping()
    public ResponseEntity<?> updateLocation(@RequestBody Location updatedLocation) {
        Location updated = locationService.updateLocation(updatedLocation);
        return ResponseEntity.ok("Location updated");
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Boolean> deleteLocationById(@PathVariable int id) {
        Boolean isDeleted = locationService.deleteLocationById(id);
        return new ResponseEntity<>(isDeleted, HttpStatus.OK);
    }
}
