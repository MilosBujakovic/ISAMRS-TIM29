package com.Reservations.Modeli;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name="Rezervacije")
public class Rezervacija {
	
	@Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long ID;
	
	@ManyToOne
	@JoinColumn(name="entitet_id")
	private Vikendica entitet;
	
	@Column(name="nazivEntiteta")
	private String nazivEntiteta;
	
	@Column(name="datum")
	private String datum;
	
	@Column(name="vreme")
	private String vreme;
	
	@Column(name="trajanje")
	private String trajanje;
	
	@Column(name="maxOsoba")
	private int maxOsoba;
	
	
	
	@Column(name="cena")
	private double cena;
	
	public Rezervacija () {
		
	}

	public long getID() {
		return ID;
	}

	public void setID(long iD) {
		ID = iD;
	}

	public String getNazivEntiteta() {
		return nazivEntiteta;
	}

	public void setNazivEntiteta(String nazivEntiteta) {
		this.nazivEntiteta = nazivEntiteta;
	}

	

	public String getDatum() {
		return datum;
	}

	public void setDatum(String datum) {
		this.datum = datum;
	}

	public String getVreme() {
		return vreme;
	}

	public void setVreme(String vreme) {
		this.vreme = vreme;
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

	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}

	@Override
	public String toString() {
		return "Rezervacija [ID=" + ID + ", nazivEntiteta=" + nazivEntiteta + ", datum=" + datum + ", vreme=" + vreme
				+ ", trajanje=" + trajanje + ", maxOsoba=" + maxOsoba + ", cena=" + cena + "]";
	}

	
	
	
}
