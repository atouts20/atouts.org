package com.aatout.commande.model;

public class Client {
	private String nom;
	private String email;
	private String phoneNumber;
	private String addresse;
	private String username;
	private String livraison;
	private String detail;
	private int pin;
	
	public Client() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getAddresse() {
		return addresse;
	}
	public void setAddresse(String addresse) {
		this.addresse = addresse;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getLivraison() {
		return livraison;
	}
	public void setLivraison(String livraison) {
		this.livraison = livraison;
	}	
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public int getPin() {
		return pin;
	}
	public void setPin(int pin) {
		this.pin = pin;
	}
	@Override
	public String toString() {
		return "Client [nom=" + nom + ", email=" + email + ", phoneNumber=" + phoneNumber + ", addresse=" + addresse
				+ ", username=" + username + ", livraison=" + livraison + ", detail=" + detail + ", pin=" + pin + "]";
	}
	
	
	
}
