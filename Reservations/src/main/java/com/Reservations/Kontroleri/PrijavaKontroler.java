package com.Reservations.Kontroleri;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Reservations.Servis.KorisnikServis;



@Controller
public class PrijavaKontroler 
{
	
	@Autowired
	KorisnikServis userService;
		
	@RequestMapping("")
	  public String getWelcomePage(){
		System.out.println("Homepage for unauthenticated users was called!");
	      return "index";
	  }
	
	@RequestMapping(value = "/login")
	  public String getLoginPage(){
		System.out.println("Login page was called!");
	      return "login";
	  }
}