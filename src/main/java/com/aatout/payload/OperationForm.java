package com.aatout.payload;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.Min;

public class OperationForm {
	private long id;
	private String numCompte;
	private String numCompte2;
	@Min(value = 0L, message = "The value must be positive")
	private double montantOp; 
	private String narrative;
	private Long badge;
	private String type;
	private String createBy;
	private String autorisedBy;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNumCompte() {
		return numCompte;
	}
	public void setNumCompte(String numCompte) {
		this.numCompte = numCompte;
	}
	public String getNumCompte2() {
		return numCompte2;
	}
	public void setNumCompte2(String numCompte2) {
		this.numCompte2 = numCompte2;
	}
	public double getMontantOp() {
		return montantOp;
	}
	public void setMontantOp(double montantOp) {
		this.montantOp = montantOp;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
	
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public String getAutorisedBy() {
		return autorisedBy;
	}
	public void setAutorisedBy(String autorisedBy) {
		this.autorisedBy = autorisedBy;
	}
	@Override
	public String toString() {
		return "OperationForm [id=" + id + ", numCompte=" + numCompte + ", numCompte2=" + numCompte2 + ", montantOp="
				+ montantOp + ", type=" + type + "]";
	}
	
	
	
}
