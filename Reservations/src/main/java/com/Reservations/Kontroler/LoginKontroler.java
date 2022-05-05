package com.Reservations.Kontroler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Reservations.Model.Administrator;
import com.Reservations.Servis.AdminServis;
import com.Reservations.Servis.InstruktorServis;
import com.Reservations.Servis.KlijentServis;
import com.Reservations.Servis.VlasnikServis;
@Controller
@RequestMapping("/login")
public class LoginKontroler {
	
	private String username;
	private String password;
	@Autowired
	private AdminServis adminservis;
	@Autowired
	private KlijentServis klijentservis;
	@Autowired
	private VlasnikServis vlasnikservis;
	@Autowired
	private InstruktorServis instruktorservis;
	
	  @RequestMapping(method = RequestMethod.POST)
	  @ResponseBody
	  public String handlePostRequest(String username, String password) {
		  this.username=username;
		  this.password=password;
		  String response;
		  if(adminservis.fetchAdminList()!=null) {
			Administrator admin=adminservis.fetchAdminList().get(0);
			if(admin.getKorisnickoIme().equals(username) && admin.getLozinka().equals(password)) {
				response=admin.toString();
			}else {
				response="nepostojeci korisnik";
			}
		  }else {
			  response="nepostojeci korisnik";
		  }
		  
	     return String.format("%s", response);
	  }
}
