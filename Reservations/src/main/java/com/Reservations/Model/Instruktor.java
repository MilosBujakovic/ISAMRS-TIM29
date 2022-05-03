package com.Reservations.Model;

import java.util.ArrayList;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
@Entity
@Table(name="Instruktor")
public class Instruktor extends Korisnik {

	public Instruktor() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Instruktor(String iD, String korisnickoIme, String ime, String prezime, String email, String lozinka,
			String adresa, String grad, String drzava, String brojTel) {
		super(iD, korisnickoIme, ime, prezime, email, lozinka, adresa, grad, drzava, brojTel);
		
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Instruktor [getID()=" + getID() + ", getKorisnickoIme()=" + getKorisnickoIme()
				+ ", getIme()=" + getIme() + ", getPrezime()=" + getPrezime() + ", getEmail()=" + getEmail()
				+ ", getLozinka()=" + getLozinka() + ", getAdresa()=" + getAdresa() + ", getGrad()=" + getGrad()
				+ ", getDrzava()=" + getDrzava() + ", getBrojTel()=" + getBrojTel() + "]";
	}
	
	
}
