package com.example.xsisminiproject.service;

import com.example.xsisminiproject.entity.Movie;
import com.example.xsisminiproject.exception.ApiResponseException;
import com.example.xsisminiproject.repository.MovieRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> getAllMovie() {
        return movieRepository.findAll();
    }

    public Movie getMovie(Integer id) {
        return movieRepository.findById(id).orElseThrow(() -> new ApiResponseException("Movie not found"));
    }

    public void postMovie(MultipartFile file, Movie requestBody) {
    }
}
