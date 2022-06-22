package com.Reservations.Servis;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Reservations.DTO.ZalbaDTO;
import com.Reservations.Modeli.Zalba;
import com.Reservations.Repozitorijumi.ZalbeRepozitorijum;
@Service
public class ZalbaServis {
	@Autowired
	private ZalbeRepozitorijum zalbeRepozitorijum;

	@Autowired
	private RezervacijaServis rezser;

	
	public Zalba findById(Long id) {
		try {
			return zalbeRepozitorijum.findById(id).get();
		} catch (NoSuchElementException e) {
			return null;
		}
	}
	
	public Zalba save(ZalbaDTO regRequest) {
		Zalba r=new Zalba();
		
	
		r.setNaziv(regRequest.getNaziv_klijenta());

		r.setRezervacija(rezser.findById(regRequest.getRez_id()));
		r.setZalba(regRequest.getZalba());
	
		
		return this.zalbeRepozitorijum.save(r);// TODO Auto-generated method stub
	}
}
