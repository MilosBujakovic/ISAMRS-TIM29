package com.Reservations.Kontroleri;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Reservations.Modeli.Vikendica;
import com.Reservations.Servis.VikendicaServis;

@Controller
public class VikendicePonude {
	@Autowired
	VikendicaServis vikendicaServis;
		
	
	
	
	@RequestMapping(value = "/prikazVikendica")
	  public String getTestPage(Model model){
		System.out.println("PrikazVikendica page was called!");
		List<Vikendica>vikendice=vikendicaServis.listAll();
		model.addAttribute("vikendice", vikendice);
		System.out.println(model.toString());
	      return "prikazVikendica";
	  }
}
