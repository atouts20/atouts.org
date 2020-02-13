package com.aatout.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.xml.bind.annotation.XmlTransient;

import com.aatout.modelBase.EntityBaseBean;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;

@Entity
@Table(name="T_COMPTE")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@JsonTypeInfo( use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes( {
    @Type( name = "CPMN", value = CompteMonnaie.class ),
    @Type( name = "CPVL", value = CompteValeur.class )
} )
@DiscriminatorColumn(name="TYPE", discriminatorType=DiscriminatorType.STRING, length=4)
public abstract class Compte extends EntityBaseBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true)
	private String numCompte;
	
	private Long monNum;
	
	private String codePays;
	
	@Column(name="CODE_PIN", unique=true)
	private String pin;
	
	//@Min(value = 0L, message = "The value must be positive")
	private Double solde = 0.0; 
	
	private Boolean fermer = Boolean.FALSE;
	
	private Boolean active = Boolean.FALSE;
	
	private Boolean trans = Boolean.FALSE;
	
	private Boolean aatout = Boolean.FALSE;
	@Min(value = 0L, message = "The value must be positive")
	private Double provision = 0.0;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCreation = new Date();
	
	private Boolean supr = Boolean.FALSE;
    
    @ManyToOne
	private AppUser appUserCompte;
	
	@OneToMany(mappedBy="compte", fetch = FetchType.LAZY)
	private List<Operation> operations = new ArrayList<>();
	
	@Column(insertable=false, updatable=false)
	private String type;
	
	
	//  Constructeur par defaut
	

	
	public String getType() {
		return type;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getTrans() {
		return trans;
	}

	public void setTrans(Boolean trans) {
		this.trans = trans;
	}

	public Boolean getAatout() {
		return aatout;
	}

	public void setAatout(Boolean aatout) {
		this.aatout = aatout;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Compte() {
		super();
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

	public AppUser getAppUserCompte() {
		return appUserCompte;
	}

	public void setAppUserCompte(AppUser appUserCompte) {
		this.appUserCompte = appUserCompte;
	}
	@JsonIgnore
	@XmlTransient
	public List<Operation> getOperations() {
		return operations;
	}

	public void setOperations(List<Operation> operations) {
		this.operations = operations;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Double getProvision() {
		return provision;
	}

	public void setProvision(Double provision) {
		this.provision = provision;
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
		
	public String getCodePays() {
		return codePays;
	}

	public void setCodePays(String codePays) {
		this.codePays = codePays;
	}

	public Long getMonNum() {
		return monNum;
	}

	public void setMonNum(Long monNum) {
		this.monNum = monNum;
	}
	

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public Compte(String numCompte, Double solde, Boolean fermer, Boolean active, Boolean aatout, Double provision,
			int nbrMAJ, Date dateCreation, Boolean supr, Date createdAt, Date updatedAt, AppUser appUserCompte,
			List<Operation> operations, String type) {
		super();
		this.numCompte = numCompte;
		this.solde = solde;
		this.fermer = fermer;
		this.active = active;
		this.aatout = aatout;
		this.provision = provision;
		this.dateCreation = dateCreation;
		this.supr = supr;
		this.appUserCompte = appUserCompte;
		this.operations = operations;
		this.type = type;
	}

	public Compte(String numCompte, Double solde, Double provision, String type) {
		super();
		this.numCompte = numCompte;
		this.solde = solde;
		this.provision = provision;
		this.type = type;
	}
	
	public Compte(String numCompte, Double solde, Boolean fermer, Boolean active, Double provision, int nbrMAJ,
			Date dateCreation, Boolean supr, Date createdAt, Date updatedAt, AppUser appUserCompte,
			List<Operation> operations, String type) {
		super();
		this.numCompte = numCompte;
		this.solde = solde;
		this.fermer = fermer;
		this.active = active;
		this.provision = provision;
		this.dateCreation = dateCreation;
		this.supr = supr;
		this.appUserCompte = appUserCompte;
		this.operations = operations;
		this.type = type;
	}

	@Override
	public String toString() {
		return "Compte [numCompte=" + numCompte + ", solde=" + solde + ", appUserCompte=" + appUserCompte
				+ ", operations=" + operations + ", type=" + type + "]";
	}

		
}
