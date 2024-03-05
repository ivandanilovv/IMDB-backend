package com.aucta.imdbbackend.service.impl;

import com.aucta.imdbbackend.model.Review;
import com.aucta.imdbbackend.model.exceptions.InvalidReviewIdException;
import com.aucta.imdbbackend.repository.ReviewRepository;
import com.aucta.imdbbackend.service.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<Review> listAllReviews() {
        return this.reviewRepository.findAll();
    }

    @Override
    public Review findById(Long id) {
        return this.reviewRepository.findById(id).orElseThrow(InvalidReviewIdException::new);
    }

    @Override
    public Review create(String comment) {
        Review review = new Review(comment);
        return this.reviewRepository.save(review);
    }
}
