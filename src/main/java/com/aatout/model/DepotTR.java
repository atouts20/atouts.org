package com.aatout.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value="DEPO_TR")
public class DepotTR extends Operation {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DepotTR() {
		// TODO Auto-generated constructor stub
	}
	
}
