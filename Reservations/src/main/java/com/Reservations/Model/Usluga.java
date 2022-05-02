package com.Reservations.Model;

import java.util.ArrayList;

public class Usluga {
	private String ID;
	private String naziv;
	private String adresa;
	private String opis;
	private String biografijaInstruktora;
	private int maxOsoba;
	private ArrayList<String> pecaroskaOprema;
	private double cena;
	private TipoviUsluga tip;
	
	public Usluga() {
		
	}
	
	public Usluga(String iD, String naziv, String adresa, String opis, String biografijaInstruktora, int maxOsoba,
			ArrayList<String> pecaroskaOprema, double cena) {
		super();
		ID = iD;
		this.naziv = naziv;
		this.adresa = adresa;
		this.opis = opis;
		this.biografijaInstruktora = biografijaInstruktora;
		this.maxOsoba = maxOsoba;
		this.pecaroskaOprema = pecaroskaOprema;
		this.cena = cena;
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

	public String getBiografijaI() {
		return biografijaInstruktora;
	}

	public void setBiografijaI(String biografijaI) {
		this.biografijaInstruktora = biografijaI;
	}

	public int getMaxOsoba() {
		return maxOsoba;
	}

	public void setMaxOsoba(int maxOsoba) {
		this.maxOsoba = maxOsoba;
	}

	public ArrayList<String> getPecaroskaOprema() {
		return pecaroskaOprema;
	}

	public void setPecaroskaOprema(ArrayList<String> pecaroskaOprema) {
		this.pecaroskaOprema = pecaroskaOprema;
	}

	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}

	@Override
	public String toString() {
		return "Usluga [ID=" + ID + ", naziv=" + naziv + ", adresa=" + adresa + ", opis=" + opis + ", biografijaI="
				+ biografijaInstruktora + ", maxOsoba=" + maxOsoba + ", pecaroskaOprema=" + pecaroskaOprema + ", cena=" + cena
				+ "]";
	}
	
	
}
