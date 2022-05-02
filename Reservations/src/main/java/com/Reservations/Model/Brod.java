package com.Reservations.Model;

import java.util.ArrayList;

public class Brod {
	private String ID;
	private String naziv;
	private String tip;
	private double duzina;
	private String brojMotora;
	private int snaga;
	private double maxBrzina;
	private String adresa;
	private String opis;
	private double cena;
	private ArrayList<String> navigacionaOprema;
	private String kapacitet;
	private ArrayList<String> pecaroskaOprema;
	
	public Brod() {
		
	}
	
	public Brod(String iD, String naziv, String tip, double duzina, String brojMotora, int snaga, double maxBrzina,
			String adresa, String opis, double cena, ArrayList<String> navigacionaOprema, String kapacitet, ArrayList<String> pecaroskaOprema) {
		super();
		ID = iD;
		this.naziv = naziv;
		this.tip = tip;
		this.duzina = duzina;
		this.brojMotora = brojMotora;
		this.snaga = snaga;
		this.maxBrzina = maxBrzina;
		this.adresa = adresa;
		this.opis = opis;
		this.cena = cena;
		this.navigacionaOprema = navigacionaOprema;
		this.kapacitet = kapacitet;
		this.pecaroskaOprema = pecaroskaOprema;
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

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public double getDuzina() {
		return duzina;
	}

	public void setDuzina(double duzina) {
		this.duzina = duzina;
	}

	public String getBrojMotora() {
		return brojMotora;
	}

	public void setBrojMotora(String brojMotora) {
		this.brojMotora = brojMotora;
	}

	public int getSnaga() {
		return snaga;
	}

	public void setSnaga(int snaga) {
		this.snaga = snaga;
	}

	public double getMaxBrzina() {
		return maxBrzina;
	}

	public void setMaxBrzina(double maxBrzina) {
		this.maxBrzina = maxBrzina;
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

	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}

	public ArrayList<String> getNavigacionaOprema() {
		return navigacionaOprema;
	}

	public void setNavigacionaOprema(ArrayList<String> navigacionaOprema) {
		this.navigacionaOprema = navigacionaOprema;
	}

	public String getKapacitet() {
		return kapacitet;
	}

	public void setKapacitet(String kapacitet) {
		this.kapacitet = kapacitet;
	}

	public ArrayList<String> getPecaroskaOprema() {
		return pecaroskaOprema;
	}

	public void setPecaroskaOprema(ArrayList<String> pecaroskaOprema) {
		this.pecaroskaOprema = pecaroskaOprema;
	}

	@Override
	public String toString() {
		return "Brod [ID=" + ID + ", naziv=" + naziv + ", tip=" + tip + ", duzina=" + duzina + ", brojMotora="
				+ brojMotora + ", snaga=" + snaga + ", maxBrzina=" + maxBrzina + ", adresa=" + adresa + ", opis=" + opis
				+ ", cena=" + cena + ", navigacionaOprema=" + navigacionaOprema + ", kapacitet=" + kapacitet
				+ ", pecaroskaOprema=" + pecaroskaOprema + "]";
	}

	
	
	
}
