package com.sapient.booking.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sapient.booking.model.Theatre;

@Repository
public interface TheatreRepository extends JpaRepository<Theatre, Long>{

	List<Theatre> findByCityName(String cityName);
	
	@Query("SELECT t FROM Theatre t JOIN Movie m JOIN ShowTime s WHERE " +
			"t.cityName =: cityName AND m.movieName =: movieName AND DATE(s.date) =: date")
	List<Theatre> findTheatreByMovieAndCityNameAndDate(
			@Param("cityName") String cityName, 
			@Param("movieName") String movieName,
			@Param("date") LocalDate date
			);
}
