package com.aatout.commande.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.aatout.model.Services;

public class CommandeServiceForm {
	private long id;
	
	private Date date;
	
	private Client client=new Client();

    private Services service;

	private boolean pin;

	private boolean compteClientOk;

	private boolean verifieCompteClientOK;

	private boolean soldeOk;

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

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Services getService() {
		return service;
	}

	public void setService(Services service) {
		this.service = service;
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

	public boolean isVerifieCompteClientOK() {
		return verifieCompteClientOK;
	}

	public void setVerifieCompteClientOK(boolean verifieCompteClientOK) {
		this.verifieCompteClientOK = verifieCompteClientOK;
	}

	public boolean isSoldeOk() {
		return soldeOk;
	}

	public void setSoldeOk(boolean soldeOk) {
		this.soldeOk = soldeOk;
	}

	public CommandeServiceForm() {
		super();
		// TODO Auto-generated constructor stub
	}
	
		
	
}
