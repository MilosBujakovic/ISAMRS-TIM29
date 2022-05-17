package com.Reservations.Servis;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.Reservations.Modeli.Prihod;
import com.Reservations.Repozitorijumi.PrihodRepozitorijum;

@Service
@Transactional
public class PrihodServis {

	@Autowired
	PrihodRepozitorijum prihodRepo;
	
	public List<Prihod> listAll() {
        return prihodRepo.findAll(Sort.by("datum").ascending());
    }
}
