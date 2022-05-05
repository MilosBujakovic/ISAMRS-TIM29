package com.Reservations.Servis.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Reservations.Model.Administrator;
import com.Reservations.Repository.AdminRepo;
import com.Reservations.Servis.AdminServis;
@Service
public class AdminServisImpl implements AdminServis {

	@Autowired
	private AdminRepo adminrepo;
	@Override
	public List<Administrator> fetchAdminList() {
		// TODO Auto-generated method stub
		return (List<Administrator>) adminrepo.findAll();
	}

}
