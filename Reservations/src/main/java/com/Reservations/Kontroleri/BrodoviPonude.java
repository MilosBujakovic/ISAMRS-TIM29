package com.Reservations.Kontroleri;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Reservations.Modeli.Brod;
import com.Reservations.Modeli.Korisnik;
import com.Reservations.Servis.BrodServis;
import com.Reservations.Servis.KorisnikServis;
@Controller
public class BrodoviPonude {
	@Autowired
	BrodServis brodServis;
		
	@Autowired
	KorisnikServis korServis;
	@RequestMapping(value = "/prikazBrodova/{id}")
	  public String getTestPage(Model model,@PathVariable Long id){
		System.out.println("PrikazBrodova page was called!");
		List<Brod>brodovi=brodServis.listAll();
		Korisnik k=korServis.findById(id);
		model.addAttribute("kor", k);
		model.addAttribute("brodovi", brodovi);
		System.out.println(model.toString());
	      return "prikazBrodova";
	  }

	@RequestMapping(value = "/OsnovniprikazBrodova")
	  public String getOsnPage(Model model){
		System.out.println("PrikazBrodova page was called!");
		List<Brod>brodovi=brodServis.listAll();
		model.addAttribute("brodovi", brodovi);
		
	      return "OsnovniPodaciBrod";
	  }
	
	
}
