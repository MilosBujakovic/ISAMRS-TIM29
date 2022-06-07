package com.Reservations.Repozitorijumi;


import org.springframework.data.jpa.repository.JpaRepository;

import com.Reservations.Modeli.Vikendica;

public interface VikendicaRepozitorijum extends JpaRepository<Vikendica, Long> 
{
	Vikendica findByNaziv(String naziv);
}
