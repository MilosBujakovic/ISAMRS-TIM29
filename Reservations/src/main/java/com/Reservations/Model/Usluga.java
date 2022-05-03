package com.Reservations.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Usluge")
public class Usluga {
	@Id
	@Column(name="id")
	private String ID;
	
	@Column(name="naziv")
	private String naziv;
	
	@Column(name="adresa")
	private String adresa;
	
	@Column(name="opis")
	private String opis;
	
	@Column(name="biografijaInstruktora")
	private String biografijaInstruktora;
	
	@Column(name="maxOsoba")
	private int maxOsoba;
	
	@Column(name="pecaroskaOprema")
	private String pecaroskaOprema;
	
	@Column(name="cena")
	private double cena;
	
	@Enumerated(EnumType.STRING)
	private TipoviUsluga tip;
	
	@Column(name="instruktor_id")
	private String instruktorID;
	
	public Usluga() {
		
	}
	
	public Usluga(String iD, String naziv, String adresa, String opis, String biografijaInstruktora, int maxOsoba,
			String pecaroskaOprema, double cena, String instruktorID) {
		super();
		ID = iD;
		this.naziv = naziv;
		this.adresa = adresa;
		this.opis = opis;
		this.biografijaInstruktora = biografijaInstruktora;
		this.maxOsoba = maxOsoba;
		this.pecaroskaOprema = pecaroskaOprema;
		this.cena = cena;
		this.instruktorID = instruktorID;
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
	
	public String getBiografijaInstruktora() {
		return biografijaInstruktora;
	}

	public void setBiografijaInstruktora(String biografijaInstruktora) {
		this.biografijaInstruktora = biografijaInstruktora;
	}

	public int getMaxOsoba() {
		return maxOsoba;
	}

	public void setMaxOsoba(int maxOsoba) {
		this.maxOsoba = maxOsoba;
	}

	public String getPecaroskaOprema() {
		return pecaroskaOprema;
	}

	public void setPecaroskaOprema(String pecaroskaOprema) {
		this.pecaroskaOprema = pecaroskaOprema;
	}

	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}
	

	public String getInstruktorID() {
		return instruktorID;
	}

	public void setInstruktorID(String instruktorID) {
		this.instruktorID = instruktorID;
	}

	@Override
	public String toString() {
		return "Usluga [ID=" + ID + ", naziv=" + naziv + ", adresa=" + adresa + ", opis=" + opis
				+ ", biografijaInstruktora=" + biografijaInstruktora + ", maxOsoba=" + maxOsoba + ", pecaroskaOprema="
				+ pecaroskaOprema + ", cena=" + cena + ", tip=" + tip + ", instruktorID=" + instruktorID + "]";
	}

	
	
}
