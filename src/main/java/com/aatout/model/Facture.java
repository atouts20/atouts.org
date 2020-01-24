package com.aatout.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.aatout.modelBase.EntityBaseBean;

@Entity
@Table(name="T_FACTURE")
public class Facture extends EntityBaseBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Temporal(TemporalType.DATE)
	private Date dateFacture;
	
	private Double totale;
	
	private boolean supprime;
	
	
	/*@OneToMany(mappedBy="facture")
	private List<Facturer> facturers=new ArrayList<>();
	*/
	
	@ManyToOne
	private Groupe groupe;
	
	@ManyToOne
	private AppUser appUser;
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getDateFacture() {
		return dateFacture;
	}
	public void setDateFacture(Date dateFacture) {
		this.dateFacture = dateFacture;
	}
	
	public Double getTotale() {
		return totale;
	}
	public void setTotale(Double totale) {
		this.totale = totale;
	}
	
	public boolean isSupprime() {
		return supprime;
	}
	public void setSupprime(boolean supprime) {
		this.supprime = supprime;
	}
	
	public Groupe getGroupe() {
		return groupe;
	}
	public void setGroupe(Groupe groupe) {
		this.groupe = groupe;
	}
	public AppUser getAppUser() {
		return appUser;
	}
	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}
	
	
	public Facture() {
		super();
		// TODO Auto-generated constructor stub
	}
	
		
	
}
