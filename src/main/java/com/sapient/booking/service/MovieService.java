package com.sapient.booking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapient.booking.model.Movie;
import com.sapient.booking.model.Theatre;
import com.sapient.booking.repository.MovieRepository;

@Service
public class MovieService {

	@Autowired
	MovieRepository movieRepository;
	
	public Movie saveMovie(Movie movie) {
		 return movieRepository.save(movie);
	}

	public List<Movie> getAllMovies() {
		return movieRepository.findAll();
	}	
}
