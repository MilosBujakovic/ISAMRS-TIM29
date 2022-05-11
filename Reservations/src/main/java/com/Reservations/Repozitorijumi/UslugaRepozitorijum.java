package com.Reservations.Repozitorijumi;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Reservations.Modeli.Usluga;

public interface UslugaRepozitorijum  extends JpaRepository<Usluga, Long>
{
	Usluga findByNaziv(String naziv);
}
