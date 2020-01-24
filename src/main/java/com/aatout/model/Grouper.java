package com.aatout.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
 


@Entity
@Table(name ="t_grouper")
public class Grouper implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private CleGrouper pkGrouper;
	
	private Double partmn;
	
	private Double partvl;
	
	@JoinColumn(name = "groupe_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)	
	 @ManyToOne(optional = false)
	 private Groupe groupe;
	 
	 @JoinColumn(name = "appUser_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
	 @ManyToOne(optional = false)
	 private AppUser appUser;
	 
	 
	 @Temporal(TemporalType.TIMESTAMP)
		@LastModifiedDate
		private Date updateAt;
		
		
		@CreatedDate
		private Date createAt; 

	public CleGrouper getPkGrouper() {
		return pkGrouper;
	}

	public void setPkGrouper(CleGrouper pkGrouper) {
		this.pkGrouper = pkGrouper;
	}	

	/*public Double getPart() {
		return part;
	}

	public void setPart(Double part) {
		this.part = part;
	}*/

	public Double getPartmn() {
		return partmn;
	}

	public Date getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(Date updateAt) {
		this.updateAt = updateAt;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public void setPartmn(Double partmn) {
		this.partmn = partmn;
	}

	public Double getPartvl() {
		return partvl;
	}

	public void setPartvl(Double partvl) {
		this.partvl = partvl;
	}

	public Groupe getGroupe() {
		return groupe;
	}

	public void setGroupe(Groupe groupe) {
		this.groupe = groupe;
	}

	public AppUser getAppUser() {
		return appUser;
	}

	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Grouper() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Grouper(CleGrouper pkGrouper, Double partmn, Double partvl, Groupe groupe, AppUser appUser) {
		super();
		this.pkGrouper = pkGrouper;
		this.partmn = partmn;
		this.partvl = partvl;
		this.groupe = groupe;
		this.appUser = appUser;
	}

	@Override
	public String toString() {
		return "Grouper [pkGrouper=" + pkGrouper + ", partmn=" + partmn + ", partvl=" + partvl + ", groupe=" + groupe
				+ ", appUser=" + appUser + "]";
	}
	
	

	/*public Grouper(CleGrouper pkGrouper, Double part, Groupe groupe, AppUser appUser) {
		super();
		this.pkGrouper = pkGrouper;
		this.part = part;
		this.groupe = groupe;
		this.appUser = appUser;
	}*/
	
	 

}
