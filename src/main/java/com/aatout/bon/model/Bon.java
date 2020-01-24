package com.aatout.bon.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.aatout.model.CompteValeur;
import com.aatout.modelBase.EntityBaseBean;
import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
@Table(name="t_Bon")
public class Bon extends EntityBaseBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique=true)	
	private String numeroBon;

	private Double montant;

	private String description;
	
	@Column(unique=true)
	private Long secret;

	private boolean encaisse = false;

	private boolean liquide  = false;
	
	private boolean suprUser = false;
	
	private String encaisseBy;
    
    private String liquideBy;

	@ManyToOne
	private CompteValeur compteValeurs;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dateExpiration = new Date();

	public CompteValeur getCompteValeurs() {
		return compteValeurs;
	}

	public void setCompteValeurs(CompteValeur compteValeurs) {
		this.compteValeurs = compteValeurs;
	}

	

	public Double getMontant() {
		return montant;
	}

	public void setMontant(Double montant) {
		this.montant = montant;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isEncaisse() {
		return encaisse;
	}

	public void setEncaisse(boolean encaisse) {
		this.encaisse = encaisse;
	}

	public boolean isLiquide() {
		return liquide;
	}

	public void setLiquide(boolean liquide) {
		this.liquide = liquide;
	}

	public Date getDateExpiration() {
		return dateExpiration;
	}

	public void setDateExpiration(Date dateExpiration) {
		this.dateExpiration = dateExpiration;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumeroBon() {
		return numeroBon;
	}

	public void setNumeroBon(String numeroBon) {
		this.numeroBon = numeroBon;
	}

	public boolean isSuprUser() {
		return suprUser;
	}

	public void setSuprUser(boolean suprUser) {
		this.suprUser = suprUser;
	}

	public Long getSecret() {
		return secret;
	}

	public void setSecret(long secret) {
		this.secret = secret;
	}

	public String getEncaisseBy() {
		return encaisseBy;
	}

	public void setEncaisseBy(String encaisseBy) {
		this.encaisseBy = encaisseBy;
	}

	public String getLiquideBy() {
		return liquideBy;
	}

	public void setLiquideBy(String liquideBy) {
		this.liquideBy = liquideBy;
	}
	
	
}
