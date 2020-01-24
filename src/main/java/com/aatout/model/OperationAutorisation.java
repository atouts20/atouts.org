package com.aatout.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;

import com.aatout.enums.StatusName;
import com.aatout.modelBase.EntityBaseBean;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
@Entity
@Table(name="T_OperationAutorisation")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type", discriminatorType=DiscriminatorType.STRING, length=7)
@JsonTypeInfo( use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes( {
    @Type( name = "DEPO_EN", value = DepotEn.class ),
    @Type( name = "RETR_EN", value = RetraitEn.class )
} )
public abstract class OperationAutorisation extends EntityBaseBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateOp;
	
	@Min(value = 0L, message = "The value must be positive")
	private double montantOp;
	
	private String narrative;
	
	private Long badge;
	
	@Column(insertable=false, updatable=false)
	private String type;
	
	//private Boolean bloqueRetrait;

	
	private Boolean suprUser = false;
	
    @Enumerated(EnumType.STRING)
	private StatusName etat = StatusName.NON_AUTORISEE ;
	
	private String numCompteSysteme;
	
	@ManyToOne
	private Compte compte;
	
	private String autorisedBy;

	public OperationAutorisation() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDateOp() {
		return dateOp;
	}

	public void setDateOp(Date dateOp) {
		this.dateOp = dateOp;
	}

	public double getMontantOp() {
		return montantOp;
	}

	public void setMontantOp(double montantOp) {
		this.montantOp = montantOp;
	}

	public String getNarrative() {
		return narrative;
	}

	public void setNarrative(String narrative) {
		this.narrative = narrative;
	}

	public Long getBadge() {
		return badge;
	}

	public void setBadge(Long badge) {
		this.badge = badge;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Compte getCompte() {
		return compte;
	}

	public void setCompte(Compte compte) {
		this.compte = compte;
	}

	public StatusName getEtat() {
		return etat;
	}

	public void setEtat(StatusName etat) {
		this.etat = etat;
	}
	

	public Boolean getSuprUser() {
		return suprUser;
	}

	public void setSuprUser(Boolean suprUser) {
		this.suprUser = suprUser;
	}

	public String getNumCompteSysteme() {
		return numCompteSysteme;
	}

	public void setNumCompteSysteme(String numCompteSysteme) {
		this.numCompteSysteme = numCompteSysteme;
	}
	

	public String getAutorisedBy() {
		return autorisedBy;
	}

	public void setAutorisedBy(String autorisedBy) {
		this.autorisedBy = autorisedBy;
	}

	public OperationAutorisation(Long id, Date dateOp, double montantOp, String narrative, Long badge, 
			String numCompteSysteme, Compte compte) {
		super();
		this.id = id;
		this.dateOp = dateOp;
		this.montantOp = montantOp;
		this.narrative = narrative;
		this.badge = badge;
		this.numCompteSysteme = numCompteSysteme;
		this.compte = compte;
	}
	
	
	
}
