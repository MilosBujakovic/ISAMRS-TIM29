package com.Reservations.Model;

public abstract class Korisnik {
	private String ID;
	private String korisnickoIme;
	private String ime;
	private String prezime;
	private String email;
	private String lozinka;
	private String adresa;
	private String grad;
	private String drzava;
	private String brojTel;
	
	public Korisnik()
	{}
	
	
	public Korisnik(String iD, String korisnickoIme, String ime, String prezime, String email, String lozinka,
			String adresa, String grad, String drzava, String brojTel) {
		super();
		ID = iD;
		this.korisnickoIme = korisnickoIme;
		this.ime = ime;
		this.prezime = prezime;
		this.email = email;
		this.lozinka = lozinka;
		this.adresa = adresa;
		this.grad = grad;
		this.drzava = drzava;
		this.brojTel = brojTel;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getKorisnickoIme() {
		return korisnickoIme;
	}

	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLozinka() {
		return lozinka;
	}

	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getGrad() {
		return grad;
	}

	public void setGrad(String grad) {
		this.grad = grad;
	}

	public String getDrzava() {
		return drzava;
	}

	public void setDrzava(String drzava) {
		this.drzava = drzava;
	}

	public String getBrojTel() {
		return brojTel;
	}

	public void setBrojTel(String brojTel) {
		this.brojTel = brojTel;
	}

	@Override
	public String toString() {
		return "Korisnik [ID=" + ID + ", korisnickoIme=" + korisnickoIme + ", ime=" + ime + ", prezime=" + prezime
				+ ", email=" + email + ", lozinka=" + lozinka + ", adresa=" + adresa + ", grad=" + grad + ", drzava="
				+ drzava + ", brojTel=" + brojTel + "]";
	}
	
	
}
