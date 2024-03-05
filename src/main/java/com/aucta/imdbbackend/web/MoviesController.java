package com.aucta.imdbbackend.web;

import com.aucta.imdbbackend.model.Movie;
import com.aucta.imdbbackend.model.Rating;
import com.aucta.imdbbackend.model.Review;
import com.aucta.imdbbackend.service.MovieService;
import com.aucta.imdbbackend.service.RatingService;
import com.aucta.imdbbackend.service.ReviewService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class MoviesController {


    private final MovieService movieService;
    private final RatingService ratingService;

    private final ReviewService reviewService;

    public MoviesController(MovieService movieService, RatingService ratingService, ReviewService reviewService) {
        this.movieService = movieService;
        this.ratingService = ratingService;
        this.reviewService = reviewService;
    }

    @GetMapping({"/", "/movies"})
    public List<Movie> showMovies(@RequestParam(required = false) String title,
                                  @RequestParam(required = false) String genre,
                                  @RequestParam(required = false) Integer year,
                                  @RequestParam(required = false) Integer yearFrom,
                                  @RequestParam(required = false) Integer yearTo) {
        return this.movieService.filterMovies(title, genre, year, yearFrom, yearTo);
    }

    @GetMapping("/reviews")
    public List<Review> showReviews() {
        return this.reviewService.listAllReviews();
    }

    @GetMapping("/movie/{id}")
    public Movie showMovieDetails(@PathVariable Long id) {
        return this.movieService.findById(id);
    }

    @PostMapping("/movies")
    public String addMovie(@RequestParam String title,
                           @RequestParam String description,
                           @RequestParam String genre,
                           @RequestParam Integer year) {
        this.movieService.create(title, description, genre, year);
        return "Success";
    }

    @PostMapping("/movies/{id}/rate")
    public String rateMovie(@PathVariable Long id,
                            @RequestParam Integer rating) {
        Rating rat1 = this.ratingService.create(rating);
        Movie movie = this.movieService.findById(id);
        List<Rating> movieRatings = movie.getRatings();
        movieRatings.add(rat1);
        this.movieService.updateRatings(id, movieRatings);
        return "Success";
    }

    @PostMapping("/movies/{id}/review")
    public String reviewMovie(@PathVariable Long id,
                              @RequestParam String review) {
        Review rev1 = this.reviewService.create(review);
        Movie movie = this.movieService.findById(id);
        List<Review> movieReviews = movie.getReviews();
        movieReviews.add(rev1);
        this.movieService.updateReviews(id, movieReviews);
        return "Success";
    }

}
