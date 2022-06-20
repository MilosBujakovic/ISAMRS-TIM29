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

import com.Reservations.Modeli.enums.TipEntiteta;
import com.Reservations.Modeli.enums.TipRezervacije;
@Entity
@Table(name="Rezervacije")
public class Rezervacija {
	
	@Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long ID;

	@Column(name="entitet_id")
	private long entitetId;
	
	@Column(name="tip_entiteta")
	private TipEntiteta tipEntiteta;
	
	

	
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
	
	@ManyToOne
	@JoinColumn(name="klijent_id")
	private Korisnik klijent;
	
	@Column(name="tip")
	private TipRezervacije tip;
	
	@Column(name="izvestaj")
	private String izvestaj;
	
	public Rezervacija () {
		
	}

	public long getID() {
		return ID;
	}

	public void setID(long iD) {
		ID = iD;
	}
	
	

	public long getEntitetId() {
		return entitetId;
	}

	public void setEntitetId(long entitetId) {
		this.entitetId = entitetId;
	}

	public TipEntiteta getTipEntiteta() {
		return tipEntiteta;
	}

	public void setTipEntiteta(TipEntiteta tipEntiteta) {
		this.tipEntiteta = tipEntiteta;
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

	public TipRezervacije getTip() {
		return tip;
	}

	public void setTip(TipRezervacije tip) {
		this.tip = tip;
	}

	public Korisnik getKlijent() {
		return klijent;
	}

	public void setKlijent(Korisnik klijent) {
		this.klijent = klijent;
	}

	public String getIzvestaj() {
		return izvestaj;
	}

	public void setIzvestaj(String izvestaj) {
		this.izvestaj = izvestaj;
	}

	@Override
	public String toString() {
		return "Rezervacija [ID=" + ID + ", nazivEntiteta=" + nazivEntiteta + ", datum=" + datum + ", vreme=" + vreme
				+ ", trajanje=" + trajanje + ", maxOsoba=" + maxOsoba + ", cena=" + cena + ", izvestaj=" + izvestaj +"]";
	}

	
	
	
}
