package com.Reservations.Kontroleri;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Reservations.Modeli.Brod;
import com.Reservations.Modeli.Vikendica;
import com.Reservations.Servis.KorisnikServis;
import com.Reservations.Servis.VikendicaServis;

@Controller
public class VikendicaKontroler {
	@Autowired
	VikendicaServis uslugaServis;

	@Autowired
	KorisnikServis korisnikServis;
	
	@RequestMapping(value = "/klijent/vikendice/{id}")
	public String getProfilePage(Model model, @PathVariable Long id) {
		System.out.println("BrodKlijentProfil page was called!");
		Vikendica usluga = uslugaServis.findById(id);
		List<Vikendica> lista = uslugaServis.findByVlasnik(3L);
		model.addAttribute("vik", usluga);
		model.addAttribute("vikVlas", lista);
		return "VikOsnovniProfil";
	}
	
	@RequestMapping(value = "/vikendice/{id}")
	public String getUnauthServicePage(Model model, @PathVariable Long id) {
		System.out.println("BrodProfil page was called!");
		Vikendica usluga = uslugaServis.findById(id);
		List<Vikendica> lista = uslugaServis.findByVlasnik(3L);
		model.addAttribute("vik", usluga);
		model.addAttribute("vikVlas", lista);
		System.out.println(model.toString());
		return "VikOsnovniProfil";
	}
	
	@RequestMapping(value = "/Regvikendice/{id}")
	public String getAuthServicePage(Model model, @PathVariable Long id) {
		System.out.println("BrodProfil page was called!");
		Vikendica usluga = uslugaServis.findById(id);
		List<Vikendica> lista = uslugaServis.findByVlasnik(3L);
		model.addAttribute("vik", usluga);
		model.addAttribute("vikVlas", lista);
		System.out.println(model.toString());
		return "ProfilVikendica";
	}
}
