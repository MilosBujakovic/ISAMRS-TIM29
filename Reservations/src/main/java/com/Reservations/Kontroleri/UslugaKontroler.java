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
public class UslugaKontroler {
	@Autowired
	UslugaServis uslugaServis;

	@Autowired
	KorisnikServis korisnikServis;
	
	@RequestMapping(value = "/klijent/usluga/{id}")
	public String getProfilePage(Model model, @PathVariable Long id) {
		System.out.println("Usluga page was called!");
		Usluga usluga = uslugaServis.findById(id);
		List<Usluga> lista = uslugaServis.findByInstruktor(5L);
		model.addAttribute("usluga", usluga);
		model.addAttribute("uslugeInst", lista);
		return "uslugaProfil";
	}
	
	@RequestMapping(value = "/usluga/{id}/{id2}")
	public String getUnauthServicePage(Model model, @PathVariable Long id,@PathVariable Long id2) {
		System.out.println("Usluga page was called!");
		Usluga usluga = uslugaServis.findById(id);
		Korisnik k=korisnikServis.findById(id2);
		List<Usluga> lista = uslugaServis.findByInstruktor(5L);
		model.addAttribute("usluga", usluga);
		model.addAttribute("uslugeInst", lista);
		model.addAttribute("kor",k);
		return "uslugaProfil";
	}
}
