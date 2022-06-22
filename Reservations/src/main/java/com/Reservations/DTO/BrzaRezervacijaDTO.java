package com.Reservations.DTO;

import javax.persistence.Column;

public class BrzaRezervacijaDTO {
	private Long id; //entitet ID
	
	private String datum;
	
	private String vreme;
	
	private String trajanje;
	
	private int maxOsoba;
	
	private double cena;
	
	private double akcija;

	public BrzaRezervacijaDTO(Long id, String datum, String vreme, String trajanje, int maxOsoba, double cena,
			double akcija) {
		this.id = id;
		this.datum = datum;
		this.vreme = vreme;
		this.trajanje = trajanje;
		this.maxOsoba = maxOsoba;
		this.cena = cena;
		this.akcija = akcija;
	}

	public BrzaRezervacijaDTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public double getAkcija() {
		return akcija;
	}

	public void setAkcija(double akcija) {
		this.akcija = akcija;
	}

	@Override
	public String toString() {
		return "BrzaRezervacijaDTO [id=" + id + ", datum=" + datum + ", vreme=" + vreme + ", trajanje=" + trajanje
				+ ", maxOsoba=" + maxOsoba + ", cena=" + cena + ", akcija=" + akcija + "]";
	}
	
	public void srediDatume()
	{
		String[] pocetni = this.getDatum().split("-");
		if(pocetni.length==3)
		{
			this.datum = pocetni[1]+"/"+pocetni[2]+"/"+pocetni[0];
			
		}
	}
}
