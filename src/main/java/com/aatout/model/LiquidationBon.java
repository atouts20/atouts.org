package com.aatout.model;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value="LIBN")
public class LiquidationBon extends Operation implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LiquidationBon() {
		// TODO Auto-generated constructor stub
	}
}
