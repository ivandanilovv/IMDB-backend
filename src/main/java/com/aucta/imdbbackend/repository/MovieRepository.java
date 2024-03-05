package com.aucta.imdbbackend.repository;

import com.aucta.imdbbackend.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {


    List<Movie> findMoviesByTitleContains(String text);

    List<Movie> findMoviesByGenreContains(String text);

    List<Movie> findMoviesByYearIs(Integer year);

    List<Movie> findMoviesByYearGreaterThanEqualAndYearLessThanEqual(Integer from, Integer to);

    List<Movie> findMoviesByTitleContainsAndGenreContains(String title, String genre);

    List<Movie> findMoviesByTitleContainsAndAndYearIs(String title, Integer year);

    List<Movie> findMoviesByGenreContainsAndYearIs(String genre, Integer year);

    List<Movie> findMoviesByTitleContainsAndGenreContainsAndYearIs(String title, String genre, Integer year);

    List<Movie> findMoviesByTitleContainsAndAndYearGreaterThanEqualAndYearLessThanEqual
            (String title, Integer yearFrom, Integer yearTo);

    List<Movie> findMoviesByGenreContainsAndYearGreaterThanEqualAndYearLessThanEqual
            (String genre, Integer yearFrom, Integer yearTo);

    List<Movie> findMoviesByTitleContainsAndGenreContainsAndYearGreaterThanEqualAndYearLessThanEqual
            (String title, String genre, Integer yearFrom, Integer yearTo);

}
