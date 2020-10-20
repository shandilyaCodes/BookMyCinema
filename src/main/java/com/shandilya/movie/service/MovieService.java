package com.shandilya.movie.service;

import com.shandilya.movie.dto.MovieDTO;
import com.shandilya.movie.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;

    public void registerMovie(MovieDTO movieDTO) {
        movieRepository.save(movieDTO.transform());
    }
}