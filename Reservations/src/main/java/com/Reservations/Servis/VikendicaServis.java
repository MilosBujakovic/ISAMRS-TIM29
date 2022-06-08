package com.Reservations.Servis;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Reservations.DTO.RegistracijaKorisnikaDTO;
import com.Reservations.DTO.VikendicaDTO;
import com.Reservations.Modeli.Korisnik;
import com.Reservations.Modeli.Uloga;
import com.Reservations.Modeli.Vikendica;
import com.Reservations.Repozitorijumi.RezervacijaRepozitorijum;
import com.Reservations.Repozitorijumi.VikendicaRepozitorijum;

@Service	
public class VikendicaServis {
	
	@Autowired
	private VikendicaRepozitorijum vikendicaRepozitorijum;
	
	@Autowired
	private RezervacijaRepozitorijum rezervacijaRepozitorijum;
	
	@Autowired
	private KorisnikServis korisnikServis;
	
	public List<Vikendica> listAll(){
		return vikendicaRepozitorijum.findAll();
	}
	public Vikendica findById(Long id) {
		try {
			return vikendicaRepozitorijum.findById(id).get();
		} catch (NoSuchElementException e) {
			return null;
		}
	}
	public List<Vikendica> findByVlasnik(long id) {
		List<Vikendica> lista = vikendicaRepozitorijum.findAll();
		for(Vikendica u : lista) {
			if (u.getVlasnik().getID() != id) lista.remove(u);
		}
		return lista;
	}
	
	
	public Vikendica dodajVikendicu(VikendicaDTO novaVikendica) {
		System.out.println("Dodaj vikendicu servis!");
		Vikendica vikendica = new Vikendica();
		vikendica.setAdresa(novaVikendica.getAdresa());
		vikendica.setBrojKreveta(novaVikendica.getBrojKreveta());
		vikendica.setBrojSoba(novaVikendica.getBrojSoba());
		vikendica.setCena(novaVikendica.getCena());
		vikendica.setLinkSlike(novaVikendica.getLinkSlike());
		vikendica.setNaziv(novaVikendica.getNaziv());
		vikendica.setOpis(novaVikendica.getOpis());
		System.out.println(novaVikendica.getVlasnik());
		Korisnik vlasnik = korisnikServis.findById(novaVikendica.getVlasnik());
		vikendica.setVlasnik(vlasnik);
		
		
		List<Vikendica> vikendice = vikendicaRepozitorijum.findAll();
		vikendica.setID(vikendice.size());
		return vikendicaRepozitorijum.save(vikendica);
		
	}
	
	public Vikendica izmijeniVikendicu(VikendicaDTO novaVikendica, Vikendica staraVikendica) {
		System.out.println("Dodaj vikendicu servis!");
		staraVikendica.setAdresa(novaVikendica.getAdresa());
		staraVikendica.setBrojKreveta(novaVikendica.getBrojKreveta());
		staraVikendica.setBrojSoba(novaVikendica.getBrojSoba());
		staraVikendica.setCena(novaVikendica.getCena());
		staraVikendica.setLinkSlike(novaVikendica.getLinkSlike());
		staraVikendica.setNaziv(novaVikendica.getNaziv());
		staraVikendica.setOpis(novaVikendica.getOpis());
		System.out.println(novaVikendica.getVlasnik());
	
		return vikendicaRepozitorijum.save(staraVikendica);
		
	}
	@SuppressWarnings("unused")
	public Long obrisiVikendicu(Long vlasnikID, Long vikendicaID) 
	{
		
		if(true)// TODO:provjeriti da li ima rezervacije?
		{	
			Vikendica staraVikendica = this.findById(vikendicaID);
			vikendicaRepozitorijum.delete(staraVikendica);
			return vlasnikID;
		}
		else return null;
	}

}