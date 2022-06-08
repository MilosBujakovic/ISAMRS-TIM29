package com.Reservations.Kontroleri;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.Reservations.DTO.SlikaDTO;
import com.Reservations.DTO.VikendicaDTO;
import com.Reservations.Modeli.Korisnik;
import com.Reservations.Modeli.Rezervacija;
import com.Reservations.Modeli.Vikendica;
import com.Reservations.Repozitorijumi.RezervacijaRepozitorijum;
import com.Reservations.Servis.KorisnikServis;
import com.Reservations.Servis.SnimanjeDatotekaServis;
import com.Reservations.Servis.VikendicaServis;

@Controller
public class VikendicaKontroler {
	@Autowired
	VikendicaServis vikendicaServis;

	@Autowired
	KorisnikServis korisnikServis;
	
	@Autowired
	RezervacijaRepozitorijum rezervacijaRepozitorijum;
	
	@Autowired
	SnimanjeDatotekaServis snimanjeDatotekaServis;
	
	public String putanjaSlika = "/img/vikendice/";
	
	@RequestMapping(value = "/klijent/vikendice/{id}")
	public String getProfilePage(Model model, @PathVariable Long id) {
		System.out.println("BrodKlijentProfil page was called!");
		Vikendica usluga = vikendicaServis.findById(id);
		List<Vikendica> lista = vikendicaServis.findByVlasnik(3L);
		model.addAttribute("vik", usluga);
		model.addAttribute("vikVlas", lista);
		return "VikOsnovniProfil";
	}
	
	@RequestMapping(value = "/vikendice/{id}")
	public String getUnauthServicePage(Model model, @PathVariable Long id) {
		System.out.println("BrodProfil page was called!");
		Vikendica usluga = vikendicaServis.findById(id);
		List<Vikendica> lista = vikendicaServis.findByVlasnik(3L);
		model.addAttribute("vik", usluga);
		model.addAttribute("vikVlas", lista);
		System.out.println(model.toString());
		return "VikOsnovniProfil";
	}
	

