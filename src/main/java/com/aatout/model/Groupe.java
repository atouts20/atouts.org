package com.aatout.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
@Entity
@Table(name= "t_groupe")
public class Groupe implements Serializable{
	@Id
	private String id;
	private String nomGroupe;
	private boolean supr;
	
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	private Date updateAt;
	
	
	@CreatedDate
	private Date createAt;
	
	
	@OneToMany(mappedBy="groupe")
	private List<Grouper> groupers= new ArrayList<>();
	
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNomGroupe() {
		return nomGroupe;
	}
	public void setNomGroupe(String nomGroupe) {
		this.nomGroupe = nomGroupe;
	}
	public boolean isSupr() {
		return supr;
	}
	public void setSupr(boolean supr) {
		this.supr = supr;
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
	
	@JsonIgnore
	public List<Grouper> getGroupers() {
		return groupers;
	}
	
	@JsonSetter
	public void setGroupers(List<Grouper> groupers) {
		this.groupers = groupers;
	}
	
	public Groupe() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Groupe(String id, String nomGroupe, boolean supr) {
		super();
		this.id = id;
		this.nomGroupe = nomGroupe;
		this.supr = supr;
	}	
	
	public Groupe(String id, String nomGroupe, boolean supr, List<Grouper> groupers) {
		super();
		this.id = id;
		this.nomGroupe = nomGroupe;
		this.supr = supr;
		this.groupers = groupers;
	}
	
	
	@Override
	public String toString() {
		return "Groupe [id=" + id + ", nomGroupe=" + nomGroupe + ", supr=" + supr + ", groupers=" + groupers + "]";
	}
	
	
	

}
