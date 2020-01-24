package com.aatout.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Entity
@Table(name="T_FACTURER")
public class Facturer implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CleFacturer pkFacturer;
	
	private Double quantite;
	
	@CreatedDate
	private Date createdAt;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	private Date updatedAt;
	
	private int nbrMAJ = 0; // pour calculer le nombre de modification

	@ManyToOne
	private Facture facture;

	@ManyToOne
	private Bien bien;


	public Facture getFacture() {
		return facture;
	}
	public void setFacture(Facture facture) {
		this.facture = facture;
	}
	public Bien getBien() {
		return bien;
	}
	public void setBien(Bien bien) {
		this.bien = bien;
	}

	public CleFacturer getPkFacturer() {
		return pkFacturer;
	}
	public void setPkFacturer(CleFacturer pkFacturer) {
		this.pkFacturer = pkFacturer;
	}
	public Double getQuantite() {
		return quantite;
	}
	public void setQuantite(Double quantite) {
		this.quantite = quantite;
	}
	
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	public int getNbrMAJ() {
		return nbrMAJ;
	}
	public void setNbrMAJ(int nbrMAJ) {
		this.nbrMAJ = nbrMAJ;
	}
	public Facturer() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Facturer(Double quantite, Facture facture, Bien bien) {
		super();
		this.quantite = quantite;
		this.facture = facture;
		this.bien = bien;
	}
	

}
