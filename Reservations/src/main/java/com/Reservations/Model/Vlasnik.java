package com.Reservations.Model;

import java.util.ArrayList;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="Vlasnici")
public class Vlasnik extends Korisnik{
	

	@Column(name="tip")
	private String tip;
	
	@Enumerated(EnumType.STRING)
	Vlasnici vl;
	
	public Vlasnik() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Vlasnik(String iD, String korisnickoIme, String ime, String prezime, String email, String lozinka,
			String adresa, String grad, String drzava, String brojTel) {
		super(iD, korisnickoIme, ime, prezime, email, lozinka, adresa, grad, drzava, brojTel);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Vlasnik [tip=" + tip + ", vl=" + vl + ", getID()="
				+ getID() + ", getKorisnickoIme()=" + getKorisnickoIme() + ", getIme()=" + getIme() + ", getPrezime()="
				+ getPrezime() + ", getEmail()=" + getEmail() + ", getLozinka()=" + getLozinka() + ", getAdresa()="
				+ getAdresa() + ", getGrad()=" + getGrad() + ", getDrzava()=" + getDrzava() + ", getBrojTel()="
				+ getBrojTel() + "]";
	}

	
	

	
}
