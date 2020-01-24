package com.aatout.contrat.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value="VOD_PRO")
public class VodProvision extends Planification {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
