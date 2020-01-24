package com.aatout.evernement.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.aatout.modelBase.EntityBaseBean;
@Entity
@Table(name="t_Evernement")
public class Evernement extends EntityBaseBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Long id;
	
	private String nom;
	
	private String description;
	
	private String invite;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateFin;
	

}
