package com.aatout.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value="RETR_EN")
public class RetraitEn extends OperationAutorisation implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RetraitEn() {
		// TODO Auto-generated constructor stub
	}

	public RetraitEn(Long id, Date dateOp, double montantOp, String narrative, Long badge, 
			String numCompteSysteme, Compte compte) {
		super(id, dateOp, montantOp, narrative, badge, numCompteSysteme, compte);
		// TODO Auto-generated constructor stub
	}

	

}
