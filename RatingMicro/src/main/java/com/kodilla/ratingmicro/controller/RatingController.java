package com.kodilla.ratingmicro.controller;

import com.kodilla.ratingmicro.domain.Rating;
import com.kodilla.ratingmicro.domain.RatingDto;
import com.kodilla.ratingmicro.exceptions.RatingNotFoundException;
import com.kodilla.ratingmicro.mapper.RatingMapper;
import com.kodilla.ratingmicro.service.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rating")
@RequiredArgsConstructor
public class RatingController {

    private final RatingService ratingService;
    private final RatingMapper ratingMapper;

    @GetMapping("/all")
    public ResponseEntity<List<RatingDto>> getAllRatings() {
        List<Rating> ratingList = ratingService.getAllRatings();
        return ResponseEntity.ok(ratingMapper.mapToDtoList(ratingList));
    }

    @GetMapping("{userId}/movie/{movieId}")
    public ResponseEntity<RatingDto> getUserRatingForMovie(@PathVariable Long userId, @PathVariable String movieId) throws RatingNotFoundException {
        return ResponseEntity.ok(ratingMapper.mapToRatingDto(ratingService.getRatingByUserandMovie(movieId, userId)));
    }

    @GetMapping("/movie/{movieId}")
    public ResponseEntity<List<RatingDto>> getMovieRatings(@PathVariable String movieId) throws RatingNotFoundException {
        return ResponseEntity.ok(ratingMapper.mapToDtoList(ratingService.getRatingsByMovie(movieId)));
    }

    @GetMapping("{ratingId}")
    public ResponseEntity<RatingDto> getRatingById(@PathVariable Long ratingId) throws RatingNotFoundException {
        return ResponseEntity.ok(ratingMapper.mapToRatingDto(ratingService.getRatingById(ratingId)));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addNewRating(@RequestBody RatingDto ratingDto) {
        Rating rating = ratingMapper.mapToRating(ratingDto);
        ratingService.saveNewRating(rating);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("delete/{ratingId}")
    public ResponseEntity<Void> deleteRating(@PathVariable Long ratingId) {
        ratingService.deleteRating(ratingId);
        return ResponseEntity.ok().build();
    }
}
