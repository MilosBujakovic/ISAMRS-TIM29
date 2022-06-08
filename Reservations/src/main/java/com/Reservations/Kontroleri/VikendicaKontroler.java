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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.Reservations.DTO.SlikaDTO;
import com.Reservations.DTO.VikendicaDTO;
import com.Reservations.Modeli.Korisnik;
import com.Reservations.Modeli.Vikendica;
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
		try(OutputStream os = new FileOutputStream(slika))
		{
			os.write(slikaDTO.getSlika().getBytes());
			os.close();
			Korisnik vlasnik = korisnikServis.findById(vlasnikID);
			novaVikendica.setVlasnik(vlasnikID);
			novaVikendica.setLinkSlike(this.putanjaSlika+slikaDTO.getNazivSlike());
			System.out.println("Vikendica:" + novaVikendica);
			model.addAttribute("vlasnikVikendice", vlasnik);
			vikendicaServis.dodajVikendicu(novaVikendica);
			//TODO:upis u bazu snimanjeDatotekaServis.snimiSlikuVikendice(slikaDTO);
			return "/vikendice/napravljenaVikendica";
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return "/loginFailure";
		}
		
	}
	
	@RequestMapping(value = "/vikendice/izmijeni/{vlasnikID}/{vikendicaID}", method=RequestMethod.POST, consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public String izmijeniVikendicu(SlikaDTO slikaDTO, Model model, @PathVariable Long vlasnikID, @PathVariable Long vikendicaID, VikendicaDTO novaVikendica) throws IOException
	{		
		System.out.println("Izmijeni Vikendicu called!");
		System.out.println("slika vrijendost: "+ slikaDTO.getSlika());
		System.out.println(slikaDTO.getNazivSlike());
		//slikaDTO.setNazivSlike(slikaDTO.getNazivSlike().split("\\")[2]);
		String apsolutnaPutanja= (new File("src/main/resources/static")).getAbsolutePath();
		File slika = new File(apsolutnaPutanja+this.putanjaSlika+slikaDTO.getNazivSlike());
		System.out.println(slika.getAbsolutePath());
		slika.createNewFile();
		System.out.println("Usao u snimi");
		Vikendica staraVikendica = vikendicaServis.findById(novaVikendica.getID());
		
		try(OutputStream os = new FileOutputStream(slika))
		{
			os.write(slikaDTO.getSlika().getBytes());
			os.close();
			Korisnik vlasnik = korisnikServis.findById(vlasnikID);
			novaVikendica.setVlasnik(vlasnikID);
			novaVikendica.setLinkSlike(this.putanjaSlika+slikaDTO.getNazivSlike());
			System.out.println("Vikendica:" + novaVikendica);
			model.addAttribute("vlasnikVikendice", vlasnik);
			vikendicaServis.izmijeniVikendicu(novaVikendica, staraVikendica);
			//TODO:upis u bazu snimanjeDatotekaServis.snimiSlikuVikendice(slikaDTO);
			return "/vikendice/napravljenaVikendica";
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return "/loginFailure";
		}
		
	}
	
	@RequestMapping(value = "/vikendice/obrisi/{vlasnikID}/{vikendicaID}")
	public String obrisiVikendicu(Model model, @PathVariable Long vlasnikID, @PathVariable Long vikendicaID) throws IOException
	{		
		System.out.println("Obrisi Vikendicu called!");
		//slikaDTO.setNazivSlike(slikaDTO.getNazivSlike().split("\\")[2]);
		System.out.println("Usao u snimi");
		Vikendica staraVikendica = vikendicaServis.findById(vikendicaID);
		//TODO: zastita od brisanja ukoliko postoje rezervacije?s
		
		Korisnik vlasnik = korisnikServis.findById(vlasnikID);
		model.addAttribute("vlasnikVikendice", vlasnik);
		if(vikendicaServis.obrisiVikendicu(vlasnikID, vikendicaID))
		{
			 return "/vikendice/napravljenaVikendica";
		}
		return "loginFailure";
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
