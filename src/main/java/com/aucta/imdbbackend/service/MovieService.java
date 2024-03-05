package com.aucta.imdbbackend.service;

import com.aucta.imdbbackend.model.Movie;
import com.aucta.imdbbackend.model.Rating;
import com.aucta.imdbbackend.model.Review;

import java.util.List;

public interface MovieService {

    List<Movie> listAllMovies();

    Movie findById(Long id);

    Movie create(String title, String description, String genre, Integer year);

    Movie update(Long id, String title, String description, String genre, Integer year);

    Movie updateRatings(Long id, List<Rating> ratings);

    Movie updateReviews(Long id, List<Review> reviews);

    List<Movie> filterMovies(String title, String genre, Integer year, Integer yearFrom, Integer yearTo);

}
