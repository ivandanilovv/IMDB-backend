package com.aucta.imdbbackend.service;

import com.aucta.imdbbackend.model.Rating;

import java.util.List;

public interface RatingService {

    List<Rating> listAllRatings();

    Rating findById(Long id);

    Rating create(Integer rating);

}
