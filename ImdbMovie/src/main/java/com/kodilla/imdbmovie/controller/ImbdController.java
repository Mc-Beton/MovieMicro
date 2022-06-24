package com.kodilla.imdbmovie.controller;

import com.kodilla.imdbmovie.domain.ImbdMovieDetailsDto;
import com.kodilla.imdbmovie.domain.ImdbMovieDto;
import com.kodilla.imdbmovie.client.IMBDClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/movies/")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ImbdController {

    private final IMBDClient imbdClient;

    @GetMapping
    public ResponseEntity<List<ImdbMovieDto>> getFirstPage() {
        return ResponseEntity.ok(imbdClient.getMoviesImdb());
    }

    @GetMapping("soon")
    public ResponseEntity<List<ImdbMovieDto>> getSoonPage() {
        return ResponseEntity.ok(imbdClient.getComingSoonImdb());
    }

    @GetMapping("top250")
    public ResponseEntity<List<ImdbMovieDto>> get250Page() {
        return ResponseEntity.ok(imbdClient.getTop250Imdb());
    }

    @GetMapping("topTV")
    public ResponseEntity<List<ImdbMovieDto>> get250TVPage() {
        return ResponseEntity.ok(imbdClient.getTopTvImdb());
    }

    @GetMapping("movieImbd_details/{movieId}")
    public ResponseEntity<ImbdMovieDetailsDto> getMovieImbdDetails(@PathVariable String movieId) {
        return ResponseEntity.ok(imbdClient.getMovieDetailsImbd(movieId));
    }

    @GetMapping("search/{content}")
    public ResponseEntity<List<ImdbMovieDto>> searchThis(@PathVariable String content) {
        return ResponseEntity.ok(imbdClient.searchMovies(content));
    }


}
