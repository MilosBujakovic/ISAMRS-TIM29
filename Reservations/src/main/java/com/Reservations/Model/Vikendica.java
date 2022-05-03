package com.Reservations.Model;

import java.util.ArrayList;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="Vikendica")
public class Vikendica {
	@Id
	@Column(name="id")
	private String ID;
	
	
	@Column(name="naziv")
	private String naziv;
	
	
	@Column(name="adresa")
	private String adresa;
	
	
	@Column(name="opis")
	private String opis;
	
	@ElementCollection
    @CollectionTable(name = "ListaSoba")
    @Column(name = "brojKreveta")
	private ArrayList<Integer> listaSoba;
	
	@Column(name="cena")
	private double cena;
	
	public Vikendica() {
		this.listaSoba = new ArrayList<Integer>();
	}
	
	public Vikendica(String iD, String naziv, String adresa, String opis, ArrayList<Integer> listaSoba) {
		super();
		ID = iD;
		this.naziv = naziv;
		this.adresa = adresa;
		this.opis = opis;
		this.listaSoba = listaSoba;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
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

	public ArrayList<Integer> getListaSoba() {
		return listaSoba;
	}

	public void setListaSoba(ArrayList<Integer> listaSoba) {
		this.listaSoba = listaSoba;
	}

	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}

	@Override
	public String toString() {
		return "Vikendica [ID=" + ID + ", naziv=" + naziv + ", adresa=" + adresa + ", opis=" + opis + ", listaSoba="
				+ listaSoba + ", cena=" + cena + "]";
	}


	
	
}
