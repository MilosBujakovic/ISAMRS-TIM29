package com.Reservations.Kontroleri;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.Reservations.DTO.AzuriranjeInstruktoraDTO;
import com.Reservations.DTO.PromenaLozinkeDTO;
import com.Reservations.Exception.ResourceConflictException;
import com.Reservations.Modeli.Korisnik;
import com.Reservations.Modeli.Usluga;
import com.Reservations.Servis.KorisnikServis;
import com.Reservations.Servis.UslugaServis;

@Controller
@RequestMapping(value = "/instruktor/{id}")
public class InstruktorKontroler {
	@Autowired
	KorisnikServis korisnikServis;
	
	@Autowired
	UslugaServis uslugaServis;
	
	@RequestMapping(value = "")
	public String getHomePage(Model model, @PathVariable Long id) {
		System.out.println("Instruktor page was called!");
		List<Usluga> lista = uslugaServis.findByInstruktor(id);
		model.addAttribute("usluge", lista);
		return "instruktorProfil";
	}
	
	@RequestMapping(value = "/profil")
	public String getDataPage(Model model, @PathVariable Long id) {
		System.out.println("Instruktor page was called!");
		Korisnik u = korisnikServis.findById(id);
		model.addAttribute("user", u);
		return "instruktorPodaci";
	}
	
	@RequestMapping(value = "/profil/azuriraj")
	public String updateData(Model model, @PathVariable Long id, AzuriranjeInstruktoraDTO aiDTO) {
		System.out.println("Azuriraj page was called!");
		System.out.println(aiDTO.toString());
		korisnikServis.update(aiDTO);
		return "redirect:/instruktor/"+String.valueOf(id)+"/profil";
	}
	
	@RequestMapping(value = "/profil/promeniLozinku", method=RequestMethod.POST)
	public String changePassword(@PathVariable Long id, PromenaLozinkeDTO plDTO) {
		System.out.println("Lozinka page was called!");
		System.out.println(plDTO.toString());
		Korisnik k = korisnikServis.findById(id);
		System.out.println(k.toString());
		if(plDTO.getStaraLozinka().equals(k.getLozinka())) {
			if(plDTO.getNovaLozinka().equals(plDTO.getNovaPonovo()))
				korisnikServis.changePassword(id, plDTO.getStaraLozinka(), plDTO.getNovaLozinka());
			else
				throw new ResourceConflictException(id, "Lozinke se ne poklapaju!");
		}
		return "redirect:/instruktor/"+String.valueOf(id)+"/profil";
	}
	
	@RequestMapping(value = "/profil/brisiNalog", method=RequestMethod.POST)
	public String requestDelete(@PathVariable Long id) {
		System.out.println("Brisi page was called!");
		Korisnik u = korisnikServis.findById(id);
		return "redirect:/instruktor/"+String.valueOf(id)+"/profil";
	}
	
	@RequestMapping(value = "/istorija")
	public String getHistoryPage(Model model, @PathVariable Long id) {
		System.out.println("Instruktor page was called!");
		List<Usluga> lista = uslugaServis.findByInstruktor(id);
		model.addAttribute("usluge", lista);
		return "instruktorProfil";
	}
	
	@RequestMapping(value = "/izvestaji")
	public String getReportsPage(Model model, @PathVariable Long id) {
		System.out.println("Instruktor page was called!");
		List<Usluga> lista = uslugaServis.findByInstruktor(id);
		model.addAttribute("usluge", lista);
		return "instruktorProfil";
	}
}
