package com.Reservations.Servis;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.Reservations.DTO.UslugaDTO;
import com.Reservations.Modeli.Brod;
import com.Reservations.Modeli.Korisnik;
import com.Reservations.Modeli.Usluga;
import com.Reservations.Modeli.enums.TipoviUsluga;
import com.Reservations.Repozitorijumi.UslugaRepozitorijum;

@Service
public class UslugaServis {

	@Autowired
	private UslugaRepozitorijum uslugaRepozitorijum;

	public List<Usluga> listAll() {
		return uslugaRepozitorijum.findAll();
	}

	public Usluga findById(Long id) {
		try {
			return uslugaRepozitorijum.findById(id).get();
		} catch (NoSuchElementException e) {
			return null;
		}
	}

	public List<Usluga> findByInstruktor(long id) {
		List<Usluga> lista = uslugaRepozitorijum.findAll();
		List<Usluga> rez = new ArrayList<Usluga>();
		for (Usluga u : lista) {
			if (u.getInstruktor().getID() == id)
				rez.add(u);
		}
		return rez;
	}

	public Usluga save(UslugaDTO dto, Korisnik instruktor) {
		Usluga u = new Usluga();
		List<Usluga> instUsluge = this.findByInstruktor(instruktor.getID());
		String bio = instUsluge.get(0).getBiografijaInstruktora();

		u.setNaziv(dto.getNaziv());
		u.setAdresa(dto.getAdresa());
		u.setBiografijaInstruktora(bio);
		u.setOpis(dto.getOpis());
		u.setMaxOsoba(dto.getMaxOsoba());
		u.setCena(dto.getCena());
		u.setPecaroskaOprema(dto.getPecaroskaOprema());
		u.setInstruktor(instruktor);
		u.setTip(TipoviUsluga.valueOf(dto.getTip()));
		u.setLinkSlike(dto.getLinkSlike());

		return this.uslugaRepozitorijum.save(u);
	}

	public Usluga update(UslugaDTO dto, long id) {
		Usluga u = this.findById(id);
		if (u != null) {
			if (!dto.getNaziv().equals("")) {
				u.setNaziv(dto.getNaziv());
			}
			if (!dto.getAdresa().equals("")) {
				u.setAdresa(dto.getAdresa());
			}
			if (!dto.getOpis().equals("")) {
				u.setOpis(dto.getOpis());
			}
			if (!String.valueOf(dto.getMaxOsoba()).equals("")) {
				u.setMaxOsoba(dto.getMaxOsoba());
			}
			if (!String.valueOf(dto.getCena()).equals("")) {
				u.setCena(dto.getCena());
			}
			if (!dto.getPecaroskaOprema().equals("")) {
				u.setPecaroskaOprema(dto.getPecaroskaOprema());
			}
			if (!dto.getTip().equals("")) {
				u.setTip(TipoviUsluga.valueOf(dto.getTip()));
			}
			if (!dto.getLinkSlike().equals("")) {
				u.setLinkSlike(dto.getLinkSlike());
			}

			System.out.println(u.toString());
		}
		return this.uslugaRepozitorijum.save(u);
	}

	public void deleteById(long id) {
		this.uslugaRepozitorijum.deleteById(id);
	}
	
	public List<Usluga>UslugaSortCena(){
		//List<Brod>li2=new ArrayList<Brod>();
		List<Usluga>li=uslugaRepozitorijum.findAll(Sort.by(Sort.Direction.DESC, "cena"));
		System.out.println(li.toString());
		return li;
		}
	
	public List<Usluga>UslugaSortNaziv(){
		//List<Brod>li2=new ArrayList<Brod>();
		List<Usluga>li=uslugaRepozitorijum.findAll(Sort.by(Sort.Direction.ASC, "naziv"));
		System.out.println(li.toString());
		return li;
		}
	
	public List<Usluga>UslugaSortAdresa(){
		//List<Brod>li2=new ArrayList<Brod>();
		List<Usluga>li=uslugaRepozitorijum.findAll(Sort.by(Sort.Direction.ASC, "adresa"));
		System.out.println(li.toString());
		return li;
		}
	
	public List<Usluga>UslugaPretraga(String pretraga){
		//List<Brod>li2=new ArrayList<Brod>();
		List<Usluga>li=new ArrayList<Usluga>();
		List<Usluga>li2=uslugaRepozitorijum.findAll();
		for (Usluga e : li2) {
			if(e.getAdresa().toLowerCase().contains(pretraga.toLowerCase())) {
				li.add(e);
				continue;
			}
			if(e.getNaziv().toLowerCase().contains(pretraga.toLowerCase())) {
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
}