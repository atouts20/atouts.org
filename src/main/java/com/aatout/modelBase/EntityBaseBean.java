package com.aatout.modelBase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class EntityBaseBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Boolean status = false;
	
	@Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
    private Date createdAt;

	@Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;

	private String createBy;

	private String deleteBy;
	
	private int nbrMAJ = 0; // pour calculer le nombre de modification
	
	public int getNbrMAJ() {
		return nbrMAJ;
	}


	public void setNbrMAJ(int nbrMAJ) {
		this.nbrMAJ = nbrMAJ;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public EntityBaseBean() {
		super();
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}


	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getDeleteBy() {
		return deleteBy;
	}

	public void setDeleteBy(String deleteBy) {
		this.deleteBy = deleteBy;
	}
	
	public EntityBaseBean(Boolean status, Date createdAt, Date updatedAt, String createBy, String deleteBy,
			int nbrMAJ) {
		super();
		this.status = status;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.createBy = createBy;
		this.deleteBy = deleteBy;
		this.nbrMAJ = nbrMAJ;
	}


		// Methode de calcule du nombre de modification
		@PostUpdate
		private void updateNbrMAJ() {
			this.nbrMAJ++;

		}


}