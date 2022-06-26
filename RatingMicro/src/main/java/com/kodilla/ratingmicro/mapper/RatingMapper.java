package com.kodilla.ratingmicro.mapper;

import com.kodilla.ratingmicro.domain.Rating;
import com.kodilla.ratingmicro.domain.RatingDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RatingMapper {

    public Rating mapToRating(RatingDto ratingDto) {
        return new Rating(
                ratingDto.getId(),
                ratingDto.getMovieId(),
                ratingDto.getUserId(),
                ratingDto.getRating()
        );
    }

    public RatingDto mapToRatingDto(Rating rating) {
        return new RatingDto(
                rating.getId(),
                rating.getMovieId(),
                rating.getUserId(),
                rating.getMovieRate()
        );
    }

    public List<RatingDto> mapToDtoList(List<Rating> ratingList) {
        return ratingList.stream()
                .map(this::mapToRatingDto)
                .collect(Collectors.toList());
    }
}
