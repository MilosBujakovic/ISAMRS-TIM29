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
public class BrodKontroler {
	@Autowired
	BrodServis uslugaServis;

	@Autowired
	KorisnikServis korisnikServis;
	
	@RequestMapping(value = "/brod/{id}")
	public String getProfilePage(Model model, @PathVariable Long id) {
		System.out.println("BrodKlijentProfil page was called!");
		Brod usluga = uslugaServis.findById(id);
		List<Brod> lista = uslugaServis.findByVlasnik(4L);
		model.addAttribute("brod", usluga);
		model.addAttribute("brodVlas", lista);
		return "brodOsnovniProfil";
	}
	
	@RequestMapping(value = "/brod/{id}/{id2}")
	public String getUnauthServicePage(Model model, @PathVariable Long id,@PathVariable Long id2) {
		System.out.println("BrodProfil page was called!");
		Brod usluga = uslugaServis.findById(id);
		Korisnik k=korisnikServis.findById(id2);
		List<Brod> lista = uslugaServis.findByVlasnik(4L);
		model.addAttribute("brod", usluga);
		model.addAttribute("brodVlas", lista);
		model.addAttribute("kor",k);
		System.out.println(model.toString());
		return "ProfilBroda";
	}
}
