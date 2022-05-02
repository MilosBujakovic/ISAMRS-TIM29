package com.Reservations.Model;

import java.util.List;

public class Rezervacija {
	private String ID;
	private String nazivEntiteta;
	private String datumVreme;
	private String trajanje;
	private int maxOsoba;
	private List dodatneUsluge;
	private double cena;
	
	public Rezervacija () {
		
	}
	
	public Rezervacija(String iD, String nazivEntiteta, String datumVreme, String trajanje, int maxOsoba,
			List dodatneUsluge, double cena) {
		super();
		ID = iD;
		this.nazivEntiteta = nazivEntiteta;
		this.datumVreme = datumVreme;
		this.trajanje = trajanje;
		this.maxOsoba = maxOsoba;
		this.dodatneUsluge = dodatneUsluge;
		this.cena = cena;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getNazivEntiteta() {
		return nazivEntiteta;
	}

	public void setNazivEntiteta(String nazivEntiteta) {
		this.nazivEntiteta = nazivEntiteta;
	}

	public String getDatumVreme() {
		return datumVreme;
	}

	public void setDatumVreme(String datumVreme) {
		this.datumVreme = datumVreme;
	}

	public String getTrajanje() {
		return trajanje;
	}

	public void setTrajanje(String trajanje) {
		this.trajanje = trajanje;
	}

	public int getMaxOsoba() {
		return maxOsoba;
	}

	public void setMaxOsoba(int maxOsoba) {
		this.maxOsoba = maxOsoba;
	}

	public List getDodatneUsluge() {
		return dodatneUsluge;
	}

	public void setDodatneUsluge(List dodatneUsluge) {
		this.dodatneUsluge = dodatneUsluge;
	}

	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}

	@Override
	public String toString() {
		return "Rezervacija [ID=" + ID + ", nazivEntiteta=" + nazivEntiteta + ", datumVreme=" + datumVreme
				+ ", trajanje=" + trajanje + ", maxOsoba=" + maxOsoba + ", dodatneUsluge=" + dodatneUsluge + ", cena="
				+ cena + "]";
	}
	
	
}
