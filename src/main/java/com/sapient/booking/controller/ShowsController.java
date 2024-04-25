package com.sapient.booking.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sapient.booking.model.Movie;
import com.sapient.booking.model.ShowTime;
import com.sapient.booking.repository.MovieRepository;
import com.sapient.booking.service.ShowTimeService;

@RestController
@RequestMapping("/api/v1/shows")
public class ShowsController {

	@Autowired
    private ShowTimeService showService;
    @Autowired
    private MovieRepository movieRepository;

    @GetMapping("/")
    public ResponseEntity<List<ShowTime>> getShowsByMovieNameAndDateAndCity(
            @RequestParam String movieName,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam String cityName) {

        Movie movie = movieRepository.findByMovieName(movieName);
        List<ShowTime> shows = showService.getShowsByMovieAndDateAndCity(movie, date, cityName);
        return new ResponseEntity<>(shows, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ShowTime> createShow(@RequestBody ShowTime show) {
        ShowTime createdShow = showService.createShow(show);
        return new ResponseEntity<>(createdShow, HttpStatus.CREATED);
    }

    @PutMapping("/{showId}")
    public ResponseEntity<ShowTime> updateShow(@PathVariable Long showId, @RequestBody ShowTime updatedShow) {
        ShowTime updated = showService.updateShow(showId, updatedShow);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{showId}")
    public ResponseEntity<Void> deleteShow(@PathVariable Long showId) {
        showService.deleteShow(showId);
        return ResponseEntity.noContent().build();
    }
}
