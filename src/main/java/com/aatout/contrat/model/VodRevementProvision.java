package com.aatout.contrat.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value="VOD_REV_PR")
public class VodRevementProvision extends Planification {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
