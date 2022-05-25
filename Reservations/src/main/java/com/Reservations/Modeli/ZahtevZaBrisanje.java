package com.Reservations.Modeli;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.Reservations.DTO.RegistracijaVlasnikaInstruktoraDTO;
import com.Reservations.Modeli.enums.TipRegistracije;
@Entity
@Table(name = "zahtevi_brisanje")
public class ZahtevZaBrisanje {

	@Id
	@Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long ID;
	
	
	@Column(name = "ime")
	private String ime;
	
	@Column(name = "lozinka")
	private String lozinka;
	
	@Column(name = "email")
	private String email;
	
	
	
	
	@Column(name = "brojTel")
	private String brojTel;
	
	
	
	// napomena: na osnovu polja tipRegistracije određujemo ulogu -> indeks u enumu mapiramo na odgovarajuću ulogu
		

		@Column(name="razlog")
		private String razlogRegistracije;

		public ZahtevZaBrisanje() {
			super();
		}

		public long getID() {
			return ID;
		}

		public void setID(long iD) {
			ID = iD;
		}

		public String getIme() {
			return ime;
		}

		public void setIme(String ime) {
			this.ime = ime;
		}

		public String getLozinka() {
			return lozinka;
		}

		public void setLozinka(String lozinka) {
			this.lozinka = lozinka;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getBrojTel() {
			return brojTel;
		}

		public void setBrojTel(String brojTel) {
			this.brojTel = brojTel;
		}

		public String getRazlogRegistracije() {
			return razlogRegistracije;
		}

		public void setRazlogRegistracije(String razlogRegistracije) {
			this.razlogRegistracije = razlogRegistracije;
		}

		@Override
		public String toString() {
			return "ZahtevZaBrisanje [ID=" + ID + ", ime=" + ime + ", lozinka=" + lozinka + ", email=" + email
					+ ", brojTel=" + brojTel + ", razlogRegistracije=" + razlogRegistracije + "]";
		}

}