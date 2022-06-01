package com.Reservations.DTO;

public class RezervacijaDTO {

	private long id;
	private String nazivEntiteta;
	private String datumVreme;
	private String trajanje;
	private int maxOsoba;
	private String dodatneUsluge;
	private double cena;
	public RezervacijaDTO(long id, String nazivEntiteta, String datumVreme, String trajanje, int maxOsoba,
			String dodatneUsluge, double cena) {
		super();
		this.id = id;
		this.nazivEntiteta = nazivEntiteta;
		this.datumVreme = datumVreme;
		this.trajanje = trajanje;
		this.maxOsoba = maxOsoba;
		this.dodatneUsluge = dodatneUsluge;
		this.cena = cena;
	}
	@Override
	public String toString() {
		return "RezervacijaDTO [id=" + id + ", nazivEntiteta=" + nazivEntiteta + ", datumVreme=" + datumVreme
				+ ", trajanje=" + trajanje + ", maxOsoba=" + maxOsoba + ", dodatneUsluge=" + dodatneUsluge + ", cena="
				+ cena + "]";
	}
	
	
	
}
