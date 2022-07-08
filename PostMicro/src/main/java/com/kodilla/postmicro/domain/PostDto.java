package com.kodilla.postmicro.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PostDto {

    private Long id;
    private String movieId;
    private Long userId;
    private String content;

    public PostDto(String movieId, Long userId, String content) {
        this.movieId = movieId;
        this.userId = userId;
        this.content = content;
    }
}
