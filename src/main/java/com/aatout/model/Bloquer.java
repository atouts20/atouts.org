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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.aatout.modelBase.EntityBaseBean;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="t_Bloquer")
public class Bloquer extends EntityBaseBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Temporal(TemporalType.DATE)
	private Date dateBloquage = new Date();
	
	private String motif;
	
	@ManyToOne
	private AppUser appUserBloquer;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDateBloquage() {
		return dateBloquage;
	}

	public void setDateBloquage(Date dateBloquage) {
		this.dateBloquage = dateBloquage;
	}

	public String getMotif() {
		return motif;
	}

	public void setMotif(String motif) {
		this.motif = motif;
	}

	@JsonIgnore
	public AppUser getAppUserBloquer() {
		return appUserBloquer;
	}

	public void setAppUserBloquer(AppUser appUserBloquer) {
		this.appUserBloquer = appUserBloquer;
	}

	public Bloquer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Bloquer(Boolean status, Date createdAt, Date updatedAt, String createBy, String deleteBy, int nbrMAJ) {
		super(status, createdAt, updatedAt, createBy, deleteBy, nbrMAJ);
		// TODO Auto-generated constructor stub
	}

	public Bloquer(String motif, AppUser appUserBloquer) {
		super();
		this.motif = motif;
		this.appUserBloquer = appUserBloquer;
	}

	@Override
	public String toString() {
		return "Bloquer [id=" + id + ", dateBloquage=" + dateBloquage + ", motif=" + motif + ", appUserBloquer="
				+ appUserBloquer + "]";
	}
	
		
	
}
