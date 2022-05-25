package com.Reservations.Servis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Reservations.DTO.ZahtevZaBrisanjeDTO;
import com.Reservations.Modeli.ZahtevZaBrisanje;
import com.Reservations.Repozitorijumi.BrisanjeNalogaRepozitorijum;
@Service
public class BrisanjeNalogaServis {
	@Autowired
	private BrisanjeNalogaRepozitorijum brisanjeRepozitorijum;
	public List<ZahtevZaBrisanje> listAll(){
		return brisanjeRepozitorijum.findAll();
	}
	public ZahtevZaBrisanje Save(ZahtevZaBrisanjeDTO DTO) {
		ZahtevZaBrisanje z=new ZahtevZaBrisanje();
		z.setIme(DTO.getUsername());
		z.setLozinka(DTO.getPassword());
		z.setEmail(DTO.getEmail());
		z.setBrojTel(DTO.getPhone());
		z.setRazlogRegistracije(DTO.getMessage());
		return this.brisanjeRepozitorijum.save(z);
		
	}
}
