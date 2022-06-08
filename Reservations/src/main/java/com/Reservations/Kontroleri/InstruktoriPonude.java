package com.Reservations.Kontroleri;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Reservations.Modeli.Korisnik;
import com.Reservations.Modeli.Usluga;
import com.Reservations.Servis.KorisnikServis;
import com.Reservations.Servis.UslugaServis;
@Controller
public class InstruktoriPonude {
	@Autowired
	UslugaServis uslugaServis;
		
	@Autowired
	KorisnikServis korServis;
	@RequestMapping(value = "/prikazInstruktora/{id}")
	  public String getTestPage(Model model,@PathVariable Long id){
		System.out.println("PrikazInstruktora page was called!");
		List<Usluga>usluga=uslugaServis.listAll();
		Korisnik k=korServis.findById(id);
		model.addAttribute("kor", k);
		
		model.addAttribute("usluga", usluga);
		System.out.println(model.toString());
	      return "prikazInstruktora";
	  }
	
	@RequestMapping(value = "/OsnovniprikazInstruktora")
	  public String getOsnPage(Model model){
		System.out.println("OsnovniPrikazInstruktora page was called!");
		List<Usluga>usluga=uslugaServis.listAll();
		model.addAttribute("usluga", usluga);
		System.out.println(model.toString());
	      return "OsnovniPodaciInstruktora";
	  }
}
