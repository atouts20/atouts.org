package com.aatout.commande.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.aatout.model.AppUser;
import com.aatout.model.Services;
import com.aatout.modelBase.EntityBaseBean;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="t_CommandeService")
public class CommandeService extends EntityBaseBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date;
    private boolean accepter = false;
    private boolean annuler = false;
    private boolean supr = false;
	private String livraison;
	private String detail;
	
    @OneToOne
    private Services service;
    
    @ManyToOne
    private AppUser client;
    
    /*@OneToMany(mappedBy="commandeService", fetch = FetchType.LAZY)
	private List<OperationTransaction> operationsTr = new ArrayList<>();
    */

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public boolean isAccepter() {
		return accepter;
	}

	public void setAccepter(boolean accepter) {
		this.accepter = accepter;
	}
	
	

	public boolean isAnnuler() {
		return annuler;
	}

	public void setAnnuler(boolean annuler) {
		this.annuler = annuler;
	}

	public boolean isSupr() {
		return supr;
	}

	public void setSupr(boolean supr) {
		this.supr = supr;
	}

	public String getLivraison() {
		return livraison;
	}

	public void setLivraison(String livraison) {
		this.livraison = livraison;
	}	

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public Services getService() {
		return service;
	}

	public void setService(Services service) {
		this.service = service;
	}

	public AppUser getClient() {
		return client;
	}

	public void setClient(AppUser client) {
		this.client = client;
	}

	/*@JsonIgnore
	public List<OperationTransaction> getOperationsTr() {
		return operationsTr;
	}

	public void setOperationsTr(List<OperationTransaction> operationsTr) {
		this.operationsTr = operationsTr;
	}*/

	public CommandeService() {
		super();
		// TODO Auto-generated constructor stub
	}

	/*public CommandeService(Boolean status, Date createdAt, Date updatedAt, String createBy, String deleteBy) {
		super(status, createdAt, updatedAt, createBy, deleteBy);
		// TODO Auto-generated constructor stub
	}

	public CommandeService(Date createdAt, Date updatedAt, String createBy, String deleteBy) {
		super(createdAt, updatedAt, createBy, deleteBy);
		// TODO Auto-generated constructor stub
	}*/

	/*public CommandeService(Long id, Date date, boolean accepter, boolean supr, String livraison, Services service,
			AppUser client, List<OperationTransaction> operationsTr) {
		super();
		this.id = id;
		this.date = date;
		this.accepter = accepter;
		this.supr = supr;
		this.livraison = livraison;
		this.service = service;
		this.client = client;
		this.operationsTr = operationsTr;
	}
	*/
	
    
    

}
