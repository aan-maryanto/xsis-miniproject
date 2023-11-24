package com.example.xsisminiproject.service;

import com.example.xsisminiproject.entity.Movie;
import com.example.xsisminiproject.exception.ApiResponseException;
import com.example.xsisminiproject.repository.MovieRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.Instant;
import java.util.List;

@Service
@Transactional
public class MovieService {

    private final MovieRepository movieRepository;
    private final FileServiceImp fileServiceImp;

    public MovieService(MovieRepository movieRepository, FileServiceImp fileServiceImp) {
        this.movieRepository = movieRepository;
        this.fileServiceImp = fileServiceImp;
    }

    public List<Movie> getAllMovie() {
        return movieRepository.findAll();
    }

    public Movie getMovie(Integer id) {
        return movieRepository.findById(id).orElseThrow(() -> new ApiResponseException("Movie not found"));
    }

    public Movie postMovie(Movie requestBody) {
        requestBody.setCreatedAt(Instant.now());
        requestBody.setUpdatedAt(Instant.now());
        return movieRepository.save(requestBody);
    }

    public Movie updateMovie(Integer id, Movie requestBody) {
        requestBody.setId(id);
        requestBody.setUpdatedAt(Instant.now());
        return movieRepository.save(requestBody);
    }

    public void deleteMovie(Integer id) {
        movieRepository.deleteById(id);
    }

    public String uploadImageMovie(MultipartFile file) {
        var filename = file.getOriginalFilename();
        fileServiceImp.store(file);
        return fileServiceImp.load(filename).toString();
    }
}
