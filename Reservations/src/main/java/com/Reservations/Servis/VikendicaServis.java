package com.Reservations.Servis;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Reservations.DTO.PoslovanjeEntitetaDTO;
import com.Reservations.DTO.VikendicaDTO;
import com.Reservations.Modeli.Korisnik;
import com.Reservations.Modeli.Rezervacija;
import com.Reservations.Modeli.Vikendica;
import com.Reservations.Modeli.enums.TipEntiteta;
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
	
	@Autowired
	GVarijableServis globalneVarijable;
	
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
		List<Vikendica> vikendiceVlasnika = new ArrayList<Vikendica>();
		System.out.println("broj vikendica: "+lista.size());
		if(lista!=null)for(Vikendica u : lista) 
		{
			System.out.println("Compare: "+u.getVlasnik().getID()+" - "+id);
			if (u.getVlasnik().getID() == id) vikendiceVlasnika.add(u);
		}
		return vikendiceVlasnika;
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
			/*
				List<Vikendica> vikendice = vikendicaRepozitorijum.findAll();
				Long ID = 0L;
				for(Vikendica vik : vikendice)
				{	
					if(vik.getID()==ID)
					{
						ID++;
					}
				}
				//vikendica.setID(ID);
				 
				 */
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
		List<Rezervacija> rezervacijeVikendice = new ArrayList<Rezervacija>();
		for(Rezervacija rez : rezervacije)
		{
			System.out.println("ID rezervacije: "+rez.getID());
			System.out.println("Trazi se brod: "+staraVikendica.getID());
			System.out.println("ID entiteta: "+rez.getEntitetId()+" Tip: "+rez.getTipEntiteta() );
			if(staraVikendica.getID()==rez.getEntitetId() && rez.getTipEntiteta().equals(TipEntiteta.vikendica))
			{
				rezervacijeVikendice.add(rez);
			}
				
		}
		if(rezervacijeVikendice==null || rezervacijeVikendice.isEmpty())
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
	
	public String[] obrisiVikendicu(Long vlasnikID, Long vikendicaID) 
	{
		String[] poruka = new String[2];
		Vikendica staraVikendica = this.findById(vikendicaID);
		List<Rezervacija> rezervacije = rezervacijaRepozitorijum.findByEntitetId(staraVikendica.getID());
		List<Rezervacija> rezervacijeVikendice = new ArrayList<Rezervacija>();
		
		for(Rezervacija rez : rezervacije)
		{
			System.out.println("ID rezervacije: "+rez.getID());
			System.out.println("Trazi se vikendica: "+staraVikendica.getID());
			System.out.println("ID entiteta: "+rez.getEntitetId()+" Tip: "+rez.getTipEntiteta() );
			if(staraVikendica.getID()==rez.getEntitetId() && rez.getTipEntiteta().equals(TipEntiteta.vikendica))
			{
				rezervacijeVikendice.add(rez);
			}
				
		}
		if(rezervacijeVikendice==null || rezervacijeVikendice.isEmpty()){	
			vikendicaRepozitorijum.delete(staraVikendica);
			poruka[0] = "Brisanje vikendice je uspjesno!";
			poruka[1] = "success";
		}
		else 
		{
			poruka[0] = "Došlo je do greške, vikendica je vec rezervisana!";
			poruka[1] = "reserved";
		}
		return poruka;
	}
	
	public List<Vikendica> nadjiVikendicePoVlasniku(Korisnik vlasnik) 
	{
		List<Vikendica> vikendice = vikendicaRepozitorijum.findAll();
		List<Vikendica> mojeVikendice = new ArrayList<Vikendica>();
		for(Vikendica vikendica : vikendice)
		{
			
			System.out.println("Naziv: "+ vikendica.getNaziv());
			System.out.println("Vlasnik: "+vikendica.getVlasnik().getKorisnickoIme());
			System.out.println(" Trazim: "+vlasnik.getKorisnickoIme());
			if(vikendica.getVlasnik().equals(vlasnik))
			{
				mojeVikendice.add(vikendica);
				System.out.println("Dodata!");
			}
			else System.out.println("Odbacena!");
		}
		return mojeVikendice;
	}
	public List<PoslovanjeEntitetaDTO> poslovanjeVikendicaPeriod(PoslovanjeEntitetaDTO poslovanje, Korisnik vlasnik) {
		List<Rezervacija> mojeRezervacije = rezervacijaServis.pronadjiRezervacijePoVlasniku(vlasnik, TipEntiteta.vikendica);
		List<Vikendica> mojeVikendice = this.nadjiVikendicePoVlasniku(vlasnik);
		System.out.println("Rez datum: "+mojeRezervacije.size());
		System.out.println("Pocetni dat: "+poslovanje.getPocetniDatum());
		List<PoslovanjeEntitetaDTO> poslovanjaVikendica = new ArrayList<PoslovanjeEntitetaDTO>();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");  
		LocalDate pocetni = LocalDate.parse(poslovanje.getPocetniDatum(), dtf);
		LocalDate krajnji = LocalDate.parse(poslovanje.getKrajnjiDatum(), dtf);
		double vlasnikovProcenat = 1-Double.parseDouble(globalneVarijable.findByName("procenat").getVrednost());
		
		for(Vikendica vikendica : mojeVikendice)
		{
			PoslovanjeEntitetaDTO poslovanjeVikendice = new PoslovanjeEntitetaDTO(poslovanje);
			poslovanjeVikendice.setVlasnikID(vlasnik.getID());
			poslovanjeVikendice.setEntitetID(vikendica.getID());
			poslovanjeVikendice.setNazivEntiteta(vikendica.getNaziv());
			poslovanje.setOcjenaEntiteta(0.0);
			double prihod = 0;
			Long brojRezervacija = 0L;
			double zarada = 0;
			for(Rezervacija rezervacija: mojeRezervacije) 
			{
				if(rezervacija.getEntitetId()==vikendica.getID())
				{
					LocalDate datum = LocalDate.parse(rezervacija.getDatum(), dtf);
					if(datum.isAfter(pocetni) && datum.isBefore(krajnji))
					{
						brojRezervacija++;
						zarada += rezervacija.getCena();
					}
				}
				//if(LocalDate.parse(r.getDatum(), dtf).isAfter())
			}
			poslovanjeVikendice.setZarada(zarada);
			poslovanjeVikendice.setBrojRezervacija(brojRezervacija);
			prihod = zarada*vlasnikovProcenat;
			poslovanjeVikendice.setPrihod(prihod);
			poslovanjaVikendica.add(poslovanjeVikendice);
		}
		return poslovanjaVikendica;
	}
		
	public PoslovanjeEntitetaDTO srediDatume(PoslovanjeEntitetaDTO poslovanje) {
		String[] pocetni = poslovanje.getPocetniDatum().split("-");
		System.out.println("Duzina: "+pocetni.length);
		return poslovanje;
	}

}