package com.Reservations.Kontroleri;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Reservations.DTO.RegistracijaVlasnikaInstruktoraDTO;
import com.Reservations.Exception.ResourceConflictException;
import com.Reservations.Modeli.Korisnik;
import com.Reservations.Modeli.Registracija;
import com.Reservations.Servis.KorisnikServis;
import com.Reservations.Servis.RegistracijaServis;

@Controller
public class RegistracijaKontroler {
	
	@Autowired
	KorisnikServis korisnikServis;
	
	@Autowired
	RegistracijaServis regServis;
	
	@RequestMapping(value = "/register-owner")
	  public String getRegisterOwnerPage(){
		System.out.println("Register owner page was called!");
	      return "registerOwner";
	  }
	
	@RequestMapping(value = "/register-client")
	  public String getRegisterClientPage(){
		System.out.println("Register client page was called!");
	      return "registerClient";
	  }
	
	@RequestMapping(value = "/request-sent", consumes =  MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	  public String registerOwner(@RequestBody RegistracijaVlasnikaInstruktoraDTO regRequest) {
		long id = 0;
		for (long i = 1; i < Long.MAX_VALUE; i++)
		{
			Registracija check = this.regServis.findById(i);
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
		regRequest.setId(id);
		System.out.println("Owner registration request sent!");
		Korisnik existKorisnik = this.korisnikServis.findByUsername(regRequest.getUsername());
		if (existKorisnik != null) {
			throw new ResourceConflictException(regRequest.getId(), "Username already exists");
		}
		this.regServis.save(regRequest);
		return "registerRequest"; 
	  }
	
	@RequestMapping(value = "/verify")
	  public String registerClient(HttpServletRequest request) throws IOException {
		
//		long id = 0;
//		for (long i = 1; i < Long.MAX_VALUE; i++)
//		{
//			Korisnik check = korisnikServis.findById(i);
//			if (check != null)
//			{
//				continue;
//			}
//			else
//			{
//				id = i;
//				break;
//			}
//		}
//		userRequest.setId(id);
//		System.out.println("Client registration in progress!");
//		Korisnik existUser = korisnikServis.findByUsername(userRequest.getUsername());
//		if (existKorisnik != null) {
//			throw new ResourceConflictException(userRequest.getId(), "Username already exists");
//		}
//
//		try 
//		{
//			this.korisnikServis.save(userRequest);
//		}
//		catch (Exception e)
//		{
//			System.out.println("Registration failure!");
//			return "registerFailure";
//		}
		System.out.println("Registration successful!");
		
		return "registerSuccess"; 
	  }
	
}
