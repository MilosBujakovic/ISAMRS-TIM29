package com.Reservations.Kontroleri;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.Reservations.DTO.ZahtevZaBrisanjeDTO;
import com.Reservations.Exception.ResourceConflictException;
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
	@RequestMapping(value = "/profilKorisnika/{id}")
	  public String getTestPage(@PathVariable Long id,Model m){
			m.addAttribute("id",id);
		System.out.println("ProfilKorisnika page was called!");
	      return "profilKorisnika";
	  }
	
	@RequestMapping(value = "/azuriraj/{id}")
	 public String getPodaciPage(@AuthenticationPrincipal Korisnik user,Model model,@PathVariable Long id){
	  		System.out.println("AzurirajPodatke page was called!");
	  		
	  		
	  		user=userService.findById(id);
		     model.addAttribute("pod",user);
	  		System.out.println(model.toString());
	  		 return "AzurirajPodatke";
	  	  }
	@RequestMapping("/moji/{id}")
	public  ModelAndView home(@AuthenticationPrincipal Korisnik user,Model model,@PathVariable Long id) {

	    user=userService.findById(id);
	     model.addAttribute("pod",user);
	    System.out.println(user.toString());
	    return new  ModelAndView("KlijentPodaci");
	}


@RequestMapping(value = "/azurirajPodatke/{id}")
public String getAzurirajPage(@PathVariable Long id,Model model,ZahtevZaBrisanjeDTO regRequest){
	System.out.println(regRequest.toString());
	userService.update(regRequest);
 		System.out.println("AzurirajPodatke page was called!");
 		Korisnik podaci=userService.findById(id);
 		model.addAttribute("pod", podaci);
 		System.out.println(model.toString());
 		 return "AzurirajPodatke";
 	  }
 
@RequestMapping(value = "/brisiNalog/{id}")
public String registerOwner( @PathVariable Long id,Korisnik user,ZahtevZaBrisanjeDTO regRequest,BrisanjeNalogaServis k,Model model) {
	user=userService.findById(id);
    model.addAttribute("pod",user);
	System.out.println("zAHTEV ZA BRISANJE POSLAT!");
	ZahtevZaBrisanje existKorisnik = this.brisanjeService.findByKorisnickoIme(regRequest.getUsername());
	if (existKorisnik != null) {
		throw new ResourceConflictException(regRequest.getId(), "Request already exists");
	}
	this.brisanjeService.save(regRequest);
	
	return "AzurirajPodatke";
}
	
} 
