package com.healthwise.HealthwiseApp.service;

import com.healthwise.HealthwiseApp.entity.Review;
import com.healthwise.HealthwiseApp.repository.ReviewRepository;
import com.healthwise.HealthwiseApp.util.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    public Review addReview(Review review){
        return reviewRepository.save(review);
    }

    public List<Review> getAllReviews(){
        return reviewRepository.findAll();
    }

    public Optional<Review> getReviewById(int id){
        return reviewRepository.findById(id);
    }

    public List<Review> getReviewsByDoctorId(int doctorId) {
        return reviewRepository.findByDoctorId(doctorId);
    }

    public Double getAvgReviewGrade(int doctorId) {
        List<Review> reviewList = reviewRepository.findByDoctorId(doctorId);
        Double avg = 0.0;
        if (!reviewList.isEmpty()) {
            for (Review review : reviewList) {
                avg += review.getGrade();
            }
            avg /= reviewList.size();
        }
        return avg;
    }

    public int getReviewCounter(int doctorId){
        List<Review> reviewList = reviewRepository.findByDoctorId(doctorId);
        return reviewList.size();
    }

    public Review updateReview(Review updatedReview) {
        Optional<Review> existingReview = reviewRepository.findById(updatedReview.getId());
        if (existingReview.isPresent()) {
            Review review = existingReview.get();
            review.setText(updatedReview.getText());
            review.setGrade(updatedReview.getGrade());
            review.setDoctor(updatedReview.getDoctor());
            return reviewRepository.save(review);
        } else {
            throw new ResourceNotFoundException("Review with id: " + updatedReview.getId() + " not found");
        }
    }

    public Boolean deleteReviewById(int id) {
        if (!reviewRepository.existsById(id)) {
            throw new ResourceNotFoundException("Review with ID " + id + " not found");
        }
        reviewRepository.deleteById(id);
        return true;
    }
}
