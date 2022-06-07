package com.Reservations.Modeli;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.Reservations.Modeli.enums.TipEntiteta;
@Entity
@Table(name="TerminDostupnosti")
public class TerminDostupnosti {

	@Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long ID;
	
	@Column(name="entitet_id")
	private long entitetId;
	
	@Column(name="tip_entiteta")
	private TipEntiteta tipEntiteta;
	
	@Column(name="datumVremePocetak")
	private String datumVremePocetak;
	
	@Column(name="datumVremeKraj")
	private String datumVremeKraj;
	
	@Column(name="trajanje")
	private String trajanje;

	public TerminDostupnosti() {
	}

	public TerminDostupnosti(long iD, long entitetId, TipEntiteta tip, String datumVremePocetak, String datumVremeKraj, String trajanje) {
		ID = iD;
		this.entitetId = entitetId;
		tipEntiteta = tip;
		this.datumVremePocetak = datumVremePocetak;
		this.datumVremeKraj = datumVremeKraj;
		this.trajanje = trajanje;
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

	public String getTrajanje() {
		return trajanje;
	}

	public void setTrajanje(String trajanje) {
		this.trajanje = trajanje;
	}

	@Override
	public String toString() {
		return "TerminDostupnosti [ID=" + ID + ", entitetId=" + entitetId + ", tipEntiteta=" + tipEntiteta
				+ ", datumVremePocetak=" + datumVremePocetak + ", datumVremeKraj=" + datumVremeKraj + ", trajanje="
				+ trajanje + "]";
	}
	
	
}
