 package com.aatout.web;

import com.aatout.model.Groupe;

public class BienForm {
	private String nom;
	
	private String description;
	
	private String photo;
	
	private Double prix;
	
	private Double tBCCV;
	
	private Double stock;
	
	private Double stockAlert;
	
	private String cat;
	
	private Groupe proprietaire;
	
	private String type;
	

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Groupe getProprietaire() {
		return proprietaire;
	}

	public void setProprietaire(Groupe proprietaire) {
		this.proprietaire = proprietaire;
	}

	public String getcat() {
		return cat;
	}

	public void setcat(String categorie) {
		cat = categorie;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Double getPrix() {
		return prix;
	}

	public void setPrix(Double prix) {
		this.prix = prix;
	}

	public Double gettBCCV() {
		return tBCCV;
	}

	public void settBCCV(Double tBCCV) {
		this.tBCCV = tBCCV;
	}

	public Double getStock() {
		return stock;
	}

	public void setStock(Double stock) {
		this.stock = stock;
	}

	public Double getStockAlert() {
		return stockAlert;
	}

	public void setStockAlert(Double stockAlert) {
		this.stockAlert = stockAlert;
	}
	
	

}
