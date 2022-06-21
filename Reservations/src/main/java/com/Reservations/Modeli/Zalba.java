package com.Reservations.Modeli;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="Zalba")
public class Zalba {
	
	@Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;
	
	@Column(name="naziv_klijenta")
    private String naziv;
	
	@Column(name="zalba")
    private String zalba;

	public Zalba(long iD, String naziv, String zalba) {
		super();
		ID = iD;
		this.naziv = naziv;
		this.zalba = zalba;
	}

	public long getID() {
		return ID;
	}

	public void setID(long iD) {
		ID = iD;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getZalba() {
		return zalba;
	}

	public void setZalba(String zalba) {
		this.zalba = zalba;
	}

	@Override
	public String toString() {
		return "Zalba [ID=" + ID + ", naziv=" + naziv + ", zalba=" + zalba + "]";
	}
	
	
	

}
