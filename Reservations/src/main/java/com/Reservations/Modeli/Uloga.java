package com.Reservations.Modeli;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Uloga")
public class Uloga {
	@Id
	@Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name="ime")
	private String ime;
	
	public Uloga() {
		
	}
	
	public Uloga(long id, String ime) {
		super();
		this.id = id;
		this.ime = ime;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	
	
	

}
