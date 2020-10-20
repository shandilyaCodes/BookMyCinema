package com.shandilya.movie.controller;

import com.shandilya.movie.dto.MovieDTO;
import com.shandilya.movie.model.Movie;
import com.shandilya.movie.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/movie")
public class MovieController {

    private final MovieService movieService;

    @PostMapping("/register")
    public ResponseEntity<?> registerMovie(@RequestBody MovieDTO movieDTO) {
        movieService.registerMovie(movieDTO);
        return ResponseEntity.ok("");
    }
}