package com.Reservations.Servis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Reservations.Modeli.Brod;
import com.Reservations.Repozitorijumi.BrodRepozitorijum;

@Service
public class BrodServis 
{
	@Autowired
	private BrodRepozitorijum brodRepozitorijum;
	public List<Brod> listAll(){
		return brodRepozitorijum.findAll();
	}

}
