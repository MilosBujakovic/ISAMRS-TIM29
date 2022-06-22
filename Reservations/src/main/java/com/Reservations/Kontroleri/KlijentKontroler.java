package com.Reservations.Kontroleri;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.Reservations.DTO.ZahtevZaBrisanjeDTO;
import com.Reservations.DTO.ZalbaDTO;
import com.Reservations.Modeli.Korisnik;
import com.Reservations.Modeli.Rezervacija;
import com.Reservations.Modeli.ZahtevZaBrisanje;
import com.Reservations.Modeli.Zalba;
import com.Reservations.Servis.BrisanjeNalogaServis;
import com.Reservations.Servis.KorisnikServis;
import com.Reservations.Servis.RezervacijaServis;
import com.Reservations.Servis.ZalbaServis;

@Controller
public class KlijentKontroler {

	@Autowired
	KorisnikServis userService;
	
	@Autowired
	RezervacijaServis resService;
		
	@Autowired
	BrisanjeNalogaServis brisanjeService;
	
	@Autowired
	ZalbaServis zalbaService;
	
	@RequestMapping(value = "/profilKorisnika/{id}")
	  public String getTestPage(@PathVariable Long id,Model m){
			m.addAttribute("id",id);
		System.out.println("ProfilKorisnika page was called!");
	      return "profilKorisnika";
	  }
	
	@RequestMapping(value = "/Profil")
	  public String getTesPage(){
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
		return "ZahtevVecPoslat";
	
		//throw new ResourceConflictException(regRequest.getId(), "Request already exists");
	}
	this.brisanjeService.save(regRequest);
	
	return "AzurirajPodatke";
}

@RequestMapping("/zalba/{id}/{id2}")
public  String home2(@AuthenticationPrincipal Korisnik user,Model model,@PathVariable Long id,@PathVariable Long id2) {
    Rezervacija r=resService.findById(id2);
   
   model.addAttribute("rez",r);
    user=userService.findById(id);
     model.addAttribute("pod",user);
   
    System.out.println(user.toString());
    return "StranicaZaZalbu";
}

@RequestMapping("/pretplata/{id}")
public  String hom(@AuthenticationPrincipal Korisnik user,Model model,@PathVariable Long id) {
    Rezervacija r=resService.findById(id);
   
   model.addAttribute("rez",r);
    user=userService.findById(id);
     model.addAttribute("pod",user);
   
    System.out.println(user.toString());
    return "MojePretplate";
}

@RequestMapping(value = "/zalba2/{id}")
public String registerOwner( @PathVariable Long id,Korisnik user,ZalbaDTO regRequest,ZalbaServis k,Model model) {
    user=userService.findById(id);
	System.out.println("zAHTEV ZA BRISANJE POSLAT!");
	model.addAttribute("pod",user);
	Zalba existKorisnik = this.zalbaService.findById(regRequest.getRez_id());
	if (existKorisnik != null) {
		return "ZahtevVecPoslat";
	
		//throw new ResourceConflictException(regRequest.getId(), "Request already exists");
	}
	this.zalbaService.save(regRequest);
	
	return "ProfilKorisnika";
}

} 
