package com.sapient.booking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sapient.booking.model.Movie;
import com.sapient.booking.model.Theatre;
import com.sapient.booking.service.MovieService;

@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {

	@Autowired
	private MovieService movieService;
	
	@PostMapping("/movie")
    public ResponseEntity<Movie> addMovie(@RequestBody Movie movie) {
		Movie savedMovie = movieService.saveMovie(movie);
        return ResponseEntity.ok(savedMovie);
    }
	
	@GetMapping
	ResponseEntity<List<Movie>> getAllMovies() {
		 List<Movie> movies = movieService.getAllMovies();
	        return ResponseEntity.ok(movies);
    }
 
	@GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable Long id) {
        // Implement logic to retrieve a movie by ID
		return null;
    }

    @PostMapping
    public ResponseEntity<Movie> createMovie(@RequestBody Movie movie) {
        // Implement logic to create a new movie
    	return null;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable Long id, @RequestBody Movie movie) {
        // Implement logic to update a movie
    	return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMovie(@PathVariable Long id) {
	        // Implement logic to delete a movie
    	return null;
    }
}
