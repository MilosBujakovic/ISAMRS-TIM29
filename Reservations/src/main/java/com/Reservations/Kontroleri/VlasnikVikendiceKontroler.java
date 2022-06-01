package com.Reservations.Kontroleri;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.Reservations.DTO.PromenaLozinkeDTO;
import com.Reservations.DTO.VikendicaDTO;
import com.Reservations.DTO.VlasnikVikendiceDTO;
import com.Reservations.Modeli.Korisnik;
import com.Reservations.Modeli.Vikendica;
import com.Reservations.Servis.GVarijableServis;
import com.Reservations.Servis.KorisnikServis;
import com.Reservations.Servis.PrihodServis;
import com.Reservations.Servis.UlogaServis;
import com.Reservations.Servis.VikendicaServis;

@Controller
@RequestMapping(value = "/vikendicaVlasnik")
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

	@RequestMapping(value="/pocetna/{korisnickoIme}", method = RequestMethod.GET)
	public String prikaziPocetnu(Model model, @PathVariable String korisnickoIme)
	{
		System.out.println("prikaziPocetnu: "+korisnickoIme);
		Korisnik korisnik = korisnikServis.findByUsername(korisnickoIme);
		VlasnikVikendiceDTO vlasnik = new VlasnikVikendiceDTO(korisnik);
		model.addAttribute("vlasnikVikendice", vlasnik);
		return "/vlasnikVikendicePocetna.html";
	}
	/*
    @RequestMapping(value = "/getClinicAndDoctor", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClinicDoctorNameDTO> getClinicAndDoctor(@RequestParam String clinicId, @RequestParam String doctorId){
        System.out.println("prikaziPocetnu");
        ClinicDoctorNameDTO clinicDoctorNameDTO = doctorService.getClinicAndDoctor(clinicId,doctorId);
        return new ResponseEntity<ClinicDoctorNameDTO>(clinicDoctorNameDTO, HttpStatus.OK);
    }
	*/
	
	@RequestMapping(value = "/prikaziVikendice/{korisnickoIme}", method = RequestMethod.GET)
	public String getEntitiesPage(Model model, @PathVariable String korisnickoIme) 
	{
		System.out.println("Pregled Vikendica page was called!");
		List<Korisnik> korisnici = korisnikServis.listAll();
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
			System.out.println("Ubacujem vikendice");
			List<Vikendica> vikendice = vikendicaServis.findByVlasnik(vlasnik.getID());
			model.addAttribute("vikendice", vikendice);
			
		}
		else System.out.println("Vlasnik vikendice nije pronadjen ili je doslo do greske!");
		VlasnikVikendiceDTO vlasnikV= new VlasnikVikendiceDTO(korisnikServis.findByUsername(korisnickoIme));
		model.addAttribute("vlasnikVikendice", vlasnikV);
		System.out.println(model.toString());
		return "mojeVikendice";
	}
	
	@RequestMapping(value = "/profil/{korisnickoIme}")
	public String moj_profil(Model model, @PathVariable String korisnickoIme) 
	{
		System.out.println("Pozvan profil od: "+korisnickoIme+" !");
		VlasnikVikendiceDTO vlasnikVikendice = new VlasnikVikendiceDTO(korisnikServis.findByUsername(korisnickoIme));//TODO:dodati ID iz tokena
		model.addAttribute("vlasnikVikendice", vlasnikVikendice);
		return "profilVlasnikaVikendice";//TODO:vlasnikVikendiceMyData
	}

	@RequestMapping(value = "/moj-profil/{korisnickoIme}")
	public String getDataPage(Model model, @PathVariable String korisnickoIme) 
	{
		System.out.println("Pozvan profil od: "+korisnickoIme+" !");
		VlasnikVikendiceDTO vlasnikVikendice = new VlasnikVikendiceDTO(korisnikServis.findByUsername(korisnickoIme));//TODO:dodati ID iz tokena
		model.addAttribute("vlasnikVikendice", vlasnikVikendice);
		return "vlasnikVikendicePodaci";//TODO:vlasnikVikendiceMyData
	}
	
	@RequestMapping(value = "/azuriraj-podatke/{korisnickoIme}")
	 public String prikupiPodatke(Model model, @PathVariable String korisnickoIme){
	  		System.out.println("AzurirajPodatke page was called!");
	  		Korisnik korisnik=korisnikServis.findByUsername(korisnickoIme);
	  		VlasnikVikendiceDTO vlasnik = new VlasnikVikendiceDTO(korisnik);
	  		System.out.println(vlasnik);
	  		model.addAttribute("vlasnikVikendice", vlasnik);
	  		System.out.println("VLASNIK ID: "+vlasnik.getId());
	  		System.out.println(model.toString());
	  		 return "azurirajPodatkeVlasnika";
	  	  }
	
	@RequestMapping(value = "/azuriranje-podataka/{idVlasnika}")
	public String izmijeniPodatke(@PathVariable Long idVlasnika, Model model, VlasnikVikendiceDTO vlasnikVikendice)
	{		
	 		System.out.println("Azuriranje Podataka page was called!");
	 		System.out.println(vlasnikVikendice);
	 		Korisnik podaci=korisnikServis.azurirajPodatkeVlasnika(vlasnikVikendice);
	 		model.addAttribute("vlasnikVikendice", podaci);
	 		System.out.println(model.toString());
	 		 return "azurirajPodatkeVlasnika";		
	 	  }
	
	@RequestMapping(value = "/promjenaLozinke/{ID}")
	 public String promjenaLozinke(Model model, @PathVariable Long ID){
	  		System.out.println("PromjenaLozinke page was called!");
	  		Korisnik korisnik=korisnikServis.findById(ID);
	  		VlasnikVikendiceDTO vlasnik = new VlasnikVikendiceDTO(korisnik);
	  		System.out.println(vlasnik);
	  		PromenaLozinkeDTO lozinke = new PromenaLozinkeDTO();
	  		model.addAttribute("vlasnikVikendice", vlasnik);
	  		model.addAttribute("lozinke", lozinke);
	  		System.out.println(model.toString());
	  		 return "promjenaLozinkeVlasnika";
	  	  }
	
	@RequestMapping(value ="/promijeniLozinku/{ID}")
	 public String promijeniLozinku(Model model, @PathVariable Long ID, String ime, String prezime, String korisnickoIme, PromenaLozinkeDTO lozinke )
	{
  		System.out.println("PromijeniLozinku page was called!");
  		Korisnik korisnik=korisnikServis.findById(ID);
  		if(!lozinke.getStaraLozinka().equals(korisnik.getLozinka()))
  		{
  			return "promjenaLozinkeNeuspjesna";
  		}
  		else if(lozinke.getNovaLozinka().equals(lozinke.getNovaPonovo()))
  		{
  			korisnikServis.changePassword(korisnik.getID(), lozinke.getStaraLozinka(), lozinke.getNovaLozinka());
  	  		return "azurirajPodatkeVlasnika";
  		}
  		else return "promjenaLozinkeNeuspjesna";
  		
  	  }
	
   @RequestMapping(value ="/napraviVikendicu/{ID}")
   public String napraviVikendicu(Model model, @PathVariable Long ID)
   {
	   System.out.println("Pravljenje vikendice was called!");
	   Korisnik k = korisnikServis.findById(ID);
	   VikendicaDTO vikendica = new VikendicaDTO();
	   model.addAttribute("vikendica", vikendica);
	   model.addAttribute("vlasnikVikendice", k);
	   return "napraviVikendicu";
   }
/*
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
