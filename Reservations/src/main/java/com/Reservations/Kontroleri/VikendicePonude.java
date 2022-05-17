package com.Reservations.Kontroleri;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Reservations.Servis.KorisnikServis;

@Controller
public class VikendicePonude {
	@Autowired
	KorisnikServis userService;
		
	
	@RequestMapping(value = "/prikazVikendica")
	  public String getTestPage(){
		System.out.println("PrikazVikendica page was called!");
	      return "prikazVikendica";
	  }
}
