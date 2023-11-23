package com.example.xsisminiproject.controllers;

import com.example.xsisminiproject.entity.Movie;
import com.example.xsisminiproject.service.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@AllArgsConstructor
@RequestMapping("/movie")
public class MovieController {

    private final MovieService movieService;

    @GetMapping
    public ResponseEntity getAll() {
        var results = movieService.getAllMovie();
        return ResponseEntity.ok(results);
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable("id") Integer id) {
        var result = movieService.getMovie(id);
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity createMovie(
            @RequestBody Movie requestBody
    ) {
        var result = movieService.postMovie(requestBody);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateMovie(
            @PathVariable("id") Integer id,
            @RequestBody Movie requestBody
    ) {
        var result = movieService.updateMovie(id, requestBody);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteMovie(
            @PathVariable("id") Integer id
    ) {
        movieService.deleteMovie(id);
        return ResponseEntity.ok("Success Delete");
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity uploadImageMovie(
            @RequestParam("file") MultipartFile file
    ) {
        var result = movieService.uploadImageMovie(file);
        return ResponseEntity.ok(result);
    }


}
