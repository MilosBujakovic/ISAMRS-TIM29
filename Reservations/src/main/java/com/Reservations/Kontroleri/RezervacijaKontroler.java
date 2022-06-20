package com.Reservations.Kontroleri;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Reservations.DTO.RezervacijaDTO;
import com.Reservations.DTO.RezervacijaSpisakDTO;
import com.Reservations.Modeli.Korisnik;
import com.Reservations.Modeli.Rezervacija;
import com.Reservations.Modeli.Vikendica;
import com.Reservations.Modeli.enums.TipEntiteta;
import com.Reservations.Modeli.enums.TipRezervacije;
import com.Reservations.Servis.KorisnikServis;
import com.Reservations.Servis.RezervacijaServis;
import com.Reservations.Servis.VikendicaServis;

@Controller
@RequestMapping(value = "/rezervacije")
public class RezervacijaKontroler {
	
	
	@Autowired
	KorisnikServis korisnikServis;
	
	@Autowired
	RezervacijaServis rezervacijaServis;

	 @Autowired
	 VikendicaServis vikendicaServis;
	
	@RequestMapping(value = "/rezervisiVik/{id}/{klijent_id}")
	public String registerOwner( @PathVariable Long id, @PathVariable Long klijent_id, RezervacijaDTO regRequest,Model model) {
	Korisnik k=korisnikServis.findById(klijent_id);
		Rezervacija user=rezervacijaServis.findById(id);
	    model.addAttribute("pod",user);
	    System.out.println(regRequest.toString());
	//    model.addAttribute("id",regRequest.getId() );
		System.out.println("Rezervacija poslata POSLAT!");
		
		this.rezervacijaServis.save(regRequest,TipEntiteta.vikendica,id,TipRezervacije.obicna,klijent_id);
		try {
			this.sendEmailToUser(TipEntiteta.vikendica,k.getEmail());
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "profilKorisnika";
	}
	

	@RequestMapping(value = "/rezervisiBrod/{id}/{id2}")
	public String rezerve( @PathVariable Long id, @PathVariable Long id2, RezervacijaDTO regRequest,Model model)
	{
		Rezervacija user=rezervacijaServis.findById(id);

		Korisnik k=korisnikServis.findById(id2);
	    model.addAttribute("pod",user);
	    System.out.println(regRequest.toString());
	//    model.addAttribute("id",regRequest.getId() );
		System.out.println("Rezervacija poslata POSLAT!");
		
		this.rezervacijaServis.save(regRequest,TipEntiteta.brod,id,TipRezervacije.obicna,id2);
		try {
			this.sendEmailToUser(TipEntiteta.usluga,k.getEmail());
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "profilKorisnika";
	}
	
	@RequestMapping(value = "/rezervisiUslugu/{id}/{klijent_id}")
	public String rezerv( @PathVariable Long id, @PathVariable Long klijent_id, RezervacijaDTO regRequest,Model model) throws AddressException, MessagingException, IOException {
	Korisnik k=korisnikServis.findById(klijent_id);
		Rezervacija user=rezervacijaServis.findById(id);
	    model.addAttribute("pod",user);
	    System.out.println(regRequest.toString());
	//    model.addAttribute("id",regRequest.getId() );
		System.out.println("Rezervacija poslata POSLAT!");
		
		this.rezervacijaServis.save(regRequest,TipEntiteta.usluga,id,TipRezervacije.obicna,klijent_id);
		this.sendEmailToUser(TipEntiteta.usluga,k.getEmail());
		return "profilKorisnika";
	}
	
	@RequestMapping(value = "/mojeRez/{id}")
	public String rezMoje(@PathVariable Long id,Model model){
	
	
	 		System.out.println("AzurirajPodatke page was called!");

	 		List<Rezervacija> user=rezervacijaServis.findByKlijent(id,null);

	 		

	 		model.addAttribute("pod", user);
	 		System.out.println(model.toString());
	 		 return "MojeRezervacije";
	 	  }
	@RequestMapping(value = "/IstorijaRez/{id}")
	public String rezMojeaa(@PathVariable Long id,Model model){
	
	
	 		System.out.println("AzurirajPodatke page was called!");

	 		List<Rezervacija> user=rezervacijaServis.findByKlijent(id,null);
            
	 		

	 		model.addAttribute("pod", user);
	 		System.out.println(model.toString());
	 		 return "IstorijaRezervacija";
	 	  }
	
	
	@RequestMapping(value = "/IstorijaRezVik/{id}")
	public String IstMoje(@PathVariable Long id,Model model){
	
	
	 		System.out.println("AzurirajPodatke page was called!");
	 		List<Rezervacija> user=rezervacijaServis.findByKlijentDateVik(id);
	 		model.addAttribute("pod", user);
	 		
            
			List<Rezervacija>vik1=rezervacijaServis.RezSortCena(user);
			List<Rezervacija>vik2=rezervacijaServis.RezSortDatum(user);
			List<Rezervacija>vik3=rezervacijaServis.RezSortTrajanje(user);
			for (Rezervacija rezervacija : vik1) {
				System.out.println(rezervacija.toString());
			}
			
			model.addAttribute("sortcene", vik1);
			model.addAttribute("sortdatum", vik2);
			model.addAttribute("sorttrajanje",vik3);
	 		
	 		 return "IstorijaRezEntiteta";
	 	  }
	
	@RequestMapping(value = "/IstorijaRezBrd/{id}")
	public String IstMojebrd(@PathVariable Long id,Model model){
	
	
	 		System.out.println("AzurirajPodatke page was called!");
	 		List<Rezervacija> user=rezervacijaServis.findByKlijentDateBrod(id);
	 		model.addAttribute("pod", user);
	 		System.out.println(model.toString());
	 		 return "IstorijaRezEntiteta";
	 	  }
	@RequestMapping(value = "/IstorijaRezUsl/{id}")
	public String IstMojeUsl(@PathVariable Long id,Model model){
	
	
	 		System.out.println("AzurirajPodatke page was called!");
	 		List<Rezervacija> user=rezervacijaServis.findByKlijentDateUsluga(id);
	 		model.addAttribute("pod", user);
	 		System.out.println(model.toString());
	 		 return "IstorijaRezEntiteta";
	 	  }
	

	private void sendEmailToUser(TipEntiteta rez,String mail ) throws AddressException, MessagingException, IOException {
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp-mail.outlook.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("mrs.isa.test@outlook.com", "123456789mrs.");
			}
		});
		Message msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress("mrs.isa.test@outlook.com", false));

		msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("igorpavlov106@gmail.com"));
		msg.setSubject("Rezervacija uspesna!");
		msg.setContent("Rezervacija uspesna", "text/html");
		msg.setSentDate(new Date());

		MimeBodyPart messageBodyPart = new MimeBodyPart();
		String ss="Vasa rezervacija  za :"+rez.toString()+" je uspesna!";
		messageBodyPart.setContent(ss, "text/html");

		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart);
		msg.setContent(multipart);
		Transport.send(msg);
	}
	
	   @RequestMapping(value="/rezervacijeMojihVikendica/{vlasnikID}")
	   public String rezervacijeMojihVikendica(Model model, @PathVariable Long vlasnikID)
	   {
		   
		   Korisnik vlasnik = korisnikServis.findById(vlasnikID);
		   
		   TipEntiteta tipEntiteta = TipEntiteta.vikendica;
		   List<Rezervacija> rezervacije = rezervacijaServis.pronadjiRezervacijePoVlasniku(vlasnik, tipEntiteta);
		   List<Vikendica> mojeVikendice = vikendicaServis.nadjiVikendicePoVlasniku(vlasnik);
		   //private TipEntiteta tipEntiteta;
		   //private long entitet_id;
		   List<RezervacijaSpisakDTO> mojeRezervacije = new ArrayList<RezervacijaSpisakDTO>();
		   for(Rezervacija rez : rezervacije) mojeRezervacije.add(new RezervacijaSpisakDTO(rez));
		   model.addAttribute("vlasnikVikendice", vlasnik);
		   model.addAttribute("rezervacije", mojeRezervacije);
		   model.addAttribute("vikendice", mojeVikendice);
		   return "/vikendice/spisakRezervacija.html";
	   }
	   
	   @RequestMapping(value="/{vlasnikID}/profil-klijenta/{klijentID}")
	   public String osnovniProfilKlijenta(Model model, @PathVariable Long vlasnikID, @PathVariable Long klijentID)
	   {
		   System.out.println("Vlasnik ID: "+vlasnikID);
		   System.out.println("Klijent ID: "+klijentID);
		   Korisnik klijent = korisnikServis.findById(klijentID);
		   if(klijent.getLinkSlike()==null || klijent.getLinkSlike().equals(""))
			   klijent.setLinkSlike("/img/avatar.png");
		   Korisnik vlasnik = korisnikServis.findById(vlasnikID);
		   model.addAttribute("klijent", klijent);
		   model.addAttribute("vlasnikVikendice", vlasnik);
		   return "/vikendice/osnovniProfilKlijenta.html";
	   }
	   
	   
}
