package com.aatout.commande.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.aatout.modelBase.EntityBaseBean;

@Entity
@Table(name="t_Payment")
public class Payment extends EntityBaseBean implements Serializable{
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date datePayment;
    private Double montant;
    private Double contrepartieFcfa;
    private String badgeLivreur;
    private String codePayment;
    private String observation;
    private boolean supr;   
    
    @OneToOne
    private Commande commande;
    
    
    
    
    
	public Payment() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDatePayment() {
		return datePayment;
	}

	public void setDatePayment(Date datePayment) {
		this.datePayment = datePayment;
	}

	public Double getMontant() {
		return montant;
	}

	public void setMontant(Double montant) {
		this.montant = montant;
	}

	public Double getContrepartieFcfa() {
		return contrepartieFcfa;
	}

	public void setContrepartieFcfa(Double contrepartieFcfa) {
		this.contrepartieFcfa = contrepartieFcfa;
	}

	public String getBadgeLivreur() {
		return badgeLivreur;
	}

	public void setBadgeLivreur(String badgeLivreur) {
		this.badgeLivreur = badgeLivreur;
	}

	public String getCodePayment() {
		return codePayment;
	}

	public void setCodePayment(String codePayment) {
		this.codePayment = codePayment;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public Commande getCommande() {
		return commande;
	}

	public void setCommande(Commande commande) {
		this.commande = commande;
	}

	public boolean isSupr() {
		return supr;
	}

	public void setSupr(boolean supr) {
		this.supr = supr;
	}

	

	public Payment(Boolean status, Date createdAt, Date updatedAt, String createBy, String deleteBy, int nbrMAJ) {
		super(status, createdAt, updatedAt, createBy, deleteBy, nbrMAJ);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Payment [id=" + id + ", datePayment=" + datePayment + ", montant=" + montant + ", contrepartieFcfa="
				+ contrepartieFcfa + ", badgeLivreur=" + badgeLivreur + ", codePayment=" + codePayment
				+ ", observation=" + observation + ", supr=" + supr + ", commande=" + commande + "]";
	}
    
	
    
}
