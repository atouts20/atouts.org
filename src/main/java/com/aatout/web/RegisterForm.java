package com.aatout.web;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class RegisterForm {
	private String username;

	private String password;

	private String nom;

	private String prenom;

	@Temporal(TemporalType.DATE)
	private Date dateNaissance;

	private String lieu;

	private String departement;

	private String photo;

	@Column(unique=true)
	
	private String email;

	private String profession ;

	@Column(unique=true)
	private String phoneNumber;

	private String phoneNumber1;

	private String phoneNumber2;

	private String residance;

	private String categorie;

	private String nationalite;

	private String naturePiece;

	private String nci;

	private Date dateEmission;

	private Date dateExpiration;

	private String nomPere;

	private String nomMere;

	private String contactPere;

	private String contactMere;

	private String matrimoniale;

	@Column(name="conjointE")
	private String nomEtPrenomConjoint;

	private int nbrEnfant;

	@Column(name="perso")
	private String personneContacter;

	@Column(name="persoTel")
	private String contactPersonneContacter;

	@Column(name="relaPerso")
	private String relationPersonne;

	private String sexe;
	
	private String scane;
	
	private String signature;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public String getLieu() {
		return lieu;
	}

	public void setLieu(String lieu) {
		this.lieu = lieu;
	}

	public String getDepartement() {
		return departement;
	}

	public void setDepartement(String departement) {
		this.departement = departement;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPhoneNumber1() {
		return phoneNumber1;
	}

	public void setPhoneNumber1(String phoneNumber1) {
		this.phoneNumber1 = phoneNumber1;
	}

	public String getPhoneNumber2() {
		return phoneNumber2;
	}

	public void setPhoneNumber2(String phoneNumber2) {
		this.phoneNumber2 = phoneNumber2;
	}

	public String getResidance() {
		return residance;
	}

	public void setResidance(String residance) {
		this.residance = residance;
	}

	public String getCategorie() {
		return categorie;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

	public String getNationalite() {
		return nationalite;
	}

	public void setNationalite(String nationalite) {
		this.nationalite = nationalite;
	}

	public String getNaturePiece() {
		return naturePiece;
	}

	public void setNaturePiece(String naturePiece) {
		this.naturePiece = naturePiece;
	}

	public String getNci() {
		return nci;
	}

	public void setNci(String nci) {
		this.nci = nci;
	}

	public Date getDateEmission() {
		return dateEmission;
	}

	public void setDateEmission(Date dateEmission) {
		this.dateEmission = dateEmission;
	}

	public Date getDateExpiration() {
		return dateExpiration;
	}

	public void setDateExpiration(Date dateExpiration) {
		this.dateExpiration = dateExpiration;
	}

	public String getNomPere() {
		return nomPere;
	}

	public void setNomPere(String nomPere) {
		this.nomPere = nomPere;
	}

	public String getNomMere() {
		return nomMere;
	}

	public void setNomMere(String nomMere) {
		this.nomMere = nomMere;
	}

	public String getContactPere() {
		return contactPere;
	}

	public void setContactPere(String contactPere) {
		this.contactPere = contactPere;
	}

	public String getContactMere() {
		return contactMere;
	}

	public void setContactMere(String contactMere) {
		this.contactMere = contactMere;
	}

	public String getMatrimoniale() {
		return matrimoniale;
	}

	public void setMatrimoniale(String matrimoniale) {
		this.matrimoniale = matrimoniale;
	}

	public String getNomEtPrenomConjoint() {
		return nomEtPrenomConjoint;
	}

	public void setNomEtPrenomConjoint(String nomEtPrenomConjoint) {
		this.nomEtPrenomConjoint = nomEtPrenomConjoint;
	}

	public int getNbrEnfant() {
		return nbrEnfant;
	}

	public void setNbrEnfant(int nbrEnfant) {
		this.nbrEnfant = nbrEnfant;
	}

	public String getPersonneContacter() {
		return personneContacter;
	}

	public void setPersonneContacter(String personneContacter) {
		this.personneContacter = personneContacter;
	}

	public String getContactPersonneContacter() {
		return contactPersonneContacter;
	}

	public void setContactPersonneContacter(String contactPersonneContacter) {
		this.contactPersonneContacter = contactPersonneContacter;
	}

	public String getRelationPersonne() {
		return relationPersonne;
	}

	public void setRelationPersonne(String relationPersonne) {
		this.relationPersonne = relationPersonne;
	}

	public String getSexe() {
		return sexe;
	}

	public void setSexe(String sexe) {
		this.sexe = sexe;
	}

	public String getScane() {
		return scane;
	}

	public void setScane(String scane) {
		this.scane = scane;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	
}
