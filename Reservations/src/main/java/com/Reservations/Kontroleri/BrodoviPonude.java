package com.Reservations.Kontroleri;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Reservations.Modeli.Brod;
import com.Reservations.Servis.BrodServis;
@Controller
public class BrodoviPonude {
	@Autowired
	BrodServis brodServis;
		
	
	@RequestMapping(value = "/prikazBrodova")
	  public String getTestPage(Model model){
		System.out.println("PrikazBrodova page was called!");
		List<Brod>brodovi=brodServis.listAll();
		model.addAttribute("brodovi", brodovi);
		System.out.println(model.toString());
	      return "prikazBrodova";
	  }
}
