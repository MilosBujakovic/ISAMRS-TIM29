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
	
	@ElementCollection
    @CollectionTable(name = "Vikendice")
    @Column(name = "naziviVikendica")
	private ArrayList<Vikendica> vikendice;
	
	@ElementCollection
    @CollectionTable(name = "Brodovi")
    @Column(name = "brojKreveta")
	private ArrayList<Brod> brodovi;
	
	public Vlasnik() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Vlasnik(String iD, String korisnickoIme, String ime, String prezime, String email, String lozinka,
			String adresa, String grad, String drzava, String brojTel, ArrayList<Vikendica> vikendice, ArrayList<Brod> brodovi) {
		super(iD, korisnickoIme, ime, prezime, email, lozinka, adresa, grad, drzava, brojTel);
		this.vikendice = vikendice;
		this.brodovi = brodovi;
		// TODO Auto-generated constructor stub
	}

	public ArrayList<Vikendica> getVikendice() {
		return vikendice;
	}

	public void setVikendice(ArrayList<Vikendica> vikendice) {
		this.vikendice = vikendice;
	}

	public ArrayList<Brod> getBrodovi() {
		return brodovi;
	}

	public void setBrodovi(ArrayList<Brod> brodovi) {
		this.brodovi = brodovi;
	}

	@Override
	public String toString() {
		return "Vlasnik [tip=" + tip + ", vl=" + vl + ", vikendice=" + vikendice + ", brodovi=" + brodovi + ", getID()="
				+ getID() + ", getKorisnickoIme()=" + getKorisnickoIme() + ", getIme()=" + getIme() + ", getPrezime()="
				+ getPrezime() + ", getEmail()=" + getEmail() + ", getLozinka()=" + getLozinka() + ", getAdresa()="
				+ getAdresa() + ", getGrad()=" + getGrad() + ", getDrzava()=" + getDrzava() + ", getBrojTel()="
				+ getBrojTel() + "]";
	}

	
	

	
}
