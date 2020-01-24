package com.aatout.model;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlTransient;

import com.aatout.modelBase.EntityBaseBean;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="t_appUser")
public class AppUser extends EntityBaseBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(unique=true)
	private String username;

	private String password;
	@Column(insertable=false, updatable=false)
	private String repassword;

	private String nom;

	private String prenom;

	@Temporal(TemporalType.DATE)
	private Date dateNaissance;

	private String lieu;

	private String departement;

	private String photo;

	@Column(unique=true)
	
	private String email;

	//private String profession ;
	@ManyToMany	
	private List<Metier> metiers;
	
	@ManyToOne
	private Metier metier;
	
	@Column(unique=true)
	private String phoneNumber;

	private String phoneNumber1;

	private String phoneNumber2;

	private String residance;

	private String categorie;
	
	@ManyToOne
	private Pays nationalite;

	private String naturePiece;

	private String nci;

	private Date dateEmission;

	@Temporal(TemporalType.DATE)
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

	@Column(name = "enabled")
	private boolean enabled = Boolean.FALSE;
	
	private boolean active = Boolean.FALSE;

	@Column(name = "supprime")
	private boolean supprime;

	private boolean remember;

	@Column(name = "confirmation_token")
	private String confirmationToken;

	@Temporal(TemporalType.TIMESTAMP)
	private Date expiryDate;
	
	private boolean accountNonLocked = true;


	@ManyToMany(fetch=FetchType.EAGER)	
	private Collection<AppRole> roles = new ArrayList<>();


	@OneToMany(mappedBy="appUserCompte")
	private List<Compte> comptes= new ArrayList<>();
	
	@OneToMany(mappedBy="appUserSousCompte")
	private List<SousCompte> sousComptes = new ArrayList<>();

	@ManyToMany
	private List<Bien> biens= new ArrayList<>();

	/*@OneToMany(mappedBy = "appUser")
	private List<Grouper> groupers = new ArrayList<>();*/

	/**
	 *  Constructeur sans paramettre est obligatoire
	 */

	public AppUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public AppUser(Boolean status, Date createdAt, Date updatedAt, String createBy, String deleteBy, int nbrMAJ) {
		super(status, createdAt, updatedAt, createBy, deleteBy, nbrMAJ);
		// TODO Auto-generated constructor stub
	}



	



	public boolean isActive() {
		return active;
	}



	public void setActive(boolean active) {
		this.active = active;
	}



	/**
	 *  Les Getters et Setters de chaque attribut est obligatoire
	 */

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	

	public String getRepassword() {
		return repassword;
	}

	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Metier> getMetiers() {
		return metiers;
	}

	public void setMetiers(List<Metier> metiers) {
		this.metiers = metiers;
	}
	
	public Metier getMetier() {
		return metier;
	}

	public void setMetier(Metier metier) {
		this.metier = metier;
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

	public Pays getNationalite() {
		return nationalite;
	}

	public void setNationalite(Pays nationalite) {
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

	

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public boolean isSupprime() {
		return supprime;
	}

	public void setSupprime(boolean supprime) {
		this.supprime = supprime;
	}

	public boolean isRemember() {
		return remember;
	}

	public void setRemember(boolean remember) {
		this.remember = remember;
	}

	public String getConfirmationToken() {
		return confirmationToken;
	}

	public void setConfirmationToken(String confirmationToken) {
		this.confirmationToken = confirmationToken;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		
		this.expiryDate = expiryDate;
	}

	

	public Collection<AppRole> getRoles() {
		return roles;
	}

	public void setRoles(Collection<AppRole> roles) {
		this.roles = roles;
	}
	@JsonIgnore
    @XmlTransient
	public List<Compte> getComptes() {
		return comptes;
	}

	public void setComptes(List<Compte> comptes) {
		this.comptes = comptes;
	}

	public List<Bien> getBiens() {
		return biens;
	}

	public void setBiens(List<Bien> biens) {
		this.biens = biens;
	}
	

	public String getCategorie() {
		return categorie;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}
	
	

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
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
	
	@JsonIgnore
    @XmlTransient
	public List<SousCompte> getSousComptes() {
		return sousComptes;
	}

	public void setSousComptes(List<SousCompte> sousComptes) {
		this.sousComptes = sousComptes;
	}


	public AppUser(String username, String password, String nom, String prenom, String lieu, String departement,
			String email) {
		super();
		this.username = username;
		this.password = password;
		this.nom = nom;
		this.prenom = prenom;
		this.lieu = lieu;
		this.departement = departement;
		this.email = email;
	}



	@Override
	public String toString() {
		return "AppUser [username=" + username + ", nom=" + nom + ", email=" + email + ", enabled=" + enabled
				+ ", active=" + active + ", supprime=" + supprime + ", confirmationToken=" + confirmationToken
				+ ", accountNonLocked=" + accountNonLocked + "]";
	}



	
	
	

		
}
