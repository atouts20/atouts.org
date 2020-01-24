package com.aatout.web;

public class FormCompte {
	
    private String numCompte;
	
	private Double solde; 
	
	private Double provision;
	
	private String type;
	
	

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getNumCompte() {
		return numCompte;
	}

	public void setNumCompte(String numCompte) {
		this.numCompte = numCompte;
	}

	public Double getSolde() {
		return solde;
	}

	public void setSolde(Double solde) {
		this.solde = solde;
	}

	public Double getProvision() {
		return provision;
	}

	public void setProvision(Double provision) {
		this.provision = provision;
	}

	

	@Override
	public String toString() {
		return "FormCompte [numCompte=" + numCompte + ", solde=" + solde + ", provision=" + provision
				+ ", type=" + type + "]";
	}
	
	
	
	

}
