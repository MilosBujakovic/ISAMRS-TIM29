package com.Reservations.Modeli;

import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name="Vikendica")
public class Vikendica {
	@Id
	
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long ID;
	
	
	@Column(name="naziv")
	private String naziv;
	
	
	@Column(name="adresa")
	private String adresa;
	
	
	@Column(name="opis")
	private String opis;

	@Column(name = "broj_soba")
	private int brojSoba;
	
    @Column(name = "broj_kreveta")
	private int brojKreveta;
	
	@Column(name="cena")
	private double cena;
	
	@ManyToOne
	@JoinColumn(name="vlasnik_id")
	private Korisnik vlasnik;
	
	public Vikendica() {

	}
	
	public Vikendica(long iD, String naziv, String adresa, String opis, int brojSoba, int brojKreveta, Korisnik vlasnik) {
		super();
		ID = iD;
		this.naziv = naziv;
		this.adresa = adresa;
		this.opis = opis;
		this.brojSoba = brojSoba;
		this.brojKreveta = brojKreveta;
		this.vlasnik = vlasnik;
	}

	public long getID() {
		return ID;
	}

	public void setID(long iD) {
		ID = iD;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}


	public int getBrojSoba() {
		return brojSoba;
	}

	public void setBrojSoba(int brojSoba) {
		this.brojSoba = brojSoba;
	}

	public int getBrojKreveta() {
		return brojKreveta;
	}

	public void setBrojKreveta(int brojKreveta) {
		this.brojKreveta = brojKreveta;
	}

	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}
	
	

	public Korisnik getVlasnik() {
		return vlasnik;
	}

	public void setVlasnik(Korisnik vlasnik) {
		this.vlasnik = vlasnik;
	}

	@Override
	public String toString() {
		return "Vikendica [ID=" + ID + ", naziv=" + naziv + ", adresa=" + adresa + ", opis=" + opis + ", brojSoba="
				+ brojSoba + ", brojKreveta=" + brojKreveta + ", cena=" + cena + ", vlasnik=" + vlasnik.toString() + "]";
	}


	
	
}
