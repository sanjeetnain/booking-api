package com.sapient.booking.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapient.booking.model.Movie;
import com.sapient.booking.model.ShowTime;
import com.sapient.booking.model.Theatre;
import com.sapient.booking.repository.MovieRepository;
import com.sapient.booking.repository.ShowRepository;
import com.sapient.booking.repository.TheatreRepository;

@Service
public class TheatreService {

	@Autowired
	TheatreRepository theatreRepository;	

    @Autowired
    private ShowRepository showRepository;
    
	@Autowired
	private MovieRepository movieRepository;

	public Theatre saveTheatre(Theatre theatre) {
        return theatreRepository.save(theatre);
    }

	public List<Theatre> getAllTheaters() {
	    return theatreRepository.findAll();
	}	
	
	public List<Theatre> getTheatresByMovieNameAndCity(String movieName, String city) {
		 Movie movie = movieRepository.findByMovieName(movieName);
	    return showRepository.findByMovieAndTheatre_CityName(movie, city)
	            .stream()
	            .map(ShowTime::getTheatre)
	            .collect(Collectors.toList());
	}
	
    public List<Theatre> getTheatresByCity(String city) {
        return theatreRepository.findByCityName(city);
    }

    public List<Theatre> getTheatresByMovieNameAndDateAndCity(String movieName, LocalDate date, String cityName) {
        Movie movie = movieRepository.findByMovieName(movieName);
        return showRepository.findByMovieAndDateAndTheatre_CityName(movie, date, cityName)
                .stream()
                .map(ShowTime::getTheatre)
                .collect(Collectors.toList());
    }

}
