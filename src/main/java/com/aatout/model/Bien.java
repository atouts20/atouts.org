 package com.aatout.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="T_BIEN")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="CAT", discriminatorType=DiscriminatorType.STRING, length=4)
@EntityListeners(AuditingEntityListener.class)
public abstract class Bien implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;	
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(name="nom", nullable=false, length=200)
	private String nom;
	
	private String description;
	
	private String photo;
	
	private Double prix;
	
	private Double tBCCV;
	
	private boolean supr;
	
	private boolean active;
	
	private boolean accepter;
	
	@Column(nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date createdAt;	
	
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	private Date updatedAt;
	
	/*@ManyToMany(mappedBy="biens")
	private List<AppUser> proprietaire= new ArrayList<>();*/
	
	@ManyToOne
	private Groupe proprietaire;
	
	
	/*@OneToMany(mappedBy="bien")
	private List<Facturer> facturers=new ArrayList<>();
*/
	/*public List<Facturer> getFacturers() {
		return facturers;
	}

	public void setFacturers(List<Facturer> facturers) {
		this.facturers = facturers;
	}
	*/
	

	public boolean isAccepter() {
		return accepter;
	}

	public void setAccepter(boolean accepter) {
		this.accepter = accepter;
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

	public Long getId() {
		return id;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}


	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Double getPrix() {
		return prix;
	}

	public void setPrix(Double prix) {
		this.prix = prix;
	}

	public Double gettBCCV() {
		return tBCCV;
	}

	public void settBCCV(Double tBCCV) {
		this.tBCCV = tBCCV;
	}
	

	public boolean isSupr() {
		return supr;
	}

	public void setSupr(boolean supr) {
		this.supr = supr;
	}

	public Bien() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public Groupe getProprietaire() {
		return proprietaire;
	}

	public void setProprietaire(Groupe proprietaire) {
		this.proprietaire = proprietaire;
	}

	public Bien(Long id, String nom, String description, String photo, Double prix, Double tBCCV, boolean supr,
			Groupe proprietaire) {
		super();
		this.id = id;
		this.nom = nom;
		this.description = description;
		this.photo = photo;
		this.prix = prix;
		this.tBCCV = tBCCV;
		this.supr = supr;
		this.proprietaire = proprietaire;
	}

	public Bien(String nom, String description, Double prix, Double tBCCV, Groupe proprietaire) {
		super();
		this.nom = nom;
		this.description = description;
		this.prix = prix;
		this.tBCCV = tBCCV;
		this.proprietaire = proprietaire;
	}

	
		
}
