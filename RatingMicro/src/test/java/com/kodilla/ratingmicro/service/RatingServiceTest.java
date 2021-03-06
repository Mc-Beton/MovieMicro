package com.kodilla.ratingmicro.service;

import com.kodilla.ratingmicro.domain.Rating;
import com.kodilla.ratingmicro.domain.RatingDto;
import com.kodilla.ratingmicro.exceptions.RatingNotFoundException;
import com.kodilla.ratingmicro.mapper.RatingMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class RatingServiceTest {

    @Autowired
    private RatingService ratingService;

    @Autowired
    private RatingMapper ratingMapper;

    @Test
    void addNewRating() throws RatingNotFoundException {
        //Given
        Rating rating = new Rating("tt1234567", 1L, 6);

        //When
        ratingService.saveNewRating(rating);
        Long id = ratingService.getRatingById(rating.getId()).getId();

        //Then
        assertEquals(1, ratingService.getAllRatings().size());

        //Clean up
        ratingService.deleteRating(id);
    }

    @Test
    void getRatingsByMovieTest() {
        //Given
        Rating rating = new Rating("tt1234567", 1L, 6);
        Rating rating2 = new Rating("tt1234567", 2L, 5);

        //When
        ratingService.saveNewRating(rating);
        ratingService.saveNewRating(rating2);

        //Then
        assertEquals(2, ratingService.getRatingsByMovie("tt1234567").size());

        //Clean up
        ratingService.deleteRating(rating.getId());
        ratingService.deleteRating(rating2.getId());
    }

    @Test
    void getRatingByMovieAndUserTest() throws RatingNotFoundException {
        //Given
        Rating rating = new Rating("tt1234567", 1L, 6);

        //When
        ratingService.saveNewRating(rating);

        //Then
        assertTrue(ratingService.getRatingByUserandMovie("tt1234567", 1L).getMovieRate().equals(6));

        //Clean up
        ratingService.deleteRating(rating.getId());
    }

    @Test
    void getRatingDtoDataTest() throws RatingNotFoundException {
        //Given
        Rating rating = new Rating("tt1234567", 1L, 6);

        //When
        ratingService.saveNewRating(rating);
        Long id = ratingService.getRatingById(rating.getId()).getId();
        RatingDto ratingDto = ratingMapper.mapToRatingDto(ratingService.getRatingById(id));

        //Then
        assertEquals(id, ratingDto.getId());
        assertEquals(rating.getMovieId(), ratingDto.getMovieId());
        assertEquals(rating.getUserId(), ratingDto.getUserId());
        assertEquals(rating.getMovieRate(),ratingDto.getRating());

        //Clean up
        ratingService.deleteRating(id);
    }

    @Test
    void mapperShouldReturnTheSame() throws RatingNotFoundException {
        //Given
        Rating rating = new Rating("tt1234567", 1L, 6);

        //When
        ratingService.saveNewRating(rating);
        Long id = ratingService.getRatingById(rating.getId()).getId();
        RatingDto ratingDto = ratingMapper.mapToRatingDto(ratingService.getRatingById(id));
        Rating checkRating = ratingMapper.mapToRating(ratingDto);

        //Then
        assertEquals(checkRating.getId(), ratingDto.getId());
        assertEquals(checkRating.getMovieId(), ratingDto.getMovieId());
        assertEquals(checkRating.getUserId(), ratingDto.getUserId());
        assertEquals(checkRating.getMovieRate(),ratingDto.getRating());

        //Clean up
        ratingService.deleteRating(id);
    }
}