package com.Reservations.Repozitorijumi;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Reservations.Modeli.Rezervacija;

public interface RezervacijaRepozitorijum extends JpaRepository<Rezervacija,Long>{
	
	Rezervacija findByNazivEntiteta(String username);
	List<Rezervacija> findAll();
	Optional<Rezervacija> findById(Long Id);
	List<Rezervacija> findByEntitetId(Long entitetId);
	
    
}
