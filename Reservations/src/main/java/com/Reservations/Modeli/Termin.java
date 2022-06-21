

package com.Reservations.Modeli;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.Reservations.Modeli.enums.TipEntiteta;
@Entity
@Table(name="Termin")
public class Termin {

	@Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long ID;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="vikendica_id")
	private Vikendica vikendica;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="brod_id")
	private Brod brod;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="usluga_id")
	private Usluga usluga;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="vlasnik_id")
	private Korisnik vlasnik;
	
	@Column(name="tip_entiteta")
	private TipEntiteta tipEntiteta;
	
	@Column(name="datumVremePocetak")
	private String datumVremePocetak;
	
	@Column(name="datumVremeKraj")
	private String datumVremeKraj;

	public Termin() {
	}


	public Termin(long iD, Vikendica vikendica, Brod brod, Usluga usluga, Korisnik vlasnik, TipEntiteta tipEntiteta,
			String datumVremePocetak, String datumVremeKraj) {
		ID = iD;
		this.vikendica = vikendica;
		this.brod = brod;
		this.usluga = usluga;
		this.vlasnik = vlasnik;
		this.tipEntiteta = tipEntiteta;
		this.datumVremePocetak = datumVremePocetak;
		this.datumVremeKraj = datumVremeKraj;
	}


	public long getID() {
		return ID;
	}

	public void setID(long iD) {
		ID = iD;
	}

	public Vikendica getVikendica() {
		return vikendica;
	}


	public void setVikendica(Vikendica vikendica) {
		this.vikendica = vikendica;
	}


	public Brod getBrod() {
		return brod;
	}


	public void setBrod(Brod brod) {
		this.brod = brod;
	}


	public Usluga getUsluga() {
		return usluga;
	}


	public void setUsluga(Usluga usluga) {
		this.usluga = usluga;
	}


	public TipEntiteta getTipEntiteta() {
		return tipEntiteta;
	}

	public void setTipEntiteta(TipEntiteta tipEntiteta) {
		this.tipEntiteta = tipEntiteta;
	}

	public String getDatumVremePocetak() {
		return datumVremePocetak;
	}

	public void setDatumVremePocetak(String datumVremePocetak) {
		this.datumVremePocetak = datumVremePocetak;
	}

	public String getDatumVremeKraj() {
		return datumVremeKraj;
	}

	public void setDatumVremeKraj(String datumVremeKraj) {
		this.datumVremeKraj = datumVremeKraj;
	}

	public Korisnik getVlasnik() {
		return vlasnik;
	}

	public void setVlasnik(Korisnik vlasnik) {
		this.vlasnik = vlasnik;
	}

	@Override
	public String toString() {
		return "Termin [ID=" + ID + ", vikendica=" + vikendica + ", brod=" + brod + ", usluga=" + usluga + ", vlasnik="
				+ vlasnik + ", tipEntiteta=" + tipEntiteta + ", datumVremePocetak=" + datumVremePocetak
				+ ", datumVremeKraj=" + datumVremeKraj + "]";
	}
	
	
}
