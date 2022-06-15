package com.Reservations.Servis;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Reservations.DTO.RezervacijaDTO;
import com.Reservations.Modeli.Brod;
import com.Reservations.Modeli.Rezervacija;
import com.Reservations.Modeli.Usluga;
import com.Reservations.Modeli.Vikendica;
import com.Reservations.Modeli.enums.TipEntiteta;
import com.Reservations.Modeli.enums.TipRezervacije;
import com.Reservations.Repozitorijumi.RezervacijaRepozitorijum;
@Service
public class RezervacijaServis {

	@Autowired
	private RezervacijaRepozitorijum rezRepozitorijum;
	@Autowired
	private VikendicaServis vik;
	@Autowired
	private UslugaServis usluga;
	@Autowired
	private BrodServis brd;
	
	@Autowired
	private KorisnikServis usr;
	
	@Autowired
	private KorisnikServis kor;
	public Rezervacija findById(Long id) {
		try {
			return rezRepozitorijum.findById(id).get();
		} catch (NoSuchElementException e) {
			return null;
		}
	}

	public Rezervacija save(RezervacijaDTO regRequest,TipEntiteta e,Long id,TipRezervacije tip,Long id2) {
		
		
		Rezervacija r = new Rezervacija();
		
		r.setTip(tip);
		r.setEntitetId(id);
		r.setTipEntiteta(e);
		r.setKlijent(kor.findById(id2));
		if(e.equals(TipEntiteta.vikendica)) {
			Vikendica v=vik.findById(id);
			r.setNazivEntiteta(v.getNaziv());
			r.setCena(v.getCena());
		}
		if(e.equals(TipEntiteta.brod)) {
			Brod v=brd.findById(id);
			r.setNazivEntiteta(v.getNaziv());
			r.setCena(v.getCena());
		}
		if(e.equals(TipEntiteta.usluga)) {
			Usluga v=usluga.findById(id);
			r.setNazivEntiteta(v.getNaziv());
			r.setCena(v.getCena());
		}
		r.setDatum(regRequest.getDatum());
		r.setVreme(regRequest.getVreme());
		r.setTrajanje(regRequest.getTrajanje());
		r.setMaxOsoba(regRequest.getMaxOsoba());
		
		
		
		
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
	
	public List<Rezervacija>findByKlijent(long id){
		List<Rezervacija>li2=new ArrayList<Rezervacija>();
		List<Rezervacija>li=rezRepozitorijum.findAll();
		  DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");  
		 LocalDate now = LocalDate.now();  
		for(Rezervacija r: li) {
			if(r.getKlijent().getID()==id) {
				if(LocalDate.parse(r.getDatum(), dtf).isAfter(now))
				li2.add(r);
			
			}
		}
		return li2;
	
	}
	public List<Rezervacija>findByKlijentDate(long id){
		List<Rezervacija>li2=new ArrayList<Rezervacija>();
		List<Rezervacija>li=rezRepozitorijum.findAll();
		  DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");  
		 LocalDate now = LocalDate.now();  
		for(Rezervacija r: li) {
			if(r.getKlijent().getID()==id) {
				if(LocalDate.parse(r.getDatum(), dtf).isBefore(now))
				li2.add(r);
			
			}
		}
		return li2;
	}
}
