package com.Reservations.Kontroleri;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Reservations.DTO.RezervacijaDTO;
import com.Reservations.DTO.ZahtevZaBrisanjeDTO;
import com.Reservations.Exception.ResourceConflictException;
import com.Reservations.Modeli.Korisnik;
import com.Reservations.Modeli.Rezervacija;
import com.Reservations.Modeli.ZahtevZaBrisanje;
import com.Reservations.Modeli.enums.TipEntiteta;
import com.Reservations.Modeli.enums.TipRezervacije;
import com.Reservations.Servis.BrisanjeNalogaServis;
import com.Reservations.Servis.KorisnikServis;
import com.Reservations.Servis.RezervacijaServis;

@Controller
public class RezervacijaKontroler {
	
	
	@Autowired
	KorisnikServis userService;
	
	@Autowired
	RezervacijaServis rez;
	
	@RequestMapping(value = "/rezervisiVik/{id}/{id2}")
	public String registerOwner( @PathVariable Long id, @PathVariable Long id2, RezervacijaDTO regRequest,Model model) {
	Korisnik k=userService.findById(id2);
		Rezervacija user=rez.findById(id);
	    model.addAttribute("pod",user);
	    System.out.println(regRequest.toString());
	//    model.addAttribute("id",regRequest.getId() );
		System.out.println("Rezervacija poslata POSLAT!");
		
		this.rez.save(regRequest,TipEntiteta.vikendica,id,TipRezervacije.obicna,id2);
		
		return "profilKorisnika";
	}
	
}
