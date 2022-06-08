package com.Reservations.DTO;

import org.springframework.web.multipart.MultipartFile;

public class SlikaDTO 
{
	private String putanja;
	private String nazivSlike;
	private MultipartFile slika;
	
	public SlikaDTO () {}

	
	

	public SlikaDTO(String putanja, String naziv, String komentari) {

		this.putanja = putanja;
		this.nazivSlike = naziv;
	}




	public SlikaDTO(int iD, String fotograf, String putanja, String naziv, String komentari, MultipartFile slika) {
		this.putanja = putanja;
		this.nazivSlike = naziv;
		this.slika = slika;
	}


	public String getPutanja() {
		return putanja;
	}


	public void setPutanja(String putanja) {
		this.putanja = putanja;
	}


	public String getNazivSlike() {
		return nazivSlike;
	}


	public void setNazivSlike(String naziv) {
		this.nazivSlike = naziv;
	}


	public MultipartFile getSlika() {
		return slika;
	}




	public void setSlika(MultipartFile slika) {
		this.slika = slika;
	}
	
	
	
}
