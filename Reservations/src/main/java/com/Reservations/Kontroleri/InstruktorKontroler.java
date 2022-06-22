package com.Reservations.Kontroleri;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.Reservations.DTO.AzuriranjeInstruktoraDTO;
import com.Reservations.DTO.IzvjestajRezervacijaDTO;
import com.Reservations.DTO.PoslovanjeEntitetaDTO;
import com.Reservations.DTO.PromenaLozinkeDTO;
import com.Reservations.Exception.ResourceConflictException;
import com.Reservations.Modeli.Korisnik;
import com.Reservations.Modeli.Rezervacija;
import com.Reservations.Modeli.Usluga;
import com.Reservations.Modeli.enums.TipEntiteta;
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

	@RequestMapping(value = "")
	public String getHomePage(Model model, @PathVariable Long id) {
		System.out.println("Instruktor page was called!");
		Korisnik instruktor = korisnikServis.findById(id);
		model.addAttribute("instruktor", instruktor);
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
		Korisnik instruktor = korisnikServis.findById(id);
		model.addAttribute("instruktor", instruktor);
		List<Rezervacija> rezervacije = rezervacijaServis.findByVlasnikInst(id, true);
		model.addAttribute("rezervacije", rezervacije);
		return "instruktor/instruktorIstorija";
	}

	@RequestMapping(value = "/izvestajiPoslovanja")
	public String getReportsPage(Model model, @PathVariable Long id) {
		System.out.println("Izvestaji poslovanja page was called!");
		Korisnik instruktor = korisnikServis.findById(id);
		model.addAttribute("instruktor", instruktor);
		
		List<PoslovanjeEntitetaDTO> sedmicnaPoslovanja = uslugaServis.izracunajSedmicnaPoslovanjaUsluga(instruktor);
		List<PoslovanjeEntitetaDTO> mjesecnaPoslovanja = uslugaServis.izracunajMjesecnaPoslovanjaUsluga(instruktor);
		List<PoslovanjeEntitetaDTO> godisnjaPoslovanja = uslugaServis.izracunajGodisnjaPoslovanjaUsluga(instruktor);
		model.addAttribute("sedmicnaPoslovanja", sedmicnaPoslovanja);
		model.addAttribute("mjesecnaPoslovanja", mjesecnaPoslovanja);
		model.addAttribute("godisnjaPoslovanja", godisnjaPoslovanja);
		
		return "instruktor/instruktorIzvestaji";
	}
	
	@RequestMapping(value = "/izvestajiPoslovanja/tabela")
	public String getReportsTablePage(Model model, @PathVariable Long id, PoslovanjeEntitetaDTO poslovanje) {
		System.out.println("Izvjestaji poslovanja period page was called!");
		System.out.println("pocetak: "+poslovanje.getPocetniDatum());
		System.out.println("kraj: "+poslovanje.getKrajnjiDatum());
		Korisnik instruktor = korisnikServis.findById(id);
		model.addAttribute("instruktor", instruktor);
		poslovanje.srediDatume();
		List<PoslovanjeEntitetaDTO> poslovanjeUsluga = uslugaServis.poslovanjeUslugaPeriod(poslovanje, instruktor);
		model.addAttribute("poslovanja", poslovanjeUsluga);
		model.addAttribute("period", poslovanje);
		for(int i = 0; i< poslovanjeUsluga.size(); i++) System.out.println(poslovanjeUsluga.get(i));
		return "/instruktor/instruktorIzvestajiTabela";
	}
	
	@RequestMapping(value = "/klijent/{rId}")
	public String getDataPage(Model model, @PathVariable Long id, @PathVariable Long rId) {
		System.out.println("Profil klijenta za instruktora page was called!");
		Korisnik instruktor = korisnikServis.findById(id);
		model.addAttribute("instruktor", instruktor);
		Korisnik u = korisnikServis.findById(rId);
		model.addAttribute("user", u);
		return "instruktor/instruktorProfilKlijenta";
	}
	
	@RequestMapping(value = "/novaBrzaRezervacija", method = RequestMethod.GET)
	public String addQuickReservation(Model model, @PathVariable Long id) {
		System.out.println("Dodajemo brzu rezervaciju!");
		return "instruktor/dodajBrzuRezervaciju";
	}
	
	@RequestMapping(value = "/novaAkcija", method = RequestMethod.GET)
	public String addSpecialOffer(Model model, @PathVariable Long id) {
		System.out.println("Dodajemo akciju!");
		return "instruktor/dodajAkciju";
	}
	
	@RequestMapping(value = "/izvestajiRezervacija")
	public String getCommentsPage(Model model, @PathVariable Long id) {
		System.out.println("Izveštaji rezervacije page was called!");
		Korisnik instruktor = korisnikServis.findById(id);
		model.addAttribute("instruktor", instruktor);
		List<IzvjestajRezervacijaDTO> rezervacijeSa = rezervacijaServis.nadjiRezervacijeSaIzvjestajem(TipEntiteta.usluga, instruktor);
		List<IzvjestajRezervacijaDTO> rezervacijeBez = rezervacijaServis.nadjiRezervacijeBezIzvjestaja(TipEntiteta.usluga, instruktor);
		model.addAttribute("rezervacijeSa", rezervacijeSa);
		model.addAttribute("rezervacijeBez", rezervacijeBez);
		return "instruktor/instruktorIzvestajiRez";
	}
	
	@RequestMapping(value = "/napisiIzvestajRezervacija/{rId}")
	public String writeCommentPage(Model model, @PathVariable Long id, @PathVariable Long rId)	 {
		System.out.println("Pisi izveštaj page was called! rID: " + String.valueOf(rId));
		Korisnik instruktor = korisnikServis.findById(id);
		model.addAttribute("instruktor", instruktor);
		
		Rezervacija rezervacija = rezervacijaServis.findById(rId);
		IzvjestajRezervacijaDTO izvestaj = new IzvjestajRezervacijaDTO(rezervacija);
		
		model.addAttribute("rezervacija", izvestaj);
		return "instruktor/instruktorNapisiIzvestaj";
	}
	
	@RequestMapping(value = "/upisiIzvestajRezervacija/{rId}", method=RequestMethod.POST)
	public String addComment(@PathVariable Long id, @PathVariable Long rId, IzvjestajRezervacijaDTO izvestaj)	 {
		System.out.println("Upis izveštaja page was called! rID: " + String.valueOf(rId));
		Rezervacija rezervacija = rezervacijaServis.findById(rId);
		rezervacija.setIzvjestaj(izvestaj.getIzvjestaj());
		boolean success = rezervacijaServis.upisiRezervaciju(rezervacija);
		if(success)
		{
			return "redirect:/instruktor/" + String.valueOf(id) + "/izvestajiRezervacija";
		}
		else
		{
			return "redirect:/instruktor/" + String.valueOf(id) + "/napisiIzvestajRezervacija/" + String.valueOf(rId);
		}
	}
}
