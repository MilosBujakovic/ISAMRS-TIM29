package com.Reservations.Kontroleri;

import java.io.IOException;
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
import com.Reservations.Modeli.Korisnik;
import com.Reservations.Modeli.Rezervacija;
import com.Reservations.Modeli.enums.TipEntiteta;
import com.Reservations.Modeli.enums.TipRezervacije;
import com.Reservations.Servis.KorisnikServis;
import com.Reservations.Servis.RezervacijaServis;

@Controller
public class RezervacijaKontroler {
	
	
	@Autowired
	KorisnikServis userService;
	
	@Autowired
	RezervacijaServis rez;
	
	@RequestMapping(value = "/rezervisiVik/{id}/{id2}")
	public String registerOwner( @PathVariable Long id, @PathVariable Long id2, RezervacijaDTO regRequest,Model model) {
	Korisnik k=userService.findById(id2);
		Rezervacija user=rez.findById(id);
	    model.addAttribute("pod",user);
	    System.out.println(regRequest.toString());
	//    model.addAttribute("id",regRequest.getId() );
		System.out.println("Rezervacija poslata POSLAT!");
		
		this.rez.save(regRequest,TipEntiteta.vikendica,id,TipRezervacije.obicna,id2);
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
	public String rezerve( @PathVariable Long id, @PathVariable Long id2, RezervacijaDTO regRequest,Model model) {
	Korisnik k=userService.findById(id2);
		Rezervacija user=rez.findById(id);
	    model.addAttribute("pod",user);
	    System.out.println(regRequest.toString());
	//    model.addAttribute("id",regRequest.getId() );
		System.out.println("Rezervacija poslata POSLAT!");
		
		this.rez.save(regRequest,TipEntiteta.brod,id,TipRezervacije.obicna,id2);
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
	
	@RequestMapping(value = "/rezervisiUslugu/{id}/{id2}")
	public String rezerv( @PathVariable Long id, @PathVariable Long id2, RezervacijaDTO regRequest,Model model) throws AddressException, MessagingException, IOException {
	Korisnik k=userService.findById(id2);
		Rezervacija user=rez.findById(id);
	    model.addAttribute("pod",user);
	    System.out.println(regRequest.toString());
	//    model.addAttribute("id",regRequest.getId() );
		System.out.println("Rezervacija poslata POSLAT!");
		
		this.rez.save(regRequest,TipEntiteta.usluga,id,TipRezervacije.obicna,id2);
		this.sendEmailToUser(TipEntiteta.usluga,k.getEmail());
		return "profilKorisnika";
	}
	
	@RequestMapping(value = "/mojeRez/{id}")
	public String rezMoje(@PathVariable Long id,Model model){
	
	
	 		System.out.println("AzurirajPodatke page was called!");
	 		List<Rezervacija> user=rez.findByKlijent(id);
	 		model.addAttribute("pod", user);
	 		System.out.println(model.toString());
	 		 return "MojeRezervacije";
	 	  }
	
	@RequestMapping(value = "/IstorijaRez/{id}")
	public String IstMoje(@PathVariable Long id,Model model){
	
	
	 		System.out.println("AzurirajPodatke page was called!");
	 		List<Rezervacija> user=rez.findByKlijentDate(id);
	 		model.addAttribute("pod", user);
	 		System.out.println(model.toString());
	 		 return "IstorijaRezervacija";
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
}
