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
public class InstruktorKontroler {
	@Autowired
	KorisnikServis korisnikServis;
	
	@Autowired
	UslugaServis uslugaServis;
	
	@RequestMapping(value = "/instruktor/{id}")
	public String getHomePage(Model model, @PathVariable Long id) {
		System.out.println("Instruktor page was called!");
		List<Usluga> lista = uslugaServis.findByInstruktor(id);
		model.addAttribute("usluge", lista);
		return "instruktorProfil";
	}
	
	@RequestMapping(value = "/instruktor/{id}/profil")
	public String getDataPage(Model model, @PathVariable Long id) {
		System.out.println("Instruktor page was called!");
		Korisnik u = korisnikServis.findById(id);
		model.addAttribute("podaci", u);
		return "instruktorProfil";
	}
	
	@RequestMapping(value = "/instruktor/{id}/istorija")
	public String getHistoryPage(Model model, @PathVariable Long id) {
		System.out.println("Instruktor page was called!");
		List<Usluga> lista = uslugaServis.findByInstruktor(id);
		model.addAttribute("usluge", lista);
		return "instruktorProfil";
	}
	
	@RequestMapping(value = "/instruktor/{id}/izvestaji")
	public String getReportsPage(Model model, @PathVariable Long id) {
		System.out.println("Instruktor page was called!");
		List<Usluga> lista = uslugaServis.findByInstruktor(id);
		model.addAttribute("usluge", lista);
		return "instruktorProfil";
	}
}
