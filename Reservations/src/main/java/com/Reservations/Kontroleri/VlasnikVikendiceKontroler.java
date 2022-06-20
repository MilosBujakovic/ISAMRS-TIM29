package com.Reservations.Kontroleri;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.Reservations.DTO.KlijentSpisakDTO;
import com.Reservations.DTO.PoslovanjeEntitetaDTO;
import com.Reservations.DTO.PromenaLozinkeDTO;
import com.Reservations.DTO.SlikaDTO;
import com.Reservations.DTO.VikendicaDTO;
import com.Reservations.DTO.VlasnikVikendiceDTO;
import com.Reservations.Modeli.Korisnik;
import com.Reservations.Modeli.Rezervacija;
import com.Reservations.Modeli.Vikendica;
import com.Reservations.Modeli.enums.TipEntiteta;
import com.Reservations.Servis.GVarijableServis;
import com.Reservations.Servis.KorisnikServis;
import com.Reservations.Servis.PrihodServis;
import com.Reservations.Servis.RezervacijaServis;
import com.Reservations.Servis.UlogaServis;
import com.Reservations.Servis.VikendicaServis;

@Controller
@RequestMapping(value = "/vikendicaVlasnik")
public class VlasnikVikendiceKontroler {

	public String putanjaSlikaKorisnika = "/img/korisnici/";
	
	@Autowired
	PrihodServis prihodServis;
	
	@Autowired
	GVarijableServis gvServis;

	@Autowired
	KorisnikServis korisnikServis;

	@Autowired
	UlogaServis ulogaServis;

	@Autowired
	RezervacijaServis rezervacijaServis;
	
	@Autowired
	VikendicaServis vikendicaServis;


	@RequestMapping(value="/pocetna/{korisnickoIme}", method = RequestMethod.GET)
	public String prikaziPocetnu(Model model, @PathVariable String korisnickoIme)
	{
		System.out.println("prikaziPocetnu: "+korisnickoIme);
		Korisnik korisnik = korisnikServis.findByUsername(korisnickoIme);
		VlasnikVikendiceDTO vlasnik = new VlasnikVikendiceDTO(korisnik);
		model.addAttribute("vlasnikVikendice", vlasnik);
		return "/vikendice/vlasnikVikendicePocetna.html";
	}

	
	@RequestMapping(value = "/prikaziVikendice/{vrstaPrikaza}/{vlasnikID}", method = RequestMethod.GET)
	public String getEntitiesPage(Model model, @PathVariable String vrstaPrikaza, @PathVariable Long vlasnikID) 
	{
		System.out.println("Pregled Vikendica page was called!");
		List<Korisnik> korisnici = korisnikServis.listAll();
		Korisnik vlasnik= korisnikServis.findById(vlasnikID);
		if(vlasnik!=null)
		{
			System.out.println("Ubacujem vikendice");
			List<Vikendica> vikendice = vikendicaServis.nadjiVikendicePoVlasniku(vlasnik);
			model.addAttribute("vikendice", vikendice);
			
		}
		else
		{
			System.out.println("Vlasnik vikendice nije pronadjen ili je doslo do greske!");
			return "/loginFaiulure";
		}
		VlasnikVikendiceDTO vlasnikV = new VlasnikVikendiceDTO(vlasnik);
		model.addAttribute("vlasnikVikendice", vlasnikV);
		System.out.println(model.toString());
		if(vrstaPrikaza.equals("upravljanje"))return "/vikendice/upravljanjeVikendicama.html";
		else if(vrstaPrikaza.equals("obrisi")) return "/vikendice/brisanjeVikendica.html";
		else return "/vikendice/mojeVikendice";
	}
	
	@RequestMapping(value = "/profil/{vlasnikID}")
	public String moj_profil(Model model, @PathVariable Long vlasnikID) 
	{
		VlasnikVikendiceDTO vlasnikVikendice = new VlasnikVikendiceDTO(korisnikServis.findById(vlasnikID));//TODO:dodati ID iz tokena
		System.out.println("Pozvan profil od: "+vlasnikVikendice.getKorisnickoIme()+" !");
		if(vlasnikVikendice.getLinkSlike()==null || vlasnikVikendice.getLinkSlike().equals("") ) vlasnikVikendice.setLinkSlike("/img/avatar.png");
		model.addAttribute("vlasnikVikendice", vlasnikVikendice);
		return "/vikendice/profilVlasnikaVikendice";//TODO:vlasnikVikendiceMyData
	}

	@RequestMapping(value = "/moj-profil/{korisnickoIme}")
	public String getDataPage(Model model, @PathVariable String korisnickoIme) 
	{
		System.out.println("Pozvan profil od: "+korisnickoIme+" !");
		VlasnikVikendiceDTO vlasnikVikendice = new VlasnikVikendiceDTO(korisnikServis.findByUsername(korisnickoIme));//TODO:dodati ID iz tokena
		System.out.println("slika profila: "+vlasnikVikendice.getLinkSlike());
		if(vlasnikVikendice.getLinkSlike()==null || vlasnikVikendice.getLinkSlike().equals("") ) vlasnikVikendice.setLinkSlike("/img/avatar.png");
		model.addAttribute("vlasnikVikendice", vlasnikVikendice);
		return "/vikendice/vlasnikVikendicePodaci";//TODO:vlasnikVikendiceMyData
	}
	
