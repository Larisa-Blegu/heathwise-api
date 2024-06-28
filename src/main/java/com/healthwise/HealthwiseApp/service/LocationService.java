package com.healthwise.HealthwiseApp.service;

import com.healthwise.HealthwiseApp.dto.CityDTO;
import com.healthwise.HealthwiseApp.entity.Location;
import com.healthwise.HealthwiseApp.repository.LocationRepository;
import com.healthwise.HealthwiseApp.util.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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
            location.setLatitude(updatedLocation.getLatitude());
            location.setLongitude(updatedLocation.getLongitude());
            location.setAddress(updatedLocation.getAddress());
            location.setDoctors(updatedLocation.getDoctors());
            return locationRepository.save(location);
        } else {
            throw new ResourceNotFoundException("Location with id: " + updatedLocation.getId() + " not found");
        }
    }

    public Boolean deleteLocationById(int id) {
        if (!locationRepository.existsById(id)) {
            throw new ResourceNotFoundException("Location with ID " + id + " not found");
        }
        locationRepository.deleteById(id);
        return true;
    }

    public List<CityDTO> getTopCities() {
        List<Location> locations = locationRepository.findAll();
        Map<String, Integer> cityCountMap = new HashMap<>();

        for (Location location : locations) {
            String city = location.getCity();
            cityCountMap.put(city, cityCountMap.getOrDefault(city, 0) + 1);
        }

        List<CityDTO> topCities = cityCountMap.entrySet().stream()
                .map(entry -> new CityDTO(entry.getKey(), entry.getValue()))
                .sorted(Comparator.comparingInt(CityDTO::getHospitalsNumber).reversed())
                .limit(4) // Limit to top 4 cities
                .collect(Collectors.toList());

        return topCities;
    }
}

