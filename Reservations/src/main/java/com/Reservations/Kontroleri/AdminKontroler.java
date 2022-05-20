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
import com.Reservations.Servis.VikendicaServis;
import com.ibm.icu.text.DateFormat;
import com.ibm.icu.text.SimpleDateFormat;
import com.lowagie.text.DocumentException;

@Controller
public class AdminKontroler {

	@Autowired
	PrihodServis prihodServis;
	
	@Autowired
	GVarijableServis gvServis;

	@Autowired
	KorisnikServis korisnikServis;

	@Autowired
	RegistracijaServis regServis;

	@Autowired
	BrodServis brodServis;

	@Autowired
	VikendicaServis vikendicaServis;

	@RequestMapping(value = "/admin")
	public String getAdminPage(Model model) {
		System.out.println("Admin page was called!");
		List<Registracija> lista = regServis.listAll();
		model.addAttribute("zahtevi", lista);
		return "adminProfile";
	}
	
	@RequestMapping(value = "/admin/request/{id}")
	public String viewRequest(Model model, @PathVariable Long id) {
		System.out.println("Request "+ String.valueOf(id) +" was opened!");
		Registracija reg = regServis.findById(id);
		model.addAttribute("zahtev", reg);
		return "adminRequest";
	}
	
	@RequestMapping(value = "/admin/request/{id}/submit")
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

	@RequestMapping(value = "/admin/view-all", method = RequestMethod.GET)
	public String getEntitiesPage(Model model) {
		System.out.println("All entities page was called!");
		List<Korisnik> lista = korisnikServis.listAll();
		List<Korisnik> klijenti = new ArrayList<Korisnik>();
		List<Korisnik> vlasniciV = new ArrayList<Korisnik>();
		List<Korisnik> vlasniciB = new ArrayList<Korisnik>();
		List<Korisnik> instruktori = new ArrayList<Korisnik>();
		List<Brod> brodovi = brodServis.listAll();
		List<Vikendica> vikendice = vikendicaServis.listAll();
		for (Korisnik kor : lista) {
			if (kor.getUloga().getIme().equals("Klijent"))
				klijenti.add(kor);
			else if (kor.getUloga().getIme().equals("VikendicaVlasnik"))
				vlasniciV.add(kor);
			else if (kor.getUloga().getIme().equals("BrodVlasnik"))
				vlasniciB.add(kor);
			else if (kor.getUloga().getIme().equals("Instruktor"))
				instruktori.add(kor);
		}
		model.addAttribute("klijenti", klijenti);
		model.addAttribute("vlasniciV", vlasniciV);
		model.addAttribute("vlasniciB", vlasniciB);
		model.addAttribute("instruktori", instruktori);
		model.addAttribute("brodovi", brodovi);
		model.addAttribute("vikendice", vikendice);
		System.out.println(model.toString());
		return "adminList";
	}

	@RequestMapping(value = "/admin/my-profile")
	public String getDataPage(Model model) {
		System.out.println("My profile page was called!");
		Korisnik admin = korisnikServis.findById(6L);
		model.addAttribute("admin", admin);
		return "adminMyData";
	}

	@RequestMapping(value = "/admin/revenue")
	public String getRevenuePage(Model model) {
		System.out.println("Revenue page was called!");
		List<Prihod> lista = prihodServis.listAll();
		GlobalnaVarijabla gv = gvServis.findByName("procenat");
		if (gv != null) {
			double procDec = Double.parseDouble(gv.getVrednost());
			int proc = (int)(procDec * 100);
			String procenat = String.valueOf(proc) + "%";
			model.addAttribute("procenatDec", procDec);
			model.addAttribute("procenat", procenat);
		}
		else {
			model.addAttribute("procenatDec", "undefined");
			model.addAttribute("procenat", "undefined");
		}
		model.addAttribute("prihodi", lista);
		return "adminRevenue";
	}

	@RequestMapping(value = "/admin/revenue/change-percent")
	public String changeRevenue(Model model, @RequestParam String procenat) {
		System.out.println("Revenue was changed!");
		List<Prihod> lista = prihodServis.listAll();
		GlobalnaVarijabla gv = gvServis.findByName("procenat");
		if (gv == null) {
			long id = 0;
			for (long i = 0; i < Long.MAX_VALUE; i++)
			{
				GlobalnaVarijabla check = gvServis.findById(i);
				if (check != null)
				{
					continue;
				}
				else
				{
					id = i;
					break;
				}
			}
			GVarijablaDTO gvDTO = new GVarijablaDTO(id, "procenat", procenat);
			gvServis.save(gvDTO);
		}
		else {
			GVarijablaDTO gvDTO = new GVarijablaDTO(gv.getID(), gv.getIme(), procenat);
			gvServis.update(gvDTO);
			double procDec = Double.parseDouble(gv.getVrednost());
			int proc = (int)(procDec * 100);
			String procStr = String.valueOf(proc) + "%";
			model.addAttribute("procenatDec", procDec);
			model.addAttribute("procenat", procStr);
		}
		return "adminRevenue";
	}

	@RequestMapping(value = "/admin/reports")
	public String getReportsDates() {
		System.out.println("Report page was called!");
		return "adminReports";
	}

	@GetMapping("/admin/reports/print")
    public void exportToPDF(HttpServletResponse response, @RequestParam String pocDatum, @RequestParam String krajDatum) throws DocumentException, IOException, ParseException {
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
}
