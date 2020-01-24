package com.aatout.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.aatout.bon.model.Bon;

@Entity
@DiscriminatorValue(value="EMBN")
public class EmissionBon extends Operation implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EmissionBon() {
		// TODO Auto-generated constructor stub
	}
	
}
