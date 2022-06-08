package com.Reservations.Kontroleri;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Reservations.Modeli.Korisnik;
import com.Reservations.Modeli.Vikendica;
import com.Reservations.Servis.KorisnikServis;
import com.Reservations.Servis.VikendicaServis;

@Controller
public class VikendicePonude {
	@Autowired
	VikendicaServis vikendicaServis;
		
	@Autowired
	KorisnikServis korServis;
	
	
	@RequestMapping(value = "/prikazVikendica/{id}")
	  public String getTestPage(Model model,@PathVariable Long id){
		System.out.println("PrikazVikendica page was called!");
		List<Vikendica>vikendice=vikendicaServis.listAll();
		Korisnik k=korServis.findById(id);
		model.addAttribute("vikendice", vikendice);
		model.addAttribute("kor",k);
		System.out.println(model.toString());
	      return "prikazVikendica";
	  }
	@RequestMapping(value = "/OsnovniprikazVikendica")
	  public String getOsnPage(Model model){
			System.out.println("OsnPrikazVikendica page was called!");
			List<Vikendica>vikendice=vikendicaServis.listAll();
			model.addAttribute("vikendice", vikendice);
			System.out.println(model.toString());
	      return "OsnovniPodaciVik";
	  }
}
