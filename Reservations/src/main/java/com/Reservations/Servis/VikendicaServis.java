package com.Reservations.Servis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Reservations.Modeli.Vikendica;
import com.Reservations.Repozitorijumi.VikendicaRepozitorijum;

@Service	
public class VikendicaServis {
	
	@Autowired
	private VikendicaRepozitorijum vikendicaRepozitorijum;
	public List<Vikendica> listAll(){
		return vikendicaRepozitorijum.findAll();
	}

}