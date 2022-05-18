package com.Reservations.Servis;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Reservations.DTO.RegistracijaKorisnikaDTO;
import com.Reservations.Modeli.Korisnik;
import com.Reservations.Modeli.Uloga;
import com.Reservations.Repozitorijumi.KorisnikRepozitorijum;


@Service
public class KorisnikServis 
{

	
	@Autowired
	private KorisnikRepozitorijum korisnikRepozitorijum;
	
	@Autowired
	private UlogaServis ulogaServis;


	
	public Korisnik findByUsername(String username) {
		return korisnikRepozitorijum.findByKorisnickoIme(username);
	}
	
	

	public Korisnik findById(Long id) {
		try {
			return korisnikRepozitorijum.findById(id).get();
		} catch (NoSuchElementException e) {
			return null;
		}
	}


	public Korisnik save(RegistracijaKorisnikaDTO userRequest) {
		Korisnik u = new Korisnik();
		u.setKorisnickoIme(userRequest.getUsername());
		
		u.setLozinka(userRequest.getPassword());

		u.setID(userRequest.getId());
		u.setIme(userRequest.getFirstName());
		u.setPrezime(userRequest.getLastName());
		u.setAdresa(userRequest.getAddress());
		u.setGrad(userRequest.getCity());
		u.setDrzava(userRequest.getCountry());
		u.setBrojTel(userRequest.getPhone());
		//u.setEnabled(true);
		u.setEmail(userRequest.getEmail());

		// u primeru se registruju samo obicni korisnici i u skladu sa tim im se i dodeljuje samo rola USER
		Uloga role = ulogaServis.findByName("ROLE_CLIENT");
		u.setUloga(role);
		
		return this.korisnikRepozitorijum.save(u);// TODO Auto-generated method stub
	}





}