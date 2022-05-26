package com.Reservations.Servis;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Reservations.Modeli.Korisnik;
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
	
	public Usluga findById(Long id){
		try {
			return uslugaRepozitorijum.findById(id).get();
		} catch (NoSuchElementException e) {
			return null;
		}
	}

	public List<Usluga> findByInstruktor(long id) {
		List<Usluga> lista = uslugaRepozitorijum.findAll();
		for(Usluga u : lista) {
			if (u.getInstruktorID().getID() != id) lista.remove(u);
		}
		return lista;
	}
}