package com.Reservations.Servis;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;

import com.Reservations.DTO.RezervacijaDTO;
import com.Reservations.Modeli.Rezervacija;
import com.Reservations.Modeli.ZahtevZaBrisanje;
import com.Reservations.Repozitorijumi.RezervacijaRepozitorijum;

public class RezervacijaServis {

	@Autowired
	private RezervacijaRepozitorijum rezRepozitorijum;


	
	public Rezervacija findById(Long id) {
		try {
			return rezRepozitorijum.findById(id).get();
		} catch (NoSuchElementException e) {
			return null;
		}
	}

	public Rezervacija save(RezervacijaDTO regRequest) {
		Rezervacija r = new Rezervacija();
		r.setDatum(regRequest.getDatum());
		r.setVreme(regRequest.getVreme());
		r.setTrajanje(regRequest.getTrajanje());
		r.setMaxOsoba(regRequest.getMaxOsoba());
		r.setNazivEntiteta(regRequest.getNazivEntiteta());
		
	
		
		return this.rezRepozitorijum.save(r);// TODO Auto-generated method stub
	}

	public List<Rezervacija> listAll() {
		return this.rezRepozitorijum.findAll();
	}
	
	public void delete(long id) {
		this.rezRepozitorijum.deleteById(id);
	}
	public Rezervacija findByIme(String ime){
		return this.rezRepozitorijum.findByNazivEntiteta(ime);

		}
}
