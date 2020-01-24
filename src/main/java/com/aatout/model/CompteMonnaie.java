package com.aatout.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value="CPMN")
public class CompteMonnaie extends Compte implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	


	public CompteMonnaie() {
		// TODO Auto-generated constructor stub
	}



	public CompteMonnaie(String numCompte, Double solde, Double provision, String type) {
		super(numCompte, solde,  provision, type);
		// TODO Auto-generated constructor stub
	}



	public CompteMonnaie(String numCompte, Double solde, Boolean fermer, Boolean active, Double provision, int nbrMAJ,
			Date dateCreation, Boolean supr, Date createdAt, Date updatedAt, AppUser appUserCompte,
			List<Operation> operations, String type) {
		super(numCompte, solde, fermer, active, provision, nbrMAJ, dateCreation, supr, createdAt, updatedAt, appUserCompte,
				operations, type);
		// TODO Auto-generated constructor stub
	}



	@Override
	public String toString() {
		return "CompteMonnaie [getType()=" + getType() + ", getNumCompte()=" + getNumCompte() + ", getSolde()="
				+ getSolde() + ", getOperations()=" + getOperations() + ", getActive()=" + getActive()
				+ ", getProvision()=" + getProvision() + "]";
	}
	
	



				
}
