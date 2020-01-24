package com.aatout.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
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

import com.aatout.bon.model.Bon;
import com.aatout.commande.model.Commande;
import com.aatout.commande.model.CommandeService;
import com.aatout.modelBase.EntityBaseBean;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@Entity
@Table(name="T_OPERATION")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type", discriminatorType=DiscriminatorType.STRING, length=4)
@JsonTypeInfo( use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes( {
    @Type( name = "DEPO", value = Depot.class ),
    @Type( name = "RETR", value = Retrait.class ),
    @Type( name = "DETR", value = DepotTR.class ),
    @Type( name = "RETR", value = RetraitTR.class ),
    @Type( name = "EMBN", value = EmissionBon.class ),
    @Type( name = "ENBN", value = EncaisseBon.class ),
    @Type( name = "LIBN", value = LiquidationBon.class )
} )

public abstract class Operation extends EntityBaseBean implements Serializable{ 
	/**
	 * 
	 */
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
	@Min(value = 0L, message = "The value must be positive")
	private double balance;
	
	@Min(value = 0L, message = "The value must be positive")
	private double balanceInit;
	
	@ManyToOne
	private Commande commande;
	
	@ManyToOne
	private CommandeService commandeService;
	 
	//private Boolean bloqueRetrait;
	
		
	private Boolean supr = false;
	
	private Boolean opManuelle = false;
	
	private Boolean compteAtout = false;
	
	@ManyToOne
	private Compte compte;
	
	@ManyToOne
	private Bon bon;
	
	@ManyToOne
	private SousCompte sousCompte;
	
	private String autorisedBy;
	
	public Operation(Double montantOp, Compte compte) {
		super();
		this.montantOp = montantOp;
		this.compte = compte;
	}
	

	/*public Boolean getBloqueRetrait() {
		return bloqueRetrait;
	}

	public void setBloqueRetrait(Boolean bloqueRetrait) {
		this.bloqueRetrait = bloqueRetrait;
	}*/
	

	public String getType() {
		return type;
	}


	public double getBalanceInit() {
		return balanceInit;
	}


	public void setBalanceInit(double balanceInit) {
		this.balanceInit = balanceInit;
	}


	public String getNarrative() {
		return narrative;
	}


	public void setNarrative(String narrative) {
		this.narrative = narrative;
	}

	public void setType(String type) {
		this.type = type;
	}


	public Boolean getSupr() {
		return supr;
	}

	public void setSupr(Boolean supr) {
		this.supr = supr;
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

	
	public Compte getCompte() {
		return compte;
	}

	public void setCompte(Compte compte) {
		this.compte = compte;
	}

	public Long getBadge() {
		return badge;
	}


	public void setBadge(Long badge) {
		this.badge = badge;
	}

	public String getAutorisedBy() {
		return autorisedBy;
	}


	public void setAutorisedBy(String autorisedBy) {
		this.autorisedBy = autorisedBy;
	}


	public Commande getCommande() {
		return commande;
	}


	public void setCommande(Commande commande) {
		this.commande = commande;
	}


	public SousCompte getSousCompte() {
		return sousCompte;
	}


	public void setSousCompte(SousCompte sousCompte) {
		this.sousCompte = sousCompte;
	}


	public CommandeService getCommandeService() {
		return commandeService;
	}


	public void setCommandeService(CommandeService commandeService) {
		this.commandeService = commandeService;
	}

	
	public Boolean getCompteAtout() {
		return compteAtout;
	}


	public void setCompteAtout(Boolean compteAtout) {
		this.compteAtout = compteAtout;
	}


	public Bon getBon() {
		return bon;
	}


	public void setBon(Bon bon) {
		this.bon = bon;
	}


	public Boolean getOpManuelle() {
		return opManuelle;
	}


	public void setOpManuelle(Boolean opManuelle) {
		this.opManuelle = opManuelle;
	}


	public double getBalance() {
		return balance;
	}


	public void setBalance(double balance) {
		this.balance = balance;
	}


	public Operation() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public Operation(Long id, Date dateOp, double montantOp, String narrative, Long badge, Compte compte) {
		super();
		this.id = id;
		this.dateOp = dateOp;
		this.montantOp = montantOp;
		this.narrative = narrative;
		this.badge = badge;
		this.compte = compte;
	}
	
	public Operation(Long id, Date dateOp, double montantOp, String narrative, Compte compte) {
		super();
		this.id = id;
		this.dateOp = dateOp;
		this.montantOp = montantOp;
		this.narrative = narrative;
		this.compte = compte;
	}


	@Override
	public String toString() {
		return "Operation [id=" + id + ", type=" + type + ", balance=" + balance + ", compte=" + compte + "]";
	}

	
}
