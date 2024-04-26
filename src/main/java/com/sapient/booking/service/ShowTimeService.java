package com.sapient.booking.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapient.booking.model.Movie;
import com.sapient.booking.model.ShowTime;
import com.sapient.booking.model.Theatre;
import com.sapient.booking.repository.MovieRepository;
import com.sapient.booking.repository.ShowRepository;
import com.sapient.booking.repository.TheatreRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ShowTimeService {
 	@Autowired
    private ShowRepository showRepository;
 	
 	@Autowired
    private TheatreRepository theatreRepository;
 	
 	@Autowired
    private MovieRepository movieRepository;

    public List<ShowTime> getShowsByMovieAndDateAndCity(Movie movie, LocalDate date, String cityName) {
        return showRepository.findByMovieAndDateAndTheatre_CityName(movie, date, cityName);
    }

    public ShowTime addShow(Long theatreId, ShowTime show) {
    	Theatre theatre = theatreRepository.findById(theatreId)
                .orElseThrow(() -> new EntityNotFoundException("Movie with given id not found"));    	
    	Optional<Movie> movie = Optional.of(movieRepository.findByTheatreId(theatreId));
    	
    	ShowTime  showTime = new ShowTime();
    	showTime.setDateTime(show.getDateTime());
    	showTime.setTheatre(show.getTheatre());
    	showTime.setMovie(show.getMovie());    	
        return showRepository.save(showTime);
    }
    
    public ShowTime createShow(ShowTime show) {
        return showRepository.save(show);
    }

    public ShowTime updateShow(Long showId, ShowTime updatedShow) {
        ShowTime existingShow = showRepository.findById(showId)
                .orElseThrow(() -> new EntityNotFoundException("Show with given name not found"));

        existingShow.setTheatre(updatedShow.getTheatre());
        existingShow.setMovie(updatedShow.getMovie());
        existingShow.setDateTime(updatedShow.getDateTime());
        return showRepository.save(existingShow);
    }

    public void deleteShowTime(Long TheatreId, Long showId) {
    	 ShowTime showTime = showRepository.findByIdAndTheatreId(showId, TheatreId)
                 .orElseThrow(() -> new EntityNotFoundException("Show with given name not found"));
        showRepository.delete(showTime);
    }
    
    public void deleteShow(Long showId) {
        showRepository.deleteById(showId);
    }

	public List<ShowTime> getShowsByTheatreAndDate(Long theatreId,  LocalDate date) {
		// TODO Auto-generated method stub
		return null;
	}
}
