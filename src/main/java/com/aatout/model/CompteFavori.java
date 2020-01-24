package com.aatout.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;

import com.aatout.modelBase.EntityBaseBean;
import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
@Table(name="T_COMPTE_FAVORI")
public class CompteFavori extends EntityBaseBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true)
	private String numCompte;
	private String nom;
	private String prenom;
	private String adresse;
	private String tel;
	@Email
	@Column(unique = true)
	private String email;
	@ManyToOne
	private AppUser appUserCompte;
	
	private Boolean supr = false;
 
	public CompteFavori() {
		// TODO Auto-generated constructor stub 
	}
 
	public CompteFavori(Boolean status, Date createdAt, Date updatedAt, String createBy, String deleteBy, int nbrMAJ) {
		super(status, createdAt, updatedAt, createBy, deleteBy, nbrMAJ);
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumCompte() {
		return numCompte;
	}

	public void setNumCompte(String numCompte) {
		this.numCompte = numCompte;
	}
	@JsonIgnore
	public AppUser getAppUserCompte() {
		return appUserCompte;
	}

	public void setAppUserCompte(AppUser appUserCompte) {
		this.appUserCompte = appUserCompte;
	}

	public Boolean getSupr() {
		return supr;
	}

	public void setSupr(Boolean supr) {
		this.supr = supr;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "CompteFavori [id=" + id + ", numCompte=" + numCompte + ", nom=" + nom + ", prenom=" + prenom
				+ ", adresse=" + adresse + ", tel=" + tel + ", email=" + email + ", supr=" + supr + "]";
	}
	

}
