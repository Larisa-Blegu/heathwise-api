package com.healthwise.HealthwiseApp.service;

import com.healthwise.HealthwiseApp.entity.Location;
import com.healthwise.HealthwiseApp.entity.Specialization;
import com.healthwise.HealthwiseApp.repository.LocationRepository;
import com.healthwise.HealthwiseApp.repository.SpecializationRepository;
import com.healthwise.HealthwiseApp.util.exception.UserNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LocationService {

    @Autowired
    private LocationRepository locationRepository;
    public Location addLocation(Location location){
        return locationRepository.save(location);
    }
    public List<Location> getAllLocations(){
        return locationRepository.findAll();
    }
    public Optional<Location> getLocationById(int id){
        return locationRepository.findById(id);
    }
    public List<Location> getLocationByHospital(String hospital){
        return locationRepository.getLocationByHospital(hospital);
    }
    public List<Location> getLocationByCity(String city){
        return locationRepository.getLocationByCity(city);
    }
    public Location updateLocation(Location updatedLocation) {
        Optional<Location> existingLocation = locationRepository.findById(updatedLocation.getId());
        if (existingLocation.isPresent()) {
            Location location = existingLocation.get();
            location.setHospital(updatedLocation.getHospital());
            location.setCity(updatedLocation.getCity());
            location.setAdress(updatedLocation.getAdress());
            location.setDoctors(updatedLocation.getDoctors());
            return locationRepository.save(location);
        } else {
            throw new UserNotFoundException("Location with id: " + updatedLocation.getId() + " not found");
        }
    }
    public Boolean deleteLocationById(int id) {
        if (!locationRepository.existsById(id)) {
            throw new UserNotFoundException("Location with ID " + id + " not found");
        }
        locationRepository.deleteById(id);
        return true;
    }
}