	@RequestMapping(value = "/vikendice/napravi/{vlasnikID}", method=RequestMethod.POST, consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public String napraviVikendicu(SlikaDTO slikaDTO, Model model, @PathVariable Long vlasnikID, VikendicaDTO novaVikendica) throws IOException
	{		
		System.out.println("Napravi Vikendicu called!");
		System.out.println("slika vrijendost: "+ slikaDTO.getSlika());
		System.out.println(slikaDTO.getNazivSlike());
		//slikaDTO.setNazivSlike(slikaDTO.getNazivSlike().split("\\")[2]);
		String apsolutnaPutanja= (new File("src/main/resources/static")).getAbsolutePath();
		File slika = new File(apsolutnaPutanja+this.putanjaSlika+slikaDTO.getNazivSlike());
		System.out.println(slika.getAbsolutePath());
		slika.createNewFile();
		System.out.println("Usao u snimi");
		if(slikaDTO.getNazivSlike().trim().equals(""))
		{
			Korisnik vlasnik = korisnikServis.findById(vlasnikID);
			novaVikendica.setVlasnik(vlasnikID);
			novaVikendica.setLinkSlike("");
			System.out.println("Vikendica:" + novaVikendica);
			model.addAttribute("vlasnikVikendice", vlasnik);
			
			//TODO:upis u bazu snimanjeDatotekaServis.snimiSlikuVikendice(slikaDTO);
			
			String poruka = vikendicaServis.dodajVikendicu(novaVikendica);
			model.addAttribute("poruka", poruka);
			return "/vikendice/potvrdnaPoruka";
		}
		else try(OutputStream os = new FileOutputStream(slika))
		{
			os.write(slikaDTO.getSlika().getBytes());
			os.close();
			Korisnik vlasnik = korisnikServis.findById(vlasnikID);
			novaVikendica.setVlasnik(vlasnikID);
			novaVikendica.setLinkSlike(this.putanjaSlika+slikaDTO.getNazivSlike());
			System.out.println("Vikendica:" + novaVikendica);
			model.addAttribute("vlasnikVikendice", vlasnik);
			
			//TODO: Snimi sliku u bazu?
			String poruka = vikendicaServis.dodajVikendicu(novaVikendica);
			model.addAttribute("poruka", poruka);
			return "/vikendice/potvrdnaPoruka";
		}
		catch(Exception e)
		{
			e.printStackTrace();
			String poruka = "Doslo je do greske u dodavanju!";
			model.addAttribute("poruka", poruka);
			return "/vikendice/pogresnaPoruka";
		}
		
	}
	
	@RequestMapping(value = "/vikendice/izmijeni/{vlasnikID}/{vikendicaID}", method=RequestMethod.POST, consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public String izmijeniVikendicu(SlikaDTO slikaDTO, Model model, @PathVariable Long vlasnikID, @PathVariable Long vikendicaID, VikendicaDTO novaVikendica) throws IOException
	{	
		novaVikendica.setID(vikendicaID);
		Vikendica staraVikendica = vikendicaServis.findById(vikendicaID);
		
		System.out.println("Izmijeni Vikendicu called!");
		System.out.println("Slika nazivi:"+staraVikendica.getID());
		System.out.println("nova: "+novaVikendica.getID()==null);
		System.out.println("slika vrijendost: ");
		System.out.println(slikaDTO.getNazivSlike());
		//slikaDTO.setNazivSlike(slikaDTO.getNazivSlike().split("\\")[2]);
		if(slikaDTO.getNazivSlike()!=null && !slikaDTO.getNazivSlike().trim().equals(""))
		{
			String apsolutnaPutanja= (new File("src/main/resources/static")).getAbsolutePath();
			File slika = new File(apsolutnaPutanja+this.putanjaSlika+slikaDTO.getNazivSlike());
			System.out.println(slika.getAbsolutePath());
			slika.createNewFile();
			System.out.println("Usao u snimi2");
			try(OutputStream os = new FileOutputStream(slika))
			{
				System.out.println("usao u try/catch");
				os.write(slikaDTO.getSlika().getBytes());
				os.close();
				Korisnik vlasnik = korisnikServis.findById(vlasnikID);
				novaVikendica.setVlasnik(vlasnikID);
				novaVikendica.setLinkSlike(this.putanjaSlika+slikaDTO.getNazivSlike());
				System.out.println("Vikendica:" + novaVikendica);
				model.addAttribute("vlasnikVikendice", vlasnik);

				String poruka = vikendicaServis.izmijeniVikendicu(novaVikendica, staraVikendica);
				//TODO:upis u bazu snimanjeDatotekaServis.snimiSlikuVikendice(slikaDTO);
				model.addAttribute("poruka", poruka);
				return "/vikendice/potvrdnaPoruka";
			}
			catch(Exception e)
			{
				e.printStackTrace();
				model.addAttribute("poruka", "Doslo je do greske!");
				return "/vikendice/pogresnaPoruka";
			}
		}
		else
		{
			System.out.println("Usao u snimi3");
			Korisnik vlasnik = korisnikServis.findById(vlasnikID);
			novaVikendica.setVlasnik(vlasnikID);
			System.out.println("Vikendica:" + novaVikendica);
			model.addAttribute("vlasnikVikendice", vlasnik);
			String poruka = vikendicaServis.izmijeniVikendicu(novaVikendica, staraVikendica);
			//TODO:upis u bazu snimanjeDatotekaServis.snimiSlikuVikendice(slikaDTO);
			model.addAttribute("poruka", poruka);
			return "/vikendice/potvrdnaPoruka";
		}
	}
	
	@RequestMapping(value = "/vikendice/obrisi/{vlasnikID}/{vikendicaID}")
	public String obrisiVikendicu(Model model, @PathVariable Long vlasnikID, @PathVariable Long vikendicaID) throws IOException
	{		
		System.out.println("Obrisi Vikendicu called!");
		//slikaDTO.setNazivSlike(slikaDTO.getNazivSlike().split("\\")[2]);
		System.out.println("Usao u snimi");
		//TODO: zastita od brisanja ukoliko postoje rezervacije?s
		
		Korisnik vlasnik = korisnikServis.findById(vlasnikID);
		model.addAttribute("vlasnikVikendice", vlasnik);
		String poruka = vikendicaServis.obrisiVikendicu(vlasnikID, vikendicaID);
		model.addAttribute("poruka", poruka);
		return "/vikendice/potvrdnaPoruka";
		//TODO:upis u bazu snimanjeDatotekaServis.snimiSlikuVikendice(slikaDTO);
		
		
	}

	@RequestMapping(value = "/Regvikendice/{id}/{id2}")
	public String getAuthServicePage(Model model, @PathVariable Long id,@PathVariable Long id2) {
		System.out.println("ProfilVikendice page was called!");
		Korisnik k=korisnikServis.findById(id2);
		Vikendica usluga = vikendicaServis.findById(id);
		List<Vikendica> lista = vikendicaServis.findByVlasnik(usluga.getVlasnik().getID());
		model.addAttribute("vik", usluga);
		model.addAttribute("vikVlas", lista);
		model.addAttribute("kor",k);
		
		return "ProfilVikendica";

	}
}
