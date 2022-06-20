package com.Reservations.Servis;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.Reservations.Modeli.Brod;
import com.Reservations.Modeli.Rezervacija;
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
	
	public List<Brod>BrodSortCena(){
		//List<Brod>li2=new ArrayList<Brod>();
		List<Brod>li=brodRepozitorijum.findAll(Sort.by(Sort.Direction.DESC, "cena"));
		System.out.println(li.toString());
		return li;
		}
	
	public List<Brod>BrodSortNaziv(){
		//List<Brod>li2=new ArrayList<Brod>();
		List<Brod>li=brodRepozitorijum.findAll(Sort.by(Sort.Direction.ASC, "tip"));
		System.out.println(li.toString());
		return li;
		}
	
	public List<Brod>BrodSortAdresa(){
		//List<Brod>li2=new ArrayList<Brod>();
		List<Brod>li=brodRepozitorijum.findAll(Sort.by(Sort.Direction.ASC, "adresa"));
		System.out.println(li.toString());
		return li;
		}
	
	public List<Brod>BrodPretraga(String pretraga){
		//List<Brod>li2=new ArrayList<Brod>();
		List<Brod>li=new ArrayList<Brod>();
		List<Brod>li2=brodRepozitorijum.findAll();
		for (Brod e : li2) {
			if(e.getAdresa().toLowerCase().contains(pretraga.toLowerCase())) {
				li.add(e);
				continue;
			}
			if(e.getTip().toLowerCase().contains(pretraga.toLowerCase())) {
				li.add(e);
				continue;
			}
			if(String.valueOf(e.getCena()).contains(pretraga)) {
				li.add(e);
				continue;
			}
		}
		return li;
		}
	
	public List<Brod>BrodFilter(String tip){
		//List<Brod>li2=new ArrayList<Brod>();
		
		List<Brod>li2=brodRepozitorijum.findAll();
	    List<Brod>li=new ArrayList<Brod>();
		 for (Brod brod : li2) {
			if(brod.getTip().equals(tip)) {
				li.add(brod);
			}
		}
		 return li;
		}

}
