package com.Reservations.Kontroleri;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Reservations.DTO.RezervacijaDTO;
import com.Reservations.DTO.ZahtevZaBrisanjeDTO;
import com.Reservations.Exception.ResourceConflictException;
import com.Reservations.Modeli.Korisnik;
import com.Reservations.Modeli.Rezervacija;
import com.Reservations.Modeli.ZahtevZaBrisanje;
import com.Reservations.Servis.BrisanjeNalogaServis;
import com.Reservations.Servis.KorisnikServis;
import com.Reservations.Servis.RezervacijaServis;

public class RezervacijaKontroler {
	
	
	@Autowired
	KorisnikServis userService;
	
	@Autowired
	RezervacijaServis rez;
	
	@RequestMapping(value = "/rezervisiVik")
	public String registerOwner( @PathVariable Long id,Korisnik user,RezervacijaDTO regRequest,Model model) {
		user=userService.findById(id);
	    model.addAttribute("pod",user);
	    model.addAttribute("id",regRequest.getId() );
		System.out.println("Rezervacija poslata POSLAT!");
		Rezervacija existKorisnik = this.rez.findByIme(regRequest.getNazivEntiteta());
		if (existKorisnik != null) {
			throw new ResourceConflictException(regRequest.getId(), "Request already exists");
		}
		this.rez.save(regRequest);
		
		return "ProfilKorisnika";
	}
	

}
