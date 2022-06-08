package com.Reservations.Repozitorijumi;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Reservations.Modeli.Registracija;
import com.Reservations.Modeli.Rezervacija;

public interface RezervacijaRepozitorijum extends JpaRepository<Rezervacija,Long>{
	
	Rezervacija findByNazivEntiteta(String username);
	
	
    
}
