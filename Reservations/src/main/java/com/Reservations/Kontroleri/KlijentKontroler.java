package com.Reservations.Kontroleri;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Reservations.Servis.KorisnikServis;

@Controller
public class KlijentKontroler {

	@Autowired
	KorisnikServis userService;
		
	
	@RequestMapping(value = "/profilKorisnika")
	  public String getTestPage(){
		System.out.println("ProfilKorisnika page was called!");
	      return "profilKorisnika";
	  }
}
