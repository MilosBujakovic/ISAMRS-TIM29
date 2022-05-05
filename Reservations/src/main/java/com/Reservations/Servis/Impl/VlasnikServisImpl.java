package com.Reservations.Servis.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Reservations.Model.Vlasnik;
import com.Reservations.Repository.VlasnikRepo;
import com.Reservations.Servis.VlasnikServis;
@Service
public class VlasnikServisImpl implements VlasnikServis {
	@Autowired
	private VlasnikRepo vlasnikrepo;

	@Override
	public List<Vlasnik> fetchVlasnikList() {
		// TODO Auto-generated method stub
		return (List<Vlasnik>) vlasnikrepo.findAll();
	}

}
