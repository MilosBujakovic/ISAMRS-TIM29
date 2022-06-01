package com.Reservations.Repozitorijumi;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Reservations.Modeli.Vikendica;

public interface VikendicaRepozitorijum extends JpaRepository<Vikendica, Long> 
{
	Vikendica findByNaziv(String naziv);
	Optional<Vikendica> findById(Long id);
}
