package com.Reservations.Servis.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Reservations.Model.Instruktor;
import com.Reservations.Repository.InstruktorRepo;
import com.Reservations.Servis.InstruktorServis;
@Service
public class InstruktorServisImpl implements InstruktorServis {
	@Autowired
	private InstruktorRepo instruktorrepo;

	@Override
	public List<Instruktor> fetchInstruktorList() {
		// TODO Auto-generated method stub
		return (List<Instruktor>) instruktorrepo.findAll();
	}

}
