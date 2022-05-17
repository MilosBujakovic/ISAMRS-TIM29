package com.Reservations.Kontroleri;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Reservations.Servis.KorisnikServis;
@Controller
public class InstruktoriPonude {
	@Autowired
	KorisnikServis userService;
		
	
	@RequestMapping(value = "/prikazInsturktora")
	  public String getTestPage(){
		System.out.println("PrikazInstruktora page was called!");
	      return "prikazInstruktora";
	  }
}
