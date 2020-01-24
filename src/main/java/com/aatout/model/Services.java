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
			Groupe proprietaire) {
		super(id, nom, description, photo, prix, tBCCV, supr, proprietaire);
		// TODO Auto-generated constructor stub
	}

	public Services(String nom, String description, Double prix, Double tBCCV, Groupe proprietaire) {
		super(nom, description, prix, tBCCV, proprietaire);
		// TODO Auto-generated constructor stub
	}

	public Services(double caution, CommandeService commandeService) {
		super();
		this.caution = caution;
		this.commandeService = commandeService;
	}

	public Services(Long id, String nom, String description, String photo, Double prix, Double tBCCV, boolean supr,
			Groupe proprietaire, double caution, CommandeService commandeService) {
		super(id, nom, description, photo, prix, tBCCV, supr, proprietaire);
		this.caution = caution;
		this.commandeService = commandeService;
	}

	public Services(String nom, String description, Double prix, Double tBCCV, Groupe proprietaire, double caution,
			CommandeService commandeService) {
		super(nom, description, prix, tBCCV, proprietaire);
		this.caution = caution;
		this.commandeService = commandeService;
	}
	
	

	
	
	
}
