package com.Reservations.Kontroleri;

import java.io.IOException;
import java.text.ParseException;
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
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.Reservations.DTO.GVarijablaDTO;
import com.Reservations.Dodaci.PrihodPDFGenerator;
import com.Reservations.Modeli.Brod;
import com.Reservations.Modeli.GlobalnaVarijabla;
import com.Reservations.Modeli.Korisnik;
import com.Reservations.Modeli.Prihod;
import com.Reservations.Modeli.Registracija;
import com.Reservations.Modeli.Vikendica;
import com.Reservations.Servis.BrodServis;
import com.Reservations.Servis.GVarijableServis;
import com.Reservations.Servis.KorisnikServis;
import com.Reservations.Servis.PrihodServis;
import com.Reservations.Servis.RegistracijaServis;
import com.Reservations.Servis.UlogaServis;
import com.Reservations.Servis.VikendicaServis;
import com.ibm.icu.text.DateFormat;
import com.ibm.icu.text.SimpleDateFormat;
import com.lowagie.text.DocumentException;

@Controller
public class VlasnikVikendiceKontroler {

	@Autowired
	PrihodServis prihodServis;
	
	@Autowired
	GVarijableServis gvServis;

	@Autowired
	KorisnikServis korisnikServis;

	@Autowired
	UlogaServis ulogaServis;

	@Autowired
	VikendicaServis vikendicaServis;
/*
	
	@RequestMapping(value = "/vikendicaVlasnik/request/{id}")
	public String viewRequest(Model model, @PathVariable Long id) {
		System.out.println("Request "+ String.valueOf(id) +" was opened!");
		Registracija reg = regServis.findById(id);
		model.addAttribute("zahtev", reg);
		return "adminRequest";
	}
	
	@RequestMapping(value = "/vikendicaVlasnik/request/{id}/submit")
	public String sendBackRequest(@PathVariable Long id, @RequestParam String radio, @RequestParam String textarea) throws AddressException, MessagingException, IOException {
		System.out.println("Request was processed!");
		Registracija reg = regServis.findById(id);
		System.out.println("radio = " + radio);
		System.out.println("textarea = " + textarea);
		System.out.println(reg.toPrivateString());
		if (radio.equals("deny")) {
			this.sendEmailToUser(false, textarea, reg.getEmail());
			regServis.delete(id);
		}
		else if (radio.equals("accept")) {
			this.sendEmailToUser(true, textarea, reg.getEmail());
			try {
				korisnikServis.save(reg);
			}
			catch (Exception e) {
				System.out.println(e.getStackTrace().toString());
			}
			regServis.delete(id);
		}
		else {
			System.out.println("Something went wrong!");
		}
		return "adminProfile";
	}
*/
	@RequestMapping(value = "/vikendicaVlasnik/prikaziVikendice", method = RequestMethod.GET)
	public String getEntitiesPage(Model model) 
	{
		System.out.println("Pregled Vikendica page was called!");
		List<Korisnik> korisnici = korisnikServis.listAll();
		List<Vikendica> vikendice = vikendicaServis.listAll();
		Korisnik vlasnik=null;
		for (Korisnik kor : korisnici) {
			if (kor.getUloga().getIme().equals("VikendicaVlasnik"))
			{
				System.out.println("Pronasao sam vlasnika vikendice!");
				vlasnik = kor;
				break;
			}
		}
		if(vlasnik!=null)
		{
			for(Vikendica vikendica : vikendice)
			{
				if(vikendica.getVlasnik().equals(vlasnik))model.addAttribute("vikendice", vikendica);
			}
		}
		else System.out.println("Vlasnik vikendice nije pronadjen ili je doslo do greske!");
		

		System.out.println(model.toString());
		return "vikendicePregled";
	}

	@RequestMapping(value = "/vikendicaVlasnik/my-profile")
	public String getDataPage(Model model) 
	{
		System.out.println("My profile page was called!");
		Korisnik vlasnikVikendice = korisnikServis.findById(6L);//TODO:dodati ID iz tokena
		model.addAttribute("vlasnikVikendice", vlasnikVikendice);
		return "vlasnikVikendicePodaci";//TODO:vlasnikVikendiceMyData
	}

	@RequestMapping(value = "/admin/reports")
	public String getReportsDates() 
	{
		System.out.println("Report page was called!");
		return "adminReports";
	}

	@GetMapping("/admin/reports/print")
    public void exportToPDF(HttpServletResponse response, @RequestParam String pocDatum, @RequestParam String krajDatum) throws DocumentException, IOException, ParseException 
	{
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
        Date pocetni =new SimpleDateFormat("dd/MM/yyyy").parse(pocDatum);  
        Date krajnji =new SimpleDateFormat("dd/MM/yyyy").parse(krajDatum); 
        
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=prihodi_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
         
        List<Prihod> lista = prihodServis.listAll();
         
        PrihodPDFGenerator pdf = new PrihodPDFGenerator(lista, pocetni, krajnji);
        pdf.export(response);
         
    }
/*
	private void sendEmailToUser(Boolean prihvacen, String razlog, String mail) throws AddressException, MessagingException, IOException {
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("mrs.isa.test@gmail.com", "123456789mrs.");
			}
		});
		Message msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress("mrs.isa.test@gmail.com", false));

		msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mail));
		msg.setSubject("Zahtev za registraciju");
		msg.setContent("Zahtev za registraciju", "text/html");
		msg.setSentDate(new Date());
		MimeBodyPart messageAccept = new MimeBodyPart();
		MimeBodyPart messageDenyReason = new MimeBodyPart();
		Multipart multipart = new MimeMultipart();
		if(prihvacen) {
			msg.setSubject("Zahtev za registraciju");
			msg.setContent("Zahtev za registraciju", "text/html");
			
			messageAccept.setContent("Cestitamo! Vas zahtev za registraciju je odobren! Mozete se ulogovati vec danas.", "text/html");

			
			multipart.addBodyPart(messageAccept);
		}
		else {
			messageAccept.setContent("Zao nam je. Vas zahtev za registraciju je odbijen.", "text/html");
			messageDenyReason.setContent("Razlog odbijanja: " + razlog, "text/html");

			multipart.addBodyPart(messageAccept);
			multipart.addBodyPart(messageDenyReason);
		}

		msg.setContent(multipart);
		
		Transport.send(msg);
	}
	*/
}
