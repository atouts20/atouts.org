package com.aatout.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.aatout.modelBase.EntityBaseBean;
@Entity
@Table(name="t_Relance")
public class Relance extends EntityBaseBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private Double prix;
	
	private String observation;
	
	private boolean suprUser;
	
	@ManyToOne
	private Mobilisation mobilisationRelance;

	public Relance() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getPrix() {
		return prix;
	}

	public void setPrix(Double prix) {
		this.prix = prix;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public boolean isSuprUser() {
		return suprUser;
	}

	public void setSuprUser(boolean suprUser) {
		this.suprUser = suprUser;
	}

	public Mobilisation getMobilisationRelance() {
		return mobilisationRelance;
	}

	public void setMobilisationRelance(Mobilisation mobilisationRelance) {
		this.mobilisationRelance = mobilisationRelance;
	}
	
}
