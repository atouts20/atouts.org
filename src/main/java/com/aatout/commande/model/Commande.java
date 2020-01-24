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
import com.aatout.modelBase.EntityBaseBean;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="t_Commande")
public class Commande extends EntityBaseBean implements Serializable{
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date;
    private boolean payer = false;
    @OneToMany(mappedBy = "commande")
    private List<Commander> commanders;
    @ManyToOne
    private AppUser client;
    private double totalAmount;
    private boolean supr = false;
	private String livraison;
    @OneToOne(mappedBy = "commande")
    private Payment payment;
    /*
    @OneToMany(mappedBy="commande", fetch = FetchType.LAZY)
	private List<OperationTransaction> operationsTr = new ArrayList<>();
    
    
    @OneToOne
    private Panier panier;
    
	*/
    
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
	public void setPayer(boolean payer) {
		this.payer = payer;
	}
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
	
	public Boolean getPayer() {
		return payer;
	}
	public void setPayer(Boolean payer) {
		this.payer = payer;
	}
	public List<Commander> getCommanders() {
		return commanders;
	}
	public void setCommanders(List<Commander> commanders) {
		this.commanders = commanders;
	}
	public AppUser getClient() {
		return client;
	}
	public void setClient(AppUser client) {
		this.client = client;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	@JsonIgnore
	public Payment getPayment() {
		return payment;
	}
	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	
	
	
	public Commande() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	   

}
