package com.kodilla.ratingmicro.service;

import com.kodilla.ratingmicro.Repository.RatingRepository;
import com.kodilla.ratingmicro.domain.Rating;
import com.kodilla.ratingmicro.exceptions.RatingNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RatingService {

    private final RatingRepository ratingRepository;

    public Rating saveNewRating(Rating rating) {
        return ratingRepository.save(rating);
    }

    public List<Rating> getAllRatings() {
        return ratingRepository.findAll();
    }

    public List<Rating> getRatingsByMovie(String id) {
        Optional<List<Rating>> movieRating = Optional.of(ratingRepository.findAllByMovieId(id).get());
        return movieRating.orElseGet(ArrayList::new);
    }

    public Rating getRatingById(Long id) throws RatingNotFoundException {
        Optional<Rating> rating = Optional.of(ratingRepository.findById(id).get());
        return rating.get();
    }

    public Rating getRatingByUserandMovie(String movieId, Long userId) throws RatingNotFoundException {
        Optional<Rating> rating = Optional.of(ratingRepository.findByMovieIdAndUserId(movieId, userId).get());
        return rating.get();
    }

    public void deleteRating(Long id) {
        ratingRepository.deleteById(id);
    }
}
