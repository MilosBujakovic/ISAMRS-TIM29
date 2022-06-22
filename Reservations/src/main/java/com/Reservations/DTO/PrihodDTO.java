package com.Reservations.DTO;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.Reservations.Modeli.Rezervacija;

public class PrihodDTO {

	private double vrednost;

	private Rezervacija rezervacija;

	public PrihodDTO(double vrednost, Rezervacija rezervacija) {
		this.vrednost = vrednost;
		this.rezervacija = rezervacija;
	}

	public PrihodDTO() {
	}

	public double getVrednost() {
		return vrednost;
	}

	public void setVrednost(double vrednost) {
		this.vrednost = vrednost;
	}

	public Rezervacija getRezervacija() {
		return rezervacija;
	}

	public void setRezervacija(Rezervacija rezervacija) {
		this.rezervacija = rezervacija;
	}

	@Override
	public String toString() {
		return "PrihodDTO [vrednost=" + vrednost + ", rezervacija=" + rezervacija + "]";
	}
	
	
}
