package com.aatout.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.aatout.modelBase.EntityBaseBean;
import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
@Table(name="t_Mobilisation")
public class Mobilisation extends EntityBaseBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private Double prix;
	
	private String nom;
	/*@Enumerated(EnumType.STRING)
	private StatusName statusName;*/
	
	private String identifiant;
	
	private String photo;
	
	private String tel;
	
	private String description;
	
	private boolean suprUser;
	
	private boolean demande;
	
	@ManyToOne
	private AppUser proprietaire;
	
	@OneToMany(mappedBy="mobilisationReponse")
	private List<Reponse> reponses = new ArrayList<>();
	
	@OneToMany(mappedBy="mobilisationRelance")
	private List<Relance> relances = new ArrayList<>();

	public Long getId() {
		return id;
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

	public String getIdentifiant() {
		return identifiant;
	}

	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isSuprUser() {
		return suprUser;
	}

	public void setSuprUser(boolean suprUser) {
		this.suprUser = suprUser;
	}

	public boolean isDemande() {
		return demande;
	}

	public void setDemande(boolean demande) {
		this.demande = demande;
	}

	public AppUser getProprietaire() {
		return proprietaire;
	}

	public void setProprietaire(AppUser proprietaire) {
		this.proprietaire = proprietaire;
	}
	@JsonIgnore
	public List<Reponse> getReponses() {
		return reponses;
	}

	public void setReponses(List<Reponse> reponses) {
		this.reponses = reponses;
	}
	@JsonIgnore
	public List<Relance> getRelances() {
		return relances;
	}

	public void setRelances(List<Relance> relances) {
		this.relances = relances;
	}

	public Mobilisation() {
		// TODO Auto-generated constructor stub
	}

	public Double getPrix() {
		return prix;
	}

	public void setPrix(Double prix) {
		this.prix = prix;
	}
	
	

}
