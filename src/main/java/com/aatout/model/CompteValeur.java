package com.aatout.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlTransient;

import com.aatout.bon.model.Bon;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@DiscriminatorValue(value="CPVL")
public class CompteValeur extends Compte implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name="CODE_PIN")
	private Long pin;
	
	@OneToMany(mappedBy="compteValeurs")
	private List<Bon> bons = new ArrayList<>();

	
	@JsonIgnore
	@XmlTransient
	public List<Bon> getBons() {
		return bons;
	}

	public void setBons(List<Bon> bons) {
		this.bons = bons;
	}

	public Long getPin() {
		return pin;
	}

	public void setPin(Long pin) {
		this.pin = pin;
	}

	public CompteValeur() {
		// TODO Auto-generated constructor stub
	}

	public CompteValeur(String numCompte, Double solde, Double provision, String type) {
		super(numCompte, solde, provision, type);
		// TODO Auto-generated constructor stub
	}

	public CompteValeur(String numCompte, Double solde, Boolean fermer, Boolean active, Double provision, int nbrMAJ,
			Date dateCreation, Boolean supr, Date createdAt, Date updatedAt, AppUser appUserCompte,
			List<Operation> operations, String type) {
		super(numCompte, solde, fermer, active, provision, nbrMAJ, dateCreation, supr, createdAt, updatedAt, appUserCompte,
				operations, type);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "CompteValeur [getType()=" + getType() + ", getNumCompte()=" + getNumCompte() + ", getSolde()="
				+ getSolde() + ", getProvision()=" + getProvision() + "]";
	}

	
		
}

