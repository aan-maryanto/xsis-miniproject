package com.example.xsisminiproject.service;

import com.example.xsisminiproject.entity.Movie;
import com.example.xsisminiproject.exception.ApiResponseException;
import com.example.xsisminiproject.repository.MovieRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
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

    public Movie postMovie(Movie requestBody) {
        return movieRepository.save(requestBody);
    }

    public Movie updateMovie(Integer id, Movie requestBody) {
        requestBody.setId(id);
        return movieRepository.save(requestBody);
    }

    public void deleteMovie(Integer id) {
        movieRepository.deleteById(id);
    }

    public String uploadImageMovie(MultipartFile file) {
        return null;
    }
}
