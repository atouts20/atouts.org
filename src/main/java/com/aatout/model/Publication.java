package com.aatout.model;

import java.io.Serializable;
import java.util.Date;

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
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.aatout.modelBase.EntityBaseBean;

@Entity
@Table(name="t_publication")
public class Publication extends EntityBaseBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String cat;
	private String nom;
	private String description;
	private double prix;
	private double tBCCV;
	private String photo;
	private String tel;
	private boolean accepter;
	private boolean creer;
	private boolean supUser;
	private boolean rejeter;
	
	@ManyToOne
	private AppUser proprietaire;
	
	
	

	public double gettBCCV() {
		return tBCCV;
	}

	public void settBCCV(double tBCCV) {
		this.tBCCV = tBCCV;
	}

	public boolean isRejeter() {
		return rejeter;
	}

	public void setRejeter(boolean rejeter) {
		this.rejeter = rejeter;
	}

	public Publication() {
		super();
	}

	public boolean isSupUser() {
		return supUser;
	}

	public void setSupUser(boolean supUser) {
		this.supUser = supUser;
	}

	public Publication(Long id, String cat, String nom, String description, double prix, String photo, String tel,
			boolean accepter, boolean creer, AppUser proprietaire) {
		super();
		this.id = id;
		this.cat = cat;
		this.nom = nom;
		this.description = description;
		this.prix = prix;
		this.photo = photo;
		this.tel = tel;
		this.accepter = accepter;
		this.creer = creer;
		this.proprietaire = proprietaire;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCat() {
		return cat;
	}

	public void setCategorie(String cat) {
		this.cat = cat;
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

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public boolean isAccepter() {
		return accepter;
	}

	public void setAccepter(boolean accepter) {
		this.accepter = accepter;
	}

	public boolean isCreer() {
		return creer;
	}

	public void setCreer(boolean creer) {
		this.creer = creer;
	}

	public AppUser getProprietaire() {
		return proprietaire;
	}

	public void setProprietaire(AppUser proprietaire) {
		this.proprietaire = proprietaire;
	}
	
	
			

}
