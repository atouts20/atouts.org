package com.aatout.web;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import com.aatout.model.Compte;

public class FormOperation {
	
	//private Depot depotOperation;
	
	private  FormCompte unCompte;
	private  FormCompte unCompte2;
	private  Date   dateOp;
	private  double montantOp;
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long badge;
	private  String type;
	//private  String type;
	public FormCompte getUnCompte() {
		return unCompte;
	}
	public void setUnCompte(FormCompte unCompte) {
		this.unCompte = unCompte;
	}
	public FormCompte getUnCompte2() {
		return unCompte2;
	}
	public void setUnCompte2(FormCompte unCompte2) {
		this.unCompte2 = unCompte2;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public Long getBadge() {
		return badge;
	}
	public void setBadge(Long badge) {
		this.badge = badge;
	}
	@Override
	public String toString() {
		return "FormOperation [unCompte=" + unCompte + ", unCompte2=" + unCompte2 + ", dateOp=" + dateOp
				+ ", montantOp=" + montantOp + ", type=" + type + "]";
	}
	
}
