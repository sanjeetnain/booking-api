package com.sapient.booking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sapient.booking.model.Theatre;

@Repository
public interface TheatreRepository extends JpaRepository<Theatre, Long>{

	List<Theatre> findByCityName(String cityName);
}
