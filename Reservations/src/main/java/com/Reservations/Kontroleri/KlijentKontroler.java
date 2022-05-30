package com.Reservations.Kontroleri;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Reservations.DTO.ZahtevZaBrisanjeDTO;
import com.Reservations.Modeli.Korisnik;
import com.Reservations.Modeli.ZahtevZaBrisanje;
import com.Reservations.Servis.BrisanjeNalogaServis;
import com.Reservations.Servis.KorisnikServis;

@Controller
public class KlijentKontroler {

	@Autowired
	KorisnikServis userService;
		
	@Autowired
	BrisanjeNalogaServis brisanjeService;
	@RequestMapping(value = "/profilKorisnika")
	  public String getTestPage(){
		System.out.println("ProfilKorisnika page was called!");
	      return "profilKorisnika";
	  }
	
	@RequestMapping(value = "/azuriraj")
	 public String getPodaciPage(Model model){
	  		System.out.println("AzurirajPodatke page was called!");
	  		Korisnik podaci=userService.findById(2L);
	  		model.addAttribute("podaci", podaci);
	  		System.out.println(model.toString());
	  		 return "AzurirajPodatke";
	  	  }
	  

@RequestMapping(value = "/azurirajPodatke")
public String getAzurirajPage(Model model){
 		System.out.println("AzurirajPodatke page was called!");
 		Korisnik podaci=userService.findById(2L);
 		model.addAttribute("podaci", podaci);
 		System.out.println(model.toString());
 		 return "AzurirajPodatke";
 	  }
 
 @RequestMapping(value = "/brisiNalog")
 public String getBrisanjePage(Model model, ZahtevZaBrisanjeDTO dto){
  		System.out.println("AzurirajPodatke page was called!");
  		Korisnik podaci=userService.findById(2L);
  		model.addAttribute("podaci", podaci);
  		
  		ZahtevZaBrisanje z=brisanjeService.Save(dto);
  		System.out.println(dto.toString());
  		System.out.println(z.toString());
  		System.out.println(model.toString());
  		 return "AzurirajPodatke";
  	  }
  }
	
  
