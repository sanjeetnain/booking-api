package com.sapient.booking.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sapient.booking.model.Theatre;
import com.sapient.booking.service.TheatreService;

@RestController
@RequestMapping("/api/v1/theatres")
public class TheatreController {

	@Autowired
	private TheatreService theatreService;	
	
	@GetMapping("/theatres")
    public ResponseEntity<List<Theatre>> getTheatresByMovieNameAndCity(
            @RequestParam String movieName,          
            @RequestParam String city) {
        List<Theatre> theatres = theatreService.getTheatresByMovieNameAndCity(movieName,city);
        return new ResponseEntity<>(theatres, HttpStatus.OK);
    } 
	
	@GetMapping("/")
    public ResponseEntity<List<Theatre>> getAllTheaters() {
        List<Theatre> theaters = theatreService.getAllTheaters();
        return ResponseEntity.ok(theaters);
	}
	
	@GetMapping("/movie")
	public ResponseEntity<List<Theatre>> getTheatresByMovieNameAndDateAndCity(
	        @RequestParam String movieName,
	        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
	        @RequestParam String city) {
	
	    List<Theatre> theatres = theatreService.getTheatresByMovieNameAndDateAndCity(movieName, date, city);
	    return new ResponseEntity<>(theatres, HttpStatus.OK);
	}
	
    @GetMapping("/city")
    public ResponseEntity<List<Theatre>> getTheatresByCity(@RequestParam String city) {
        List<Theatre> theatres = theatreService.getTheatresByCity(city);
        return new ResponseEntity<>(theatres, HttpStatus.OK);
    }  

	@PostMapping("/onboard-theatre")
    public ResponseEntity<Theatre> onboardTheater(@RequestBody Theatre theatre) {
		Theatre savedTheatre = theatreService.saveTheatre(theatre);
        return ResponseEntity.ok(savedTheatre);
    }
}
