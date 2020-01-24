package com.aatout.contrat.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value="VOD_ED")
public class VodED extends Planification {

}
