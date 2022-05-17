package com.Reservations.Kontroleri;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Reservations.Servis.KorisnikServis;
@Controller
public class BrodoviPonude {
	@Autowired
	KorisnikServis userService;
		
	
	@RequestMapping(value = "/prikazBrodova")
	  public String getTestPage(){
		System.out.println("PrikazBrodova page was called!");
	      return "prikazBrodova";
	  }
}
