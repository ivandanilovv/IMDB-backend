package com.aucta.imdbbackend.model;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String genre;
    private Integer year;

    @OneToMany
    private List<Review> reviews;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Rating> ratings;

    private Double averageRating;

    public Movie(String title, String description, String genre, Integer year) {
        this.title = title;
        this.description = description;
        this.genre = genre;
        this.year = year;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    public Double getAverageRating() {
        if (this.ratings.isEmpty())
            return 0.0;
        else {
            int sum = this.ratings.stream().mapToInt(Rating::getRating).sum();
            return ((double) sum / this.ratings.size());
        }
    }

    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }

}
