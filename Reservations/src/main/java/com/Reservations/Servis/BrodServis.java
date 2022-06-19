package com.Reservations.Servis;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.Reservations.DTO.BrodDTO;
import com.Reservations.Modeli.Brod;
import com.Reservations.Modeli.Korisnik;
import com.Reservations.Modeli.Rezervacija;
import com.Reservations.Modeli.enums.TipEntiteta;
import com.Reservations.Repozitorijumi.BrodRepozitorijum;
import com.Reservations.Repozitorijumi.RezervacijaRepozitorijum;

@Service
public class BrodServis 
{
	@Autowired
	private BrodRepozitorijum brodRepozitorijum;
	
	@Autowired
	private KorisnikServis korisnikServis;

	@Autowired
	private RezervacijaRepozitorijum rezervacijaRepozitorijum;
	
	public List<Brod> listAll(){
		return brodRepozitorijum.findAll();
	}

	public Brod findById(Long id){
		try {
			return brodRepozitorijum.findById(id).get();
		} catch (NoSuchElementException e) {
			return null;
		}
	}
	
	public List<Brod> findByVlasnik(long id) {
		List<Brod> lista = brodRepozitorijum.findAll();
		for(Brod u : lista) {
			if (u.getVlasnik().getID() != id) lista.remove(u);
		}
		return lista;
	}

	public List<Brod> nadjiBrodovePoVlasniku(Korisnik vlasnik) {
		List<Brod> brodovi = this.listAll();
		List<Brod> mojiBrodovi = new ArrayList<Brod>();
		for(Brod brod : brodovi)
		{
			
			System.out.println("Naziv: "+ brod.getNaziv());
			System.out.println("Vlasnik: "+brod.getVlasnik().getKorisnickoIme());
			System.out.println(" Trazim: "+vlasnik.getKorisnickoIme());
			if(brod.getVlasnik().equals(vlasnik))
			{
				mojiBrodovi.add(brod);
				System.out.println("Dodata!");
			}
			else System.out.println("Odbacena!");
		}
		return mojiBrodovi;
	}

	public Brod pronadjiPoNazivu(String naziv) {
		Brod brod = brodRepozitorijum.findByNaziv(naziv);
		return brod;
	}

