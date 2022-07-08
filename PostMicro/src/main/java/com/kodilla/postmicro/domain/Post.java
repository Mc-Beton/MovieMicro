package com.kodilla.postmicro.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "MOVIE_ID")
    private String movieId;

    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "CONTENT")
    private String content;

    public Post(String movieId, Long userId, String content) {
        this.movieId = movieId;
        this.userId = userId;
        this.content = content;
    }
}
