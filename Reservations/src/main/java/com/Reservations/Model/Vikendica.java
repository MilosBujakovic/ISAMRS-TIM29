package com.Reservations.Model;

import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
	
	@ElementCollection
    @CollectionTable(name = "ListaSoba", joinColumns=@JoinColumn(name="soba_id"))
    @Column(name = "brojKreveta")
	public Set<Integer> listaSoba;
	
	@Column(name="cena")
	private double cena;
	
	@Column(name="vlasnik")
	private Korisnik vlasnik;
	
	public Vikendica() {

	}
	
	public Vikendica(long iD, String naziv, String adresa, String opis, Set<Integer> listaSoba, Korisnik vlasnik) {
		super();
		ID = iD;
		this.naziv = naziv;
		this.adresa = adresa;
		this.opis = opis;
		this.listaSoba = listaSoba;
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

	public Set<Integer> getListaSoba() {
		return listaSoba;
	}

	public void setListaSoba(Set<Integer> listaSoba) {
		this.listaSoba = listaSoba;
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
		return "Vikendica [ID=" + ID + ", naziv=" + naziv + ", adresa=" + adresa + ", opis=" + opis + ", listaSoba="
				+ listaSoba + ", cena=" + cena + ", vlasnikID=" + vlasnik + "]";
	}


	
	
}
