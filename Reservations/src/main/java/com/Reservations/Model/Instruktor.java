package com.Reservations.Model;

import java.util.ArrayList;

public class Instruktor extends Korisnik {
	private ArrayList<Usluga> usluge;

	public Instruktor() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Instruktor(String iD, String korisnickoIme, String ime, String prezime, String email, String lozinka,
			String adresa, String grad, String drzava, String brojTel, ArrayList<Usluga> usluge) {
		super(iD, korisnickoIme, ime, prezime, email, lozinka, adresa, grad, drzava, brojTel);
		this.usluge = usluge;
		// TODO Auto-generated constructor stub
	}

	public ArrayList<Usluga> getUsluge() {
		return usluge;
	}

	public void setUsluge(ArrayList<Usluga> usluge) {
		this.usluge = usluge;
	}

	@Override
	public String toString() {
		return "Instruktor [usluge=" + usluge + ", getID()=" + getID() + ", getKorisnickoIme()=" + getKorisnickoIme()
				+ ", getIme()=" + getIme() + ", getPrezime()=" + getPrezime() + ", getEmail()=" + getEmail()
				+ ", getLozinka()=" + getLozinka() + ", getAdresa()=" + getAdresa() + ", getGrad()=" + getGrad()
				+ ", getDrzava()=" + getDrzava() + ", getBrojTel()=" + getBrojTel() + "]";
	}
	
	
}