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
public class UslugaKontroler {
	@Autowired
	UslugaServis uslugaServis;

	@Autowired
	KorisnikServis korisnikServis;
	
	@RequestMapping(value = "/usluga")
	public String getProfilePage(Model model) {
		System.out.println("Usluga page was called!");
		Usluga usluga = uslugaServis.findById(0L);
		List<Usluga> lista = uslugaServis.findByInstruktor(5L);
		model.addAttribute("usluga", usluga);
		model.addAttribute("uslugeInst", lista);
		return "uslugaProfil";
	}
}
