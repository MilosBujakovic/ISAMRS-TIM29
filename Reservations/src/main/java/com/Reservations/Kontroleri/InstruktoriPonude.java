package com.Reservations.Kontroleri;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Reservations.Modeli.Usluga;
import com.Reservations.Servis.UslugaServis;
@Controller
public class InstruktoriPonude {
	@Autowired
	UslugaServis uslugaServis;
		
	
	@RequestMapping(value = "/prikazInstruktora")
	  public String getTestPage(Model model){
		System.out.println("PrikazInstruktora page was called!");
		List<Usluga>usluga=uslugaServis.listAll();
		model.addAttribute("usluga", usluga);
		System.out.println(model.toString());
	      return "prikazInstruktora";
	  }
	
	@RequestMapping(value = "/OsnovniprikazInstruktora")
	  public String getOsnPage(Model model){
		System.out.println("OsnovniPrikazInstruktora page was called!");
		List<Usluga>usluga=uslugaServis.listAll();
		model.addAttribute("usluga", usluga);
		System.out.println(model.toString());
	      return "OsnovniPodaciInstruktora";
	  }
}
