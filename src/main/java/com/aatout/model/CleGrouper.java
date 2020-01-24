package com.aatout.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
@Embeddable
public class CleGrouper implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Basic(optional = false)
	@NotNull
	@Column(name = "appUser_id", nullable = false)
	private Long appUser_id;

	@Basic(optional = false)
	@NotNull
	@Column(name = "groupe_id", nullable = false)
	private String groupe_id;

	public Long getAppUser_id() {
		return appUser_id;
	}

	public void setAppUser_id(Long appUser_id) {
		this.appUser_id = appUser_id;
	}

	public String getGroupe_id() {
		return groupe_id;
	}

	public void setGroupe_id(String groupe_id) {
		this.groupe_id = groupe_id;
	}

	public CleGrouper() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CleGrouper(Long appUser_id, String groupe_id) {
		super();
		this.appUser_id = appUser_id;
		this.groupe_id = groupe_id;
	}

	@Override
	public String toString() {
		return "CleGrouper [appUser_id=" + appUser_id + ", groupe_id=" + groupe_id + "]";
	}






}
