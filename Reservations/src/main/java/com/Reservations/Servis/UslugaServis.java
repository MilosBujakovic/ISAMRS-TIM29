package com.Reservations.Servis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Reservations.Repozitorijumi.UslugaRepozitorijum;

@Service
public class UslugaServis
{

	@Autowired
	private UslugaRepozitorijum uslugaRepozitorijum;

}