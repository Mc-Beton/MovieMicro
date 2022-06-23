package com.kodilla.imdbmovie.utellyService.controller;


import com.kodilla.imdbmovie.utellyService.client.UtellyClient;
import com.kodilla.imdbmovie.utellyService.domain.LocationWatch;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/watchmovie")
@RequiredArgsConstructor
@CrossOrigin("*")
public class UtellyController {

    @Autowired
    private UtellyClient utellyClient;

    @GetMapping("/{imdbId}")
    public ResponseEntity<List<LocationWatch>> getSiteList(@PathVariable String imdbId) {
        return ResponseEntity.ok(utellyClient.getMovieWatch(imdbId));
    }
}
