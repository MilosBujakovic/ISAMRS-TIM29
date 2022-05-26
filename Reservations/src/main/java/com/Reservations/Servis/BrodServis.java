package com.Reservations.Servis;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Reservations.Modeli.Brod;
import com.Reservations.Modeli.Usluga;
import com.Reservations.Repozitorijumi.BrodRepozitorijum;

@Service
public class BrodServis 
{
	@Autowired
	private BrodRepozitorijum brodRepozitorijum;
	public List<Brod> listAll(){
		return brodRepozitorijum.findAll();
	}

	public Brod findById(Long id){
		try {
			return brodRepozitorijum.findById(id).get();
		} catch (NoSuchElementException e) {
			return null;
		}
	}
	
	public List<Brod> findByVlasnik(long id) {
		List<Brod> lista = brodRepozitorijum.findAll();
		for(Brod u : lista) {
			if (u.getVlasnik().getID() != id) lista.remove(u);
		}
		return lista;
	}
}
