package com.Reservations.Modeli;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "prihodi")
public class Prihod {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long ID;

	@Column(name = "vrednost")
	private double vrednost;

	@OneToOne
	@JoinColumn(name = "rezervacija_id")
	private Rezervacija rezervacija;

	@Column(name = "datum")
	private Date datum;

	public Prihod(long iD, double vrednost, Rezervacija rezervacija, Date datum) {
		super();
		ID = iD;
		this.vrednost = vrednost;
		this.rezervacija = rezervacija;
		this.datum = datum;
	}

	public long getID() {
		return ID;
	}

	public void setID(long iD) {
		ID = iD;
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

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	@Override
	public String toString() {
		return "Prihod [ID=" + ID + ", vrednost=" + vrednost + ", rezervacija=" + rezervacija + ", datum=" + datum
				+ "]";
	}

}
