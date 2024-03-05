package com.aucta.imdbbackend.service;

import com.aucta.imdbbackend.model.Review;

import java.util.List;

public interface ReviewService {

    List<Review> listAllReviews();

    Review findById(Long id);

    Review create(String reviewComment);

}
