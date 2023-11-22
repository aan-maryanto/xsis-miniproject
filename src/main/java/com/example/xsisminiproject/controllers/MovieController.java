package com.example.xsisminiproject.controllers;

import com.example.xsisminiproject.entity.Movie;
import com.example.xsisminiproject.service.MovieService;
import lombok.AllArgsConstructor;
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
            @RequestParam("image")MultipartFile file,
            @RequestBody Movie requestBody
    ) {
        movieService.postMovie(file, requestBody);
        return ResponseEntity.ok("ok");
    }


}
