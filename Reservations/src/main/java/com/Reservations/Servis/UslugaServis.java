package com.Reservations.Servis;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Reservations.Modeli.Usluga;
import com.Reservations.Repozitorijumi.UslugaRepozitorijum;

@Service
public class UslugaServis
{

	@Autowired
	private UslugaRepozitorijum uslugaRepozitorijum;
	public List<Usluga> listAll(){
		return uslugaRepozitorijum.findAll();
	}
}