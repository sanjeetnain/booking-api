package com.sapient.booking.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapient.booking.model.Movie;
import com.sapient.booking.model.ShowTime;
import com.sapient.booking.repository.ShowRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ShowTimeService {
	 @Autowired
	    private ShowRepository showRepository;

	    public List<ShowTime> getShowsByMovieAndDateAndCity(Movie movie, LocalDate date, String cityName) {
	        return showRepository.findByMovieAndDateAndTheatre_CityName(movie, date, cityName);
	    }

	    public ShowTime createShow(ShowTime show) {
	        return showRepository.save(show);
	    }

	    public ShowTime updateShow(Long showId, ShowTime updatedShow) {
	        ShowTime existingShow = showRepository.findById(showId)
	                .orElseThrow(() -> new EntityNotFoundException("Show not found"));

	        existingShow.setTheatre(updatedShow.getTheatre());
	        existingShow.setMovie(updatedShow.getMovie());
	        existingShow.setDate(updatedShow.getDate());
	        existingShow.setTime(updatedShow.getTime());

	        return showRepository.save(existingShow);
	    }

	    public void deleteShow(Long showId) {
	        showRepository.deleteById(showId);
	    }
}
