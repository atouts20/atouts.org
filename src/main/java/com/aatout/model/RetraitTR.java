package com.aatout.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value="RETR_TR")
public class RetraitTR extends Operation {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RetraitTR() {
		// TODO Auto-generated constructor stub
	}
}
