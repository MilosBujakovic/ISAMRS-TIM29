package com.Reservations.Kontroleri;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	
	@RequestMapping(value = "/instruktor")
	public String getAdminPage(Model model) {
		System.out.println("Instruktor page was called!");
		List<Usluga> lista = uslugaServis.findByInstruktor(5L);
		model.addAttribute("usluge", lista);
		return "instruktorProfil";
	}
}
