package com.aatout.model;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import com.aatout.commande.model.CommandeService;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@DiscriminatorValue(value="SERV")
public class Services extends Bien implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

    private double caution;
    
    

    @OneToOne(mappedBy = "service")
    private CommandeService commandeService;
    
    

	public double getCaution() {
		return caution;
	}

	public void setCaution(double caution) {
		this.caution = caution;
	}

	@JsonIgnore
	public CommandeService getCommandeService() {
		return commandeService;
	}

	public void setCommandeService(CommandeService commandeService) {
		this.commandeService = commandeService;
	}

	public Services() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Services(Long id, String nom, String description, String photo, Double prix, Double tBCCV, boolean supr,
			Groupe proprietaire, String localisation) {
		super(id, nom, description, photo, prix, tBCCV, supr, proprietaire, localisation);
		// TODO Auto-generated constructor stub
	}

	public Services(String nom, String description, Double prix, Double tBCCV, Groupe proprietaire, String localisation) {
		super(nom, description, prix, tBCCV, proprietaire, localisation);
		// TODO Auto-generated constructor stub
	}
	
	public Services(String nom, String description, Double prix, Double tBCCV, Groupe proprietaire,
			//AppUser proprietaireUser, double caution) {
			 String localisation, double caution) {
		//super(nom, description, prix, tBCCV, proprietaire, proprietaireUser);
		super(nom, description, prix, tBCCV, proprietaire, localisation);
		this.caution = caution;
	}

	public Services(double caution, CommandeService commandeService) {
		super();
		this.caution = caution;
		this.commandeService = commandeService;
	}

	public Services(Long id, String nom, String description, String photo, Double prix, Double tBCCV, boolean supr,
			Groupe proprietaire, String localisation, double caution, CommandeService commandeService) {
		super(id, nom, description, photo, prix, tBCCV, supr, proprietaire, localisation);
		this.caution = caution;
		this.commandeService = commandeService;
	}

	public Services(String nom, String description, Double prix, Double tBCCV, Groupe proprietaire, String localisation, double caution,
			CommandeService commandeService) {
		super(nom, description, prix, tBCCV, proprietaire, localisation);
		this.caution = caution;
		this.commandeService = commandeService;
	}
	
	

	
	
	
}
