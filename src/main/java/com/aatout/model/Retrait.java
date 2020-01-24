package com.aatout.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value="RETR")
public class Retrait extends Operation implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Retrait() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Retrait(Double montantOp, Compte compte) {
		super(montantOp, compte);
		// TODO Auto-generated constructor stub
	}

	
	/*public Retrait(Long id, Date dateOp, Double montantOp, Compte compte, String narrative) {
		super(id, dateOp, montantOp, compte, narrative);
		// TODO Auto-generated constructor stub
	}*/
	
	
	public Retrait(Long id, Date dateOp, double montantOp, String narrative, Long badge, Compte compte) {
		super(id, dateOp, montantOp, narrative, badge, compte);
		// TODO Auto-generated constructor stub
	}
	

	@Override
	public String toString() {
		return "Retrait [getType()=" + getType() + ", getId()=" + getId() + ", getMontantOp()=" + getMontantOp()
				+ ", getCompte()=" + getCompte() + "]";
	}
	
	

}
