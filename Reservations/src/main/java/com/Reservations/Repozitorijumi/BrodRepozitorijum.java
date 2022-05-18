package com.Reservations.Repozitorijumi;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Reservations.Modeli.Brod;

public interface BrodRepozitorijum extends JpaRepository<Brod, Long>
{
	Brod findByNaziv(String name);
}
