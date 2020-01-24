package com.aatout.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value="DEPO_EN")
public class DepotEn extends OperationAutorisation implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DepotEn() {
		// TODO Auto-generated constructor stub
	}

	public DepotEn(Long id, Date dateOp, double montantOp, String narrative, Long badge, 
			String numCompteSysteme, Compte compte) {
		super(id, dateOp, montantOp, narrative, badge, numCompteSysteme, compte);
		// TODO Auto-generated constructor stub
	}	
	
}
