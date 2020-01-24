package com.aatout.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue(value="PROD")
public class Produit extends Bien implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Double stock;
	private Double stockAlert;
	@Transient
	private int quantite = 1;
	public Produit() {
		super();
		// TODO Auto-generated constructor stub
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
	


	public int getQuantite() {
		return quantite;
	}


	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}


	public Produit(String nom, String description, Double prix, Double tBCCV, Groupe proprietaire, Double stock,
			Double stockAlert) {
		super(nom, description, prix, tBCCV, proprietaire);
		this.stock = stock;
		this.stockAlert = stockAlert;
	}
	
	
	
}
