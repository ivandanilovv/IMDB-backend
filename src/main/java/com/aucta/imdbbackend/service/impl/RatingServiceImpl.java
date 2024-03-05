package com.aucta.imdbbackend.service.impl;

import com.aucta.imdbbackend.model.Rating;
import com.aucta.imdbbackend.model.exceptions.InvalidRatingIdException;
import com.aucta.imdbbackend.repository.RatingRepository;
import com.aucta.imdbbackend.service.RatingService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {

    private final RatingRepository ratingRepository;

    public RatingServiceImpl(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    @Override
    public List<Rating> listAllRatings() {
        return this.ratingRepository.findAll();
    }

    @Override
    public Rating findById(Long id) {
        return this.ratingRepository.findById(id).orElseThrow(InvalidRatingIdException::new);
    }

    @Override
    public Rating create(Integer rating) {
        Rating rating1 = new Rating(rating);
        return this.ratingRepository.save(rating1);
    }

}
