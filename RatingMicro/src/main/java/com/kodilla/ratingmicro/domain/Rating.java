package com.kodilla.ratingmicro.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "MOVIE_ID")
    private String movieId;

    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "RATE")
    private Integer movieRate;
}
