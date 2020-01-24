package com.aatout.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.aatout.bon.model.Bon;

@Entity
@DiscriminatorValue(value="ENBN")
public class EncaisseBon extends Operation implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EncaisseBon() {
		// TODO Auto-generated constructor stub
	}
}
