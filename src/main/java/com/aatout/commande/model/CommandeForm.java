package com.aatout.commande.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.aatout.model.AppUser;

public class CommandeForm {	
	private long id;
	private Date date;
	private Client client=new Client();

	private List<CommandeProduit> Produits=new ArrayList<>();

	private boolean pin;

	private boolean compteClientOk;

	private boolean verifieCompteClientOK;

	private boolean soldeOk;

	private boolean stockOk;
	
	

	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}

	public List<CommandeProduit> getProduits() {
		return Produits;
	}
	public void setProduits(List<CommandeProduit> produits) {
		Produits = produits;
	}

	public boolean isPin() {
		return pin;
	}
	public void setPin(boolean pin) {
		this.pin = pin;
	}
	public boolean isCompteClientOk() {
		return compteClientOk;
	}
	public void setCompteClientOk(boolean compteClientOk) {
		this.compteClientOk = compteClientOk;
	}
	public boolean isSoldeOk() {
		return soldeOk;
	}
	public void setSoldeOk(boolean soldeOk) {
		this.soldeOk = soldeOk;
	}
	
	public boolean isStockOk() {
		return stockOk;
	}
	public void setStockOk(boolean stockOk) {
		this.stockOk = stockOk;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	public boolean isVerifieCompteClientOK() {
		return verifieCompteClientOK;
	}
	public void setVerifieCompteClientOK(boolean verifieCompteClientOK) {
		this.verifieCompteClientOK = verifieCompteClientOK;
	}
	public CommandeForm() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}


