package com.Reservations.DTO;

public class RezervacijaDTO {

	private long id;
	private String nazivEntiteta;
	private String datum;
	private String vreme;
	private String trajanje;
	private int maxOsoba;
	private String dodatneUsluge;
	private double cena;
	public RezervacijaDTO(long id, String nazivEntiteta, String datum, String vreme, String trajanje, int maxOsoba,
			String dodatneUsluge, double cena) {
		super();
		this.id = id;
		this.nazivEntiteta = nazivEntiteta;
		this.datum = datum;
		this.vreme = vreme;
		this.trajanje = trajanje;
		this.maxOsoba = maxOsoba;
		this.dodatneUsluge = dodatneUsluge;
		this.cena = cena;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
		return "RezervacijaDTO [id=" + id + ", nazivEntiteta=" + nazivEntiteta + ", datum=" + datum + ", vreme=" + vreme
				+ ", trajanje=" + trajanje + ", maxOsoba=" + maxOsoba + ", dodatneUsluge=" + dodatneUsluge + ", cena="
				+ cena + "]";
	}

	
	
	
	
	
}
