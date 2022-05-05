package com.Reservations.Servis.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Reservations.Model.Administrator;
import com.Reservations.Model.Klijent;
import com.Reservations.Repository.KlijentRepo;
import com.Reservations.Servis.KlijentServis;
@Service
public class KlijentServisImpl implements KlijentServis {
	@Autowired
	private KlijentRepo klijentrepo;

	@Override
	public List<Klijent> fetchKlijentList() {
		// TODO Auto-generated method stub
		return (List<Klijent>) klijentrepo.findAll();
	}

}
