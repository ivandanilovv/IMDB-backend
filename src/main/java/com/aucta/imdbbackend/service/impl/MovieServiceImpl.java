package com.aucta.imdbbackend.service.impl;

import com.aucta.imdbbackend.model.Movie;
import com.aucta.imdbbackend.model.Rating;
import com.aucta.imdbbackend.model.Review;
import com.aucta.imdbbackend.model.exceptions.InvalidMovieIdException;
import com.aucta.imdbbackend.repository.MovieRepository;
import com.aucta.imdbbackend.service.MovieService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public List<Movie> listAllMovies() {
        return this.movieRepository.findAll();
    }

    @Override
    public Movie findById(Long id) {
        return this.movieRepository.findById(id).orElseThrow(InvalidMovieIdException::new);
    }

    @Override
    public Movie create(String title, String description, String genre, Integer year) {
        Movie movie = new Movie(title, description, genre, year);
        return this.movieRepository.save(movie);
    }

    @Override
    public Movie update(Long id, String title, String description, String genre, Integer year) {
        Movie movie = this.findById(id);
        movie.setTitle(title);
        movie.setDescription(description);
        movie.setGenre(genre);
        movie.setYear(year);
        return this.movieRepository.save(movie);
    }

    @Override
    public Movie updateRatings(Long id, List<Rating> ratings) {
        Movie movie = this.findById(id);
        movie.setRatings(ratings);
        return this.movieRepository.save(movie);
    }

    @Override
    public Movie updateReviews(Long id, List<Review> reviews) {
        Movie movie = this.findById(id);
        movie.setReviews(reviews);
        return this.movieRepository.save(movie);
    }

    @Override
    public List<Movie> filterMovies(String title, String genre, Integer year, Integer yearFrom, Integer yearTo) {
        // Filtering by only one parameter
        if (title != null && genre == null && year == null && yearFrom == null && yearTo == null)
            return this.movieRepository.findMoviesByTitleContains(title);
        else if (title == null && genre != null && year == null && yearFrom == null && yearTo == null)
            return this.movieRepository.findMoviesByGenreContains(genre);
        else if (title == null && genre == null && year != null && yearFrom == null && yearTo == null)
            return this.movieRepository.findMoviesByYearIs(year);
        else if (title == null && genre == null && year == null && yearFrom != null && yearTo != null)
            return this.movieRepository.findMoviesByYearGreaterThanEqualAndYearLessThanEqual(yearFrom, yearTo);

        // Filtering by multiple parameters with exact year
        else if (title != null && genre != null && year == null && yearFrom == null && yearTo == null)
            return this.movieRepository.findMoviesByTitleContainsAndGenreContains(title, genre);
        else if (title != null && genre == null && year != null && yearFrom == null && yearTo == null)
            return this.movieRepository.findMoviesByTitleContainsAndAndYearIs(title, year);
        else if (title == null && genre != null && year != null && yearFrom == null && yearTo == null)
            return this.movieRepository.findMoviesByGenreContainsAndYearIs(genre, year);
        else if (title != null && genre != null && year != null && yearFrom == null && yearTo == null)
            return this.movieRepository.findMoviesByTitleContainsAndGenreContainsAndYearIs(title, genre, year);

        // Filtering by multiple parameters with range of years
        else if (title != null && genre == null && year == null && yearFrom != null && yearTo != null)
            return this.movieRepository.findMoviesByTitleContainsAndAndYearGreaterThanEqualAndYearLessThanEqual
                    (title, yearFrom, yearTo);
        else if (title == null && genre != null && year == null && yearFrom != null && yearTo != null)
            return this.movieRepository.findMoviesByGenreContainsAndYearGreaterThanEqualAndYearLessThanEqual
                    (genre, yearFrom, yearTo);
        else if (title != null && genre != null && year == null && yearFrom != null && yearTo != null)
            return this.movieRepository.findMoviesByTitleContainsAndGenreContainsAndYearGreaterThanEqualAndYearLessThanEqual
                    (title, genre, yearFrom, yearTo);

        // Not filtering
        else
            return this.movieRepository.findAll();
    }


}
