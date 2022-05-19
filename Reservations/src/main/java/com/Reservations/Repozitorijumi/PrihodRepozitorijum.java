package com.Reservations.Repozitorijumi;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Reservations.Modeli.Prihod;

public interface PrihodRepozitorijum extends JpaRepository<Prihod, Long> {
	
}
