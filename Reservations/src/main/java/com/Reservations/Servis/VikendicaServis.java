package com.Reservations.Servis;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Reservations.DTO.VikendicaDTO;
import com.Reservations.Modeli.Korisnik;
import com.Reservations.Modeli.Rezervacija;
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
	private RezervacijaServis rezervacijaServis;
	
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
	
	
	public String dodajVikendicu(VikendicaDTO novaVikendica) {
		String poruka;
		try
		{
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
			Long ID = 0L;
			for(Vikendica vik : vikendice)
			{	
				if(vik.getID()==ID)
				{
					ID++;
				}
			}
			vikendica.setID(ID);
			vikendicaRepozitorijum.save(vikendica);
			poruka = "Vikendica je uspjesno dodata!";
			return poruka;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			poruka = "Doslo je do greske pri dodavanju!";
			
		}
		return poruka;
	}
	
	public String izmijeniVikendicu(VikendicaDTO novaVikendica, Vikendica staraVikendica) {
		List<Rezervacija> rezervacije = rezervacijaRepozitorijum.findByEntitetId(staraVikendica.getID());
		for(Rezervacija rez : rezervacije)
		{
			if(staraVikendica.getNaziv()!=rez.getNazivEntiteta())
				rezervacije.remove(rez);
		}
		if(rezervacije==null || rezervacije.isEmpty())
		{
			System.out.println("Izmjena vikendice servis!");
			staraVikendica.setAdresa(novaVikendica.getAdresa());
			staraVikendica.setBrojKreveta(novaVikendica.getBrojKreveta());
			staraVikendica.setBrojSoba(novaVikendica.getBrojSoba());
			staraVikendica.setCena(novaVikendica.getCena());
			
			if(novaVikendica.getLinkSlike()!=null || novaVikendica.getLinkSlike()!="")
				staraVikendica.setLinkSlike(novaVikendica.getLinkSlike());
			
			staraVikendica.setNaziv(novaVikendica.getNaziv());
			staraVikendica.setOpis(novaVikendica.getOpis());
			System.out.println(novaVikendica.getVlasnik());
		
			vikendicaRepozitorijum.save(staraVikendica);
			return "Izmjena vikendice je uspjesna";
		}
		else return "Doslo je do greske, vikendica je vec rezervisana!";
			
	}
	
	public String obrisiVikendicu(Long vlasnikID, Long vikendicaID) 
	{
		Vikendica staraVikendica = this.findById(vikendicaID);
		List<Rezervacija> rezervacije = rezervacijaRepozitorijum.findByEntitetId(staraVikendica.getID());
		for(Rezervacija rez : rezervacije)
		{
			if(staraVikendica.getNaziv()!=rez.getNazivEntiteta())
				rezervacije.remove(rez);
		}
		if(rezervacije==null || rezervacije.isEmpty())
		{	
			vikendicaRepozitorijum.delete(staraVikendica);
			return "Brisanje vikendice je uspjesno!";
		}
		else return "Doslo je do greske, vikendica je vec rezervisana!";
	}

}