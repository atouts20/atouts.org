package com.aatout.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class CleFacturer implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String codeFacture;
	private Long codeBien;
	public String getCodeFacture() {
		return codeFacture;
	}
	public void setCodeFacture(String codeFacture) {
		this.codeFacture = codeFacture;
	}
	
	public Long getCodeBien() {
		return codeBien;
	}
	public void setCodeBien(Long codeBien) {
		this.codeBien = codeBien;
	}
	public CleFacturer() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CleFacturer(String codeFacture, Long codeBien) {
		super();
		this.codeFacture = codeFacture;
		this.codeBien = codeBien;
	}
	
	
	
}
