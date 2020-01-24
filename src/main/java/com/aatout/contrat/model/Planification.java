package com.aatout.contrat.model;

import java.io.Serializable;

import javax.annotation.Generated;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.aatout.model.Depot;
import com.aatout.model.DepotTR;
import com.aatout.model.EmissionBon;
import com.aatout.model.EncaisseBon;
import com.aatout.model.LiquidationBon;
import com.aatout.model.Retrait;
import com.aatout.model.RetraitTR;
import com.aatout.modelBase.EntityBaseBean;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;

@Entity
@Table(name="T_PLANIFICATION")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type", discriminatorType=DiscriminatorType.STRING, length=8)
@JsonTypeInfo( use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes( {
    @Type( name = "VOD_ET", value = VodET.class ),
    @Type( name = "VOD_ED", value = VodED.class ),
    @Type( name = "VOD_AVA_ED", value = VodAvanceED.class ),
    @Type( name = "VOD_REV_PR", value = VodRevementProvision.class ),
    @Type( name = "VOD_PRO", value = VodProvision.class )
} )
public abstract class Planification extends EntityBaseBean implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

}