	@RequestMapping(value = "/azuriraj-podatke/{vlasnikID}")
	 public String prikupiPodatke(Model model, @PathVariable Long vlasnikID){
	  		System.out.println("AzurirajPodatke page was called!");
	  		Korisnik korisnik=korisnikServis.findById(vlasnikID);
	  		VlasnikVikendiceDTO vlasnik = new VlasnikVikendiceDTO(korisnik);
	  		
	  		if(vlasnik.getLinkSlike()==null || vlasnik.getLinkSlike().trim().equals("")) vlasnik.setLinkSlike("/img/avatar.png");
	  		model.addAttribute("vlasnikVikendice", vlasnik);
	  		System.out.println("VLASNIK ID: "+vlasnik.getId());
	  		System.out.println(model.toString());
	  		 return "/vikendice/azurirajPodatkeVlasnika.html";
	  	  }
	
	@RequestMapping(value = "/azuriranje-podataka/{idVlasnika}", method=RequestMethod.POST, consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public String izmijeniPodatke(@PathVariable Long idVlasnika, SlikaDTO slikaDTO, Model model, VlasnikVikendiceDTO vlasnikVikendice) throws IOException
	{		String poruka;
			System.out.println("slika naziv: "+slikaDTO.getNazivSlike());
	 		if(slikaDTO.getNazivSlike()!=null || !slikaDTO.getNazivSlike().trim().equals(""))
	 		{
				String apsolutnaPutanja= (new File("src/main/resources/static")).getAbsolutePath();
				File slika = new File(apsolutnaPutanja+this.putanjaSlikaKorisnika+slikaDTO.getNazivSlike());
				System.out.println(slika.getAbsolutePath());
				slika.createNewFile();
				//TODO:upis u bazu snimanjeDatotekaServis.snimiSlikuVikendice(slikaDTO);
				try(OutputStream os = new FileOutputStream(slika))
				{
					os.write(slikaDTO.getSlika().getBytes());
					os.close();
					System.out.println("Ucitana slika korisnika: "+slikaDTO.getPutanja()+slikaDTO.getNazivSlike());
					vlasnikVikendice.setLinkSlike(putanjaSlikaKorisnika+slikaDTO.getNazivSlike());
				}
				catch(Exception e)
				{
					poruka = "Doslo je do greške kod učitavanja slike";
					model.addAttribute("poruka", poruka);
					return "/vikendice/pogresnaPoruka";
				}
	 		}
			System.out.println("Azuriranje Podataka page was called!");
	 		System.out.println(vlasnikVikendice);
	 		Korisnik podaci=korisnikServis.azurirajPodatkeVlasnika(vlasnikVikendice);
	 		model.addAttribute("vlasnikVikendice", podaci);
	 		System.out.println(model.toString());
	 		poruka = "Uspjesno azurirani podaci korisnika";
	 		model.addAttribute("poruka", poruka);
	 		return "/vikendice/potvrdnaPoruka";		
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
	  		 return "/vikendice/promjenaLozinkeVlasnika";
	  	  }
	
	@RequestMapping(value ="/promijeniLozinku/{ID}")
	 public String promijeniLozinku(Model model, @PathVariable Long ID, String ime, String prezime, String korisnickoIme, PromenaLozinkeDTO lozinke )
	{
 		System.out.println("PromijeniLozinku page was called!");
 		Korisnik korisnik=korisnikServis.findById(ID);
 		model.addAttribute("vlasnikVikendice", korisnik);
 		
 		if(!lozinke.getStaraLozinka().equals(korisnik.getLozinka()))
 		{
 			model.addAttribute("poruka", "Pogrešan unos stare lozinke!");
 			return "/promjenaLozinkeNeuspjesna";
 		}
 		else if(lozinke.getNovaLozinka().equals(lozinke.getNovaPonovo()))
 		{
 			korisnikServis.changePassword(korisnik.getID(), lozinke.getStaraLozinka(), lozinke.getNovaLozinka());
 	  		return "/vikendice/azurirajPodatkeVlasnika";
 		}
 		else 
 		{
 			model.addAttribute("poruka", "Došlo je do greške kod unosa nove lozinke!");
 			return "/promjenaLozinkeNeuspjesna";
 		}
 		
 	  }
	
   @RequestMapping(value ="/napraviVikendicu/{ID}")
   public String napraviVikendicu(Model model, @PathVariable Long ID)
   {
	   System.out.println("Pravljenje vikendice was called!");
	   Korisnik k = korisnikServis.findById(ID);
	   VikendicaDTO vikendica = new VikendicaDTO();
	   model.addAttribute("vikendica", vikendica);
	   model.addAttribute("vlasnikVikendice", k);
	   return "/vikendice/napraviVikendicu";
   }
   
   @RequestMapping(value ="/izmijeniVikendicu/{vlasnikID}/{vikendicaID}")
   public String izmijeniVikendicu(Model model, @PathVariable Long vlasnikID, @PathVariable Long vikendicaID)
   {
	   System.out.println("Izmjena vikendice was called!");
	   Korisnik k = korisnikServis.findById(vlasnikID);
	   Vikendica staraVikendica = vikendicaServis.findById(vikendicaID);
	   System.out.println("Prikaz Slika1: "+staraVikendica.getLinkSlike());
	   System.out.println("Prikaz Slika2: "+staraVikendica.getLinkInterijera());

	   model.addAttribute("slika1", staraVikendica.getLinkSlike());
	   model.addAttribute("slika2", staraVikendica.getLinkInterijera());
	   model.addAttribute("vikendica", staraVikendica);
	   model.addAttribute("vlasnikVikendice", k);
	   
	   return "/vikendice/izmijeniVikendicu.html";
   }
   
   @RequestMapping(value ="/obrisiVikendicu/{vlasnikID}/{vikendicaID}")
   public String obrisiVikendicu(Model model, @PathVariable Long vlasnikID, @PathVariable Long vikendicaID)
   {
	   System.out.println("Brisanje vikendice was called!");
	   Korisnik k = korisnikServis.findById(vlasnikID);
	   Vikendica vikendica = vikendicaServis.findById(vikendicaID);
	   model.addAttribute("vikendica", vikendica);
	   model.addAttribute("vlasnikVikendice", k);
	   return "/vikendice/obrisiVikendicu.html";
   }

   @RequestMapping(value ="/profilMojeVikendice/{vlasnikID}/{vikendicaID}")
   public String profilMojeVikendice(Model model, @PathVariable Long vlasnikID, @PathVariable Long vikendicaID)
   {
	   System.out.println("Profil vikendice was called!");
	   Korisnik k = korisnikServis.findById(vlasnikID);
	   Vikendica staraVikendica = vikendicaServis.findById(vikendicaID);
	   System.out.println("Prikaz Slika1: "+staraVikendica.getLinkSlike());
	   System.out.println("Prikaz Slika2: "+staraVikendica.getLinkInterijera());

	   model.addAttribute("slika1", staraVikendica.getLinkSlike());
	   model.addAttribute("slika2", staraVikendica.getLinkInterijera());
	   model.addAttribute("vikendica", staraVikendica);
	   model.addAttribute("vlasnikVikendice", k);
	   
	   return "/vikendice/profilMojeVikendice.html";
   }
   

   @RequestMapping(value="/moji-klijenti/{vlasnikID}")
   public String mojiKlijenti(Model model, @PathVariable Long vlasnikID)
   {
	   System.out.println("Prikaz mojih klijenata!");
	   Korisnik vlasnik = korisnikServis.findById(vlasnikID);
	   List<KlijentSpisakDTO> mojiKlijenti = rezervacijaServis.nadjiKlijenteVlasnikaVikendice(vlasnik);
	   

	   model.addAttribute("vlasnikVikendice", vlasnik);
	   model.addAttribute("mojiKlijenti", mojiKlijenti);
	   
	   return "/vikendice/klijentiMojihVikendica.html";
	   
   }
   

	@RequestMapping(value = "/izvjestajiPoslovanja/{vlasnikID}")
	public String izvjestajiPoslovanja(Model model, @PathVariable Long vlasnikID) 
	{
		System.out.println("Izvjestaji poslovanja page was called!");
		Korisnik vlasnik = korisnikServis.findById(vlasnikID);
		model.addAttribute("vlasnikVikendice", vlasnik);
		return "/vikendice/izvjestajiOposlovanjuVikendice.html";
	}
	
	@RequestMapping(value = "/izvjestajPoslovanjaPeriod/{vlasnikID}")
	public String izvjestajPoslovanjaPeriod(Model model, @PathVariable Long vlasnikID, PoslovanjeEntitetaDTO poslovanje) 
	{
		System.out.println("Izvjestaji poslovanja period page was called!");
		System.out.println("pocetak: "+poslovanje.getPocetniDatum());
		System.out.println("kraj: "+poslovanje.getKrajnjiDatum());
		Korisnik vlasnik = korisnikServis.findById(vlasnikID);
		model.addAttribute("vlasnikVikendice", vlasnik);
		poslovanje.srediDatume();
		List<PoslovanjeEntitetaDTO> poslovanjaVikendica = vikendicaServis.poslovanjeVikendicaPeriod(poslovanje, vlasnik);
		model.addAttribute("poslovanja", poslovanjaVikendica);
		model.addAttribute("period", poslovanje);
		for(int i = 0; i< poslovanjaVikendica.size(); i++) System.out.println(poslovanjaVikendica.get(i));
		return "/vikendice/izvjestajPoslovanjaPeriod.html";
	}
	
/*
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
