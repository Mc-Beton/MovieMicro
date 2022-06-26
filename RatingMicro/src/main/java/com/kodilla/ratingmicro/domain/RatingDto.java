package com.kodilla.ratingmicro.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RatingDto {

    private Long id;
    private String movieId;
    private Long userId;
    private Integer rating;
}
