package com.aatout.contrat.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value="VOD_ET")
public class VodET extends Planification {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
