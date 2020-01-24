package com.aatout.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;

import com.aatout.enums.TypeName;
import com.aatout.modelBase.EntityBaseBean;
@Entity
@Table(name="t_SousCompte")
public class SousCompte extends EntityBaseBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true)
	private String numCompte;
	@Min(value = 0L, message = "The value must be positive")
	private Double solde = 0.0; 
	
	private Boolean fermer = Boolean.FALSE;
	
	private Boolean active = Boolean.FALSE;
	
	private Boolean aatout = Boolean.FALSE;
	
	private int nbrMAJ = 0; // pour calculer le nombre de modification
	
	@Temporal(TemporalType.DATE)
	private Date dateCreation = new Date();
	
	private Boolean supr = Boolean.FALSE;
	
	@Column(name="CODE_PIN", nullable=true, unique=false)
	private Long pin = 18945l;
	    
    @ManyToOne
	private AppUser appUserSousCompte;
	
	@OneToMany(mappedBy="sousCompte", fetch = FetchType.LAZY)
	private List<Operation> operations = new ArrayList<>();
	
	@Enumerated(EnumType.STRING)
	@Column(name="TYPE_SOUS_COMPTE", nullable=false)
	private TypeName type;
	
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public SousCompte() {
		// TODO Auto-generated constructor stub
	}

	
	public SousCompte(Boolean status, Date createdAt, Date updatedAt, String createBy, String deleteBy, int nbrMAJ) {
		super(status, createdAt, updatedAt, createBy, deleteBy, nbrMAJ);
		// TODO Auto-generated constructor stub
	}

	public String getNumCompte() {
		return numCompte;
	}

	public void setNumCompte(String numCompte) {
		this.numCompte = numCompte;
	}

	public Double getSolde() {
		return solde;
	}

	public void setSolde(Double solde) {
		this.solde = solde;
	}

	public Boolean getFermer() {
		return fermer;
	}

	public void setFermer(Boolean fermer) {
		this.fermer = fermer;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Boolean getAatout() {
		return aatout;
	}

	public void setAatout(Boolean aatout) {
		this.aatout = aatout;
	}

	public int getNbrMAJ() {
		return nbrMAJ;
	}

	public void setNbrMAJ(int nbrMAJ) {
		this.nbrMAJ = nbrMAJ;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public Boolean getSupr() {
		return supr;
	}

	public void setSupr(Boolean supr) {
		this.supr = supr;
	}

	public Long getPin() {
		return pin;
	}

	public void setPin(Long pin) {
		this.pin = pin;
	}

	public AppUser getAppUserSousCompte() {
		return appUserSousCompte;
	}

	public void setAppUserSousCompte(AppUser appUserSousCompte) {
		this.appUserSousCompte = appUserSousCompte;
	}

	public List<Operation> getOperations() {
		return operations;
	}

	public void setOperations(List<Operation> operations) {
		this.operations = operations;
	}

	public TypeName getType() {
		return type;
	}

	public void setType(TypeName type) {
		this.type = type;
	}
	

}
