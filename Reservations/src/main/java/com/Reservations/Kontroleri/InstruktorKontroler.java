package com.Reservations.Kontroleri;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.Reservations.DTO.AzuriranjeInstruktoraDTO;
import com.Reservations.DTO.PoslovanjeEntitetaDTO;
import com.Reservations.DTO.PromenaLozinkeDTO;
import com.Reservations.Exception.ResourceConflictException;
import com.Reservations.Modeli.Korisnik;
import com.Reservations.Modeli.Rezervacija;
import com.Reservations.Modeli.Usluga;
import com.Reservations.Servis.BrisanjeNalogaServis;
import com.Reservations.Servis.KorisnikServis;
import com.Reservations.Servis.RezervacijaServis;
import com.Reservations.Servis.UslugaServis;

@Controller
@RequestMapping(value = "/instruktor/{id}")
public class InstruktorKontroler {
	@Autowired
	KorisnikServis korisnikServis;

	@Autowired
	UslugaServis uslugaServis;

	@Autowired
	RezervacijaServis rezervacijaServis;

	@Autowired
	BrisanjeNalogaServis bnServis;

	private Boolean[] select = new Boolean[10000];

	@RequestMapping(value = "")
	public String getHomePage(Model model, @PathVariable Long id) {
		System.out.println("Instruktor page was called!");
		List<Usluga> usluge = uslugaServis.findByInstruktor(id);
		List<Rezervacija> rezervacije = rezervacijaServis.findByVlasnikInst(id, false);
		model.addAttribute("id", id);
		model.addAttribute("usluge", usluge);
		model.addAttribute("rezervacije", rezervacije);
		return "instruktor/instruktorPocetna";
	}

	@RequestMapping(value = "/profil")
	public String getDataPage(Model model, @PathVariable Long id) {
		System.out.println("Instruktor page was called!");
		Korisnik u = korisnikServis.findById(id);
		model.addAttribute("user", u);
		return "instruktor/instruktorProfil";
	}

	@RequestMapping(value = "/profil/azuriraj")
	public String updateData(Model model, @PathVariable Long id, AzuriranjeInstruktoraDTO aiDTO) {
		System.out.println("Azuriraj page was called!");
		System.out.println(aiDTO.toString());
		korisnikServis.update(aiDTO);
		return "redirect:/instruktor/" + String.valueOf(id) + "/profil";
	}

	@RequestMapping(value = "/profil/promeniLozinku", method = RequestMethod.POST)
	public String changePassword(@PathVariable Long id, PromenaLozinkeDTO plDTO) {
		System.out.println("Lozinka page was called!");
		System.out.println(plDTO.toString());
		Korisnik k = korisnikServis.findById(id);
		System.out.println(k.toString());
		if (plDTO.getStaraLozinka().equals(k.getLozinka())) {
			if (plDTO.getNovaLozinka().equals(plDTO.getNovaPonovo()))
				korisnikServis.changePassword(id, plDTO.getStaraLozinka(), plDTO.getNovaLozinka());
			else
				throw new ResourceConflictException(id, "Lozinke se ne poklapaju!");
		}
		return "redirect:/instruktor/" + String.valueOf(id) + "/profil";
	}

	@RequestMapping(value = "/profil/brisiNalog", method = RequestMethod.POST)
	public String requestDelete(@PathVariable Long id, @RequestParam String razlog) {
		System.out.println("Brisi page was called!");
		Korisnik k = korisnikServis.findById(id);
		bnServis.save(k, razlog);
		return "redirect:/instruktor/" + String.valueOf(id) + "/profil";
	}

	@RequestMapping(value = "/istorija")
	public String getHistoryPage(Model model, @PathVariable Long id) {
		System.out.println("Instruktor page was called!");
		List<Rezervacija> rezervacije = rezervacijaServis.findByVlasnikInst(id, true);
		for (int i = 0; i < rezervacije.size(); i++)
			select[i] = false;
		model.addAttribute("postoji", select);
		model.addAttribute("rezervacije", rezervacije);
		return "instruktor/instruktorIstorija";
	}

	@RequestMapping(value = "/izvestaj/{rId}")
	public String getReportsPage(Model model, @PathVariable Long id, @PathVariable Long rId,
			@RequestParam String izvestaj) {
		System.out.println("Izvestaj page was called!");
		System.out.println(izvestaj);
		List<Rezervacija> rezervacije = rezervacijaServis.findByVlasnikInst(id, true);
		Rezervacija rez = rezervacijaServis.findById(rId);
		System.out.println(rez.toString());
		System.out.println(rez.getIzvestaj());
		//this.select[rezervacije.indexOf(rez)] = rezervacijaServis.upisiIzvestajVI(rId, izvestaj);
		System.out.println(select.toString());
		return "redirect:/instruktor/" + String.valueOf(id) + "/istorija";
	}

	@RequestMapping(value = "/izvestaji")
	public String getReportsPage(Model model, @PathVariable Long id) {
		System.out.println("Instruktor page was called!");
		return "instruktor/instruktorIzvestaji";
	}
	
	@RequestMapping(value = "/izvjestajiPoslovanja/")
	public String izvjestajiPoslovanja(Model model, @PathVariable Long id) 
	{
		System.out.println("Izvjestaji poslovanja page was called!");
		Korisnik instruktor = korisnikServis.findById(id);
		model.addAttribute("instruktor", instruktor);
		
		List<PoslovanjeEntitetaDTO> sedmicnaPoslovanja = uslugaServis.izracunajSedmicnaPoslovanjaBrodova(instruktor);
		List<PoslovanjeEntitetaDTO> mjesecnaPoslovanja = uslugaServis.izracunajMjesecnaPoslovanjaBrodova(instruktor);
		List<PoslovanjeEntitetaDTO> godisnjaPoslovanja = uslugaServis.izracunajGodisnjaPoslovanjaBrodova(instruktor);
		model.addAttribute("sedmicnaPoslovanja", sedmicnaPoslovanja);
		model.addAttribute("mjesecnaPoslovanja", mjesecnaPoslovanja);
		model.addAttribute("godisnjaPoslovanja", godisnjaPoslovanja);
		
		return "/brodovi/izvjestajiOposlovanjuBrodova.html";
	}
	
	@RequestMapping(value = "/izvjestajPoslovanjaPeriod")
	public String izvjestajPoslovanjaPeriod(Model model, @PathVariable Long id, PoslovanjeEntitetaDTO poslovanje) 
	{
		System.out.println("Izvjestaji poslovanja period page was called!");
		System.out.println("pocetak: "+poslovanje.getPocetniDatum());
		System.out.println("kraj: "+poslovanje.getKrajnjiDatum());
		Korisnik vlasnik = korisnikServis.findById(id);
		model.addAttribute("vlasnikBroda", vlasnik);
		poslovanje.srediDatume();
		List<PoslovanjeEntitetaDTO> poslovanjeUsluga = uslugaServis.poslovanjeUslugaPeriod(poslovanje, vlasnik);
		model.addAttribute("poslovanja", poslovanjeUsluga);
		model.addAttribute("period", poslovanje);
		for(int i = 0; i< poslovanjeUsluga.size(); i++) System.out.println(poslovanjeUsluga.get(i));
		return "/brodovi/izvjestajPoslovanjaPeriod.html";
	}
}
