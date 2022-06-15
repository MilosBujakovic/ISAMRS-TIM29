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
	
	
	public String[] dodajVikendicu(VikendicaDTO novaVikendica) {
		String[] poruka = new String[2];
		try
		{
			System.out.println("Dodaj vikendicu servis!");
			Vikendica vikendica = vikendicaRepozitorijum.findByNaziv(novaVikendica.getNaziv());
					//findByNaziv(novaVikendica.getNaziv());
			System.out.println("find by naziv: "+ vikendica);
			if(vikendica==null)
			{
				vikendica = new Vikendica();
				vikendica.setAdresa(novaVikendica.getAdresa());
				vikendica.setBrojKreveta(novaVikendica.getBrojKreveta());
				vikendica.setBrojSoba(novaVikendica.getBrojSoba());
				vikendica.setCena(novaVikendica.getCena());
				vikendica.setLinkSlike(novaVikendica.getLinkSlike());
				vikendica.setNaziv(novaVikendica.getNaziv());
				vikendica.setOpis(novaVikendica.getOpis());
				vikendica.setLinkInterijera(novaVikendica.getLinkInterijera());
				vikendica.setDodatneUsluge(novaVikendica.getDodatneUsluge());
				vikendica.setPravilaPonasanja(novaVikendica.getPravilaPonasanja());
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
				poruka[0] = "Vikendica je uspjesno dodata!";
				poruka[1] = "success";
				return poruka;
			}
			else 
			{
				poruka[0] = "Vikendica sa tim nazivom već postoji";
				poruka[1] = "duplicate";
				return poruka;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			poruka[0] = "Doslo je do greske pri dodavanju!";
			poruka[1] = "error";
			
		}
		return poruka;
	}
	
	public String[] izmijeniVikendicu(VikendicaDTO novaVikendica, Vikendica staraVikendica) {
		String[] poruka = new String[2];
		List<Rezervacija> rezervacije = rezervacijaRepozitorijum.findByEntitetId(staraVikendica.getID());
		for(Rezervacija rez : rezervacije)
		{
			if(staraVikendica.getNaziv()!=rez.getNazivEntiteta())
				rezervacije.remove(rez);
		}
		if(rezervacije==null || rezervacije.isEmpty())
		{
			System.out.println("Izmjena vikendice servis!");
			Vikendica vikendicaProvjere = vikendicaRepozitorijum.findByNaziv(novaVikendica.getNaziv());
			if(vikendicaProvjere==null || vikendicaProvjere.getID()==staraVikendica.getID())
			{
				staraVikendica.setAdresa(novaVikendica.getAdresa());
				staraVikendica.setBrojKreveta(novaVikendica.getBrojKreveta());
				staraVikendica.setBrojSoba(novaVikendica.getBrojSoba());
				staraVikendica.setCena(novaVikendica.getCena());
				staraVikendica.setPravilaPonasanja(novaVikendica.getPravilaPonasanja());
				staraVikendica.setDodatneUsluge(novaVikendica.getDodatneUsluge());
				System.out.println("prva slika: "+novaVikendica.getLinkSlike()+"\ndruga slika: "+novaVikendica.getLinkInterijera());
				if(novaVikendica.getLinkSlike()!=null && !novaVikendica.getLinkSlike().trim().equals(""))
				{			
					System.out.println("Prva slika je: "+novaVikendica.getLinkSlike());
					staraVikendica.setLinkSlike(novaVikendica.getLinkSlike());
				}
				if(novaVikendica.getLinkInterijera()!=null && !novaVikendica.getLinkInterijera().trim().equals(""))
				{
					System.out.println("Druga slika je: "+novaVikendica.getLinkInterijera());
					staraVikendica.setLinkInterijera(novaVikendica.getLinkInterijera());
				}
				
				staraVikendica.setNaziv(novaVikendica.getNaziv());
				staraVikendica.setOpis(novaVikendica.getOpis());
				System.out.println(novaVikendica.getVlasnik());
				vikendicaRepozitorijum.save(staraVikendica);
				poruka[0] = "Izmjena vikendice je uspjesna";
				poruka[1] = "success";
			}
			else
			{
				poruka[0] = "Vikendica sa unijetim nazivom već postoji!";
				poruka[1] = "duplicate";
			
			}
		}	
		else
		{	
			poruka[0]= "Doslo je do greske, vikendica je vec rezervisana!";
			poruka[1] = "failure";
		}
		return poruka;
			
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