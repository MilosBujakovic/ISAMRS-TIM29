package com.Reservations.Model;

public class Klijent extends Korisnik {

	public Klijent() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Klijent(String iD, String korisnickoIme, String ime, String prezime, String email, String lozinka,
			String adresa, String grad, String drzava, String brojTel) {
		super(iD, korisnickoIme, ime, prezime, email, lozinka, adresa, grad, drzava, brojTel);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Klijent [getID()=" + getID() + ", getKorisnickoIme()=" + getKorisnickoIme() + ", getIme()=" + getIme()
				+ ", getPrezime()=" + getPrezime() + ", getEmail()=" + getEmail() + ", getLozinka()=" + getLozinka()
				+ ", getAdresa()=" + getAdresa() + ", getGrad()=" + getGrad() + ", getDrzava()=" + getDrzava()
				+ ", getBrojTel()=" + getBrojTel() + ", toString()=" + super.toString() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + "]";
	}
	
	
	
}
