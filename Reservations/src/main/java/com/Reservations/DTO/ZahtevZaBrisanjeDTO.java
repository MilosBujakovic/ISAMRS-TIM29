package com.Reservations.DTO;

public class ZahtevZaBrisanjeDTO {
	private String username;

	private String password;

	

	private String email;
	
	
	
	private String phone;


	private String message;
	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public ZahtevZaBrisanjeDTO() {
		super();
	}


	public String getUsername() {
		return username;
	}


	@Override
	public String toString() {
		return "ZahtevZaBrisanjeDTO [username=" + username + ", password=" + password + ", email=" + email + ", phone="
				+ phone + ", message=" + message + "]";
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}
	

	
}
