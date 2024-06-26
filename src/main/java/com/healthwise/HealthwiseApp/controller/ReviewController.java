package com.healthwise.HealthwiseApp.controller;

import com.healthwise.HealthwiseApp.dto.ContactDTO;
import com.healthwise.HealthwiseApp.dto.ReviewDTO;
import com.healthwise.HealthwiseApp.entity.Contact;
import com.healthwise.HealthwiseApp.entity.Doctor;
import com.healthwise.HealthwiseApp.entity.Review;
import com.healthwise.HealthwiseApp.service.ContactService;
import com.healthwise.HealthwiseApp.service.DoctorService;
import com.healthwise.HealthwiseApp.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/review")
@CrossOrigin(origins = "http://localhost:3000")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;
    @Autowired
    private DoctorService doctorService;

    @PostMapping()
    public ResponseEntity<?> addReview(@RequestBody Review review) {
        Review newReview = reviewService.addReview(review);
        return ResponseEntity.ok("Review added");
    }

    @GetMapping()
    public ResponseEntity<List<Review>> getAllReviews() {
        List<Review> allReviews = reviewService.getAllReviews();
        return ResponseEntity.ok(allReviews);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getReviewById(@PathVariable int id) {
        Optional<Review> review = reviewService.getReviewById(id);
        return ResponseEntity.ok(review);
    }

    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<Review>> getReviewsByDoctorId(@PathVariable int doctorId) {
        List<Review> reviews = reviewService.getReviewsByDoctorId(doctorId);
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/avg/{doctorId}")
    public ResponseEntity<?> getAvgReviewGrade(@PathVariable int doctorId) {
        Double avg = reviewService.getAvgReviewGrade(doctorId);
        return ResponseEntity.ok(avg);
    }

    @GetMapping("/counter/{doctorId}")
    public ResponseEntity<?> getReviewCounter(@PathVariable int doctorId) {
        int counter = reviewService.getReviewCounter(doctorId);
        return ResponseEntity.ok(counter);
    }

    @PutMapping()
    public ResponseEntity<?> updateReview(@RequestBody ReviewDTO updatedReview) {
        List<Doctor> doctor = doctorService.getDoctorsByName(updatedReview.getDoctor());
        Review newReview = new Review(updatedReview.getId(), updatedReview.getText(), updatedReview.getGrade(), doctor.get(0));
        Review updated = reviewService.updateReview(newReview);
        return ResponseEntity.ok("Review updated");
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Boolean> deleteReviewById(@PathVariable int id) {
        Boolean isDeleted = reviewService.deleteReviewById(id);
        return new ResponseEntity<>(isDeleted, HttpStatus.OK);
    }
}
