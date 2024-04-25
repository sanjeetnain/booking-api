package com.sapient.booking.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sapient.booking.model.Movie;
import com.sapient.booking.model.ShowTime;

@Repository
public interface ShowRepository extends JpaRepository<ShowTime, Long> {
	 List<ShowTime> findByMovieAndDateAndTheatre_CityName(Movie movie, LocalDate date, String cityName);
}
