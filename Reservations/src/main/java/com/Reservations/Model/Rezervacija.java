package com.Reservations.Model;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Table;
@Entity
@Table(name="Rezervacije")
public class Rezervacija {
	@Column(name="id")
	private String ID;
	
	@Column(name="nazivEntiteta")
	private String nazivEntiteta;
	
	@Column(name="datumVreme")
	private String datumVreme;
	
	@Column(name="trajanje")
	private String trajanje;
	
	@Column(name="maxOsoba")
	private int maxOsoba;
	
	@Column(name="dodatneUsluge")
	private String dodatneUsluge;
	
	@Column(name="cena")
	private double cena;
	
	public Rezervacija () {
		
	}
	
	public Rezervacija(String iD, String nazivEntiteta, String datumVreme, String trajanje, int maxOsoba,
			String dodatneUsluge, double cena) {
		super();
		ID = iD;
		this.nazivEntiteta = nazivEntiteta;
		this.datumVreme = datumVreme;
		this.trajanje = trajanje;
		this.maxOsoba = maxOsoba;
		this.dodatneUsluge=dodatneUsluge;
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

	public String getDodatneUsluge() {
		return dodatneUsluge;
	}

	public void setDodatneUsluge(String dodatneUsluge) {
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