	public String[] dodajBrod(BrodDTO noviBrod) {
		String[] poruka = new String[2];
		Korisnik vlasnik = korisnikServis.findById(noviBrod.getVlasnik());
		try
		{
			System.out.println("Dodaj brod servis!");
			Brod brod = brodRepozitorijum.findByNaziv(noviBrod.getNaziv());
					//findByNaziv(novaVikendica.getNaziv());
			System.out.println("find by naziv: "+ brod);
			if(brod==null)
			{
				brod = new Brod();
				brod.setAdresa(noviBrod.getAdresa());
				brod.setBrojMotora(noviBrod.getBrojMotora().toString());
				brod.setCena(noviBrod.getCena());
				brod.setDuzina(noviBrod.getDuzina());
				brod.setKapacitet(noviBrod.getKapacitet()+" osoba");
				brod.setLinkKabine(noviBrod.getLinkKabine());
				brod.setLinkSlike(noviBrod.getLinkSlike());
				brod.setMaxBrzina(noviBrod.getMaxBrzina());
				brod.setNavigacionaOprema(noviBrod.getNavigacionaOprema());
				brod.setNaziv(noviBrod.getNaziv());
				brod.setOpis(noviBrod.getOpis());
				brod.setPecaroskaOprema(noviBrod.getPecaroskaOprema());
				brod.setPravilaPonasanja(noviBrod.getPravilaPonasanja());
				brod.setSnaga(noviBrod.getSnaga());
				brod.setTip(noviBrod.getTip());
				brod.setVlasnik(vlasnik);
				
				
				List<Brod> brodovi = this.listAll();
				Long ID = 0L;
				for(Brod brodd : brodovi)
				{	
					BrodDTO bbb = new BrodDTO(brodd);
					System.out.println("ParseLong za kapacitet: "+ bbb.getKapacitet());
					if(brodd.getID()==ID)
					{
						ID++;
					}
				}
				//brod.setID(ID);
				brodRepozitorijum.save(brod);
				poruka[0] = "Brod je uspjesno dodat!";
				poruka[1] = "success";
				return poruka;
			}
			else 
			{
				poruka[0] = "Brod sa tim nazivom već postoji";
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

	public String[] izmijeniBrod(BrodDTO noviBrod, Brod stariBrod) {
		String[] poruka = new String[2];
		List<Rezervacija> rezervacije = rezervacijaRepozitorijum.findByEntitetId(stariBrod.getID());
		for(Rezervacija rez : rezervacije)
		{
			System.out.println("ID rezervacije: "+rez.getID());
			System.out.println("Trazi se brod: "+stariBrod.getID());
			System.out.println("ID entiteta: "+rez.getEntitetId()+" Tip: "+rez.getTipEntiteta() );
			if(stariBrod.getID()!=rez.getEntitetId())
			{
				rezervacije.remove(rez);
				System.out.println("Izbacena po ID!");
				continue;
			}
			if(rez.getTipEntiteta()!=TipEntiteta.brod)
			{
				rezervacije.remove(rez);
				System.out.println("Izbacena po tipu!");
				continue;
			}
				
		}
		if(rezervacije==null || rezervacije.isEmpty())
		{
			System.out.println("Izmjena vikendice servis!");
			Brod brodProvjere = brodRepozitorijum.findByNaziv(noviBrod.getNaziv());
			if(brodProvjere==null || brodProvjere.getID()==stariBrod.getID())
			{
				stariBrod.setAdresa(noviBrod.getAdresa());
				stariBrod.setBrojMotora(noviBrod.getBrojMotora());
				stariBrod.setCena(noviBrod.getCena());
				stariBrod.setDuzina(noviBrod.getDuzina());
				stariBrod.setKapacitet(noviBrod.getKapacitet());
				stariBrod.setMaxBrzina(noviBrod.getMaxBrzina());
				stariBrod.setNavigacionaOprema(noviBrod.getNavigacionaOprema());
				stariBrod.setOpis(noviBrod.getOpis());
				stariBrod.setPecaroskaOprema(noviBrod.getPecaroskaOprema());
				stariBrod.setPravilaPonasanja(noviBrod.getPravilaPonasanja());
				stariBrod.setSnaga(noviBrod.getSnaga());
				stariBrod.setTip(noviBrod.getTip());
				System.out.println("prva slika: "+noviBrod.getLinkSlike()+"\ndruga slika: "+noviBrod.getLinkKabine());
				if(noviBrod.getLinkSlike()!=null && !noviBrod.getLinkSlike().trim().equals(""))
				{			
					System.out.println("Prva slika je: "+noviBrod.getLinkSlike());
					stariBrod.setLinkSlike(noviBrod.getLinkSlike());
				}
				if(noviBrod.getLinkKabine()!=null && !noviBrod.getLinkKabine().trim().equals(""))
				{
					System.out.println("Druga slika je: "+noviBrod.getLinkKabine());
					stariBrod.setLinkKabine(noviBrod.getLinkKabine());
				}
				
				stariBrod.setNaziv(noviBrod.getNaziv());
				stariBrod.setOpis(noviBrod.getOpis());
				System.out.println(noviBrod.getVlasnik());
				
				brodRepozitorijum.save(stariBrod);
				poruka[0] = "Izmjena broda je uspjesna";
				poruka[1] = "success";
			}
			else
			{
				poruka[0] = "Brod sa unijetim nazivom već postoji!";
				poruka[1] = "duplicate";
			
			}
		}	
		else
		{	
			poruka[0]= "Doslo je do greske, brod je vec rezervisan!";
			poruka[1] = "failure";
		}
		return poruka;
			
	}

	
	public List<Brod>BrodSortCena(){
		//List<Brod>li2=new ArrayList<Brod>();
		List<Brod>li=brodRepozitorijum.findAll(Sort.by(Sort.Direction.DESC, "cena"));
		System.out.println(li.toString());
		return li;
		}
	
	public List<Brod>BrodSortNaziv(){
		//List<Brod>li2=new ArrayList<Brod>();
		List<Brod>li=brodRepozitorijum.findAll(Sort.by(Sort.Direction.ASC, "tip"));
		System.out.println(li.toString());
		return li;
		}
	
	public List<Brod>BrodSortAdresa(){
		//List<Brod>li2=new ArrayList<Brod>();
		List<Brod>li=brodRepozitorijum.findAll(Sort.by(Sort.Direction.ASC, "adresa"));
		System.out.println(li.toString());
		return li;
		}
	
	public List<Brod>BrodPretraga(String pretraga){
		//List<Brod>li2=new ArrayList<Brod>();
		List<Brod>li=new ArrayList<Brod>();
		List<Brod>li2=brodRepozitorijum.findAll();
		for (Brod e : li2) {
			if(e.getAdresa().toLowerCase().contains(pretraga.toLowerCase())) {
				li.add(e);
				continue;
			}
			if(e.getTip().toLowerCase().contains(pretraga.toLowerCase())) {
				li.add(e);
				continue;
			}
			if(String.valueOf(e.getCena()).contains(pretraga)) {
				li.add(e);
				continue;
			}
		}
		return li;
		}
	
	public List<Brod>BrodFilter()
	{
		//List<Brod>li2=new ArrayList<Brod>();
		
		List<Brod>li2=brodRepozitorijum.findAll();
		
		 return li2.stream()
	            .filter(o -> !o.getTip().equals("camac")) != null
	        ? li2 : null;
		}

}
