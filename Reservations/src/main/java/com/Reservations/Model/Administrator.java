package com.Reservations.Model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="Administratori")
public class Administrator extends Korisnik {
	
	public Administrator() {
		
	}

	public Administrator(String iD, String korisnickoIme, String ime, String prezime, String email, String lozinka,
			String adresa, String grad, String drzava, String brojTel) {
		super(iD, korisnickoIme, ime, prezime, email, lozinka, adresa, grad, drzava, brojTel);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Administrator [getID()=" + getID() + ", getKorisnickoIme()=" + getKorisnickoIme() + ", getIme()="
				+ getIme() + ", getPrezime()=" + getPrezime() + ", getEmail()=" + getEmail() + ", getLozinka()="
				+ getLozinka() + ", getAdresa()=" + getAdresa() + ", getGrad()=" + getGrad() + ", getDrzava()="
				+ getDrzava() + ", getBrojTel()=" + getBrojTel() + "]";
	}
	
	
	
}
