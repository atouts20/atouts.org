package com.aatout.contrat.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value="VOD_AVA_ED")
public class VodAvanceED extends Planification {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
