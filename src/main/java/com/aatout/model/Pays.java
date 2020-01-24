package com.aatout.model;
import com.aatout.modelBase.EntityBaseBean;

import javax.persistence.*;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name = "t_Pays")
public class Pays implements Serializable {
	@Id
	@Column(nullable= false)
    private String sortOrder;
    private String sommo_Name;
    private String formal_Name;
    private String type;
    private String sub_Type;
    private String sovereignty;
    private String capital;
    private String iSO_4217_Currency_Code;
    private String iSO_4217_Currency_Name;
    private String iTU_T_Telephone_Code;
    private String iSO_3166_1_2_Letter_Code;
    private String iSO_3166_1_3_Letter_Code;
    private String iSO_3166_1_Number;
    private String iANA_Country_Code_TLD;
    
    
    
	public Pays() {
		// TODO Auto-generated constructor stub
	}

	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}

	public String getSommo_Name() {
		return sommo_Name;
	}



	public void setSommo_Name(String sommo_Name) {
		this.sommo_Name = sommo_Name;
	}



	public String getFormal_Name() {
		return formal_Name;
	}



	public void setFormal_Name(String formal_Name) {
		this.formal_Name = formal_Name;
	}



	public String getType() {
		return type;
	}



	public void setType(String type) {
		this.type = type;
	}



	public String getSub_Type() {
		return sub_Type;
	}



	public void setSub_Type(String sub_Type) {
		this.sub_Type = sub_Type;
	}



	public String getSovereignty() {
		return sovereignty;
	}



	public void setSovereignty(String sovereignty) {
		this.sovereignty = sovereignty;
	}



	public String getCapital() {
		return capital;
	}



	public void setCapital(String capital) {
		this.capital = capital;
	}



	public String getiSO_4217_Currency_Code() {
		return iSO_4217_Currency_Code;
	}



	public void setiSO_4217_Currency_Code(String iSO_4217_Currency_Code) {
		this.iSO_4217_Currency_Code = iSO_4217_Currency_Code;
	}



	public String getiSO_4217_Currency_Name() {
		return iSO_4217_Currency_Name;
	}



	public void setiSO_4217_Currency_Name(String iSO_4217_Currency_Name) {
		this.iSO_4217_Currency_Name = iSO_4217_Currency_Name;
	}



	public String getiTU_T_Telephone_Code() {
		return iTU_T_Telephone_Code;
	}



	public void setiTU_T_Telephone_Code(String iTU_T_Telephone_Code) {
		this.iTU_T_Telephone_Code = iTU_T_Telephone_Code;
	}



	public String getiSO_3166_1_2_Letter_Code() {
		return iSO_3166_1_2_Letter_Code;
	}



	public void setiSO_3166_1_2_Letter_Code(String iSO_3166_1_2_Letter_Code) {
		this.iSO_3166_1_2_Letter_Code = iSO_3166_1_2_Letter_Code;
	}



	public String getiSO_3166_1_3_Letter_Code() {
		return iSO_3166_1_3_Letter_Code;
	}



	public void setiSO_3166_1_3_Letter_Code(String iSO_3166_1_3_Letter_Code) {
		this.iSO_3166_1_3_Letter_Code = iSO_3166_1_3_Letter_Code;
	}



	public String getiSO_3166_1_Number() {
		return iSO_3166_1_Number;
	}



	public void setiSO_3166_1_Number(String iSO_3166_1_Number) {
		this.iSO_3166_1_Number = iSO_3166_1_Number;
	}



	public String getiANA_Country_Code_TLD() {
		return iANA_Country_Code_TLD;
	}



	public void setiANA_Country_Code_TLD(String iANA_Country_Code_TLD) {
		this.iANA_Country_Code_TLD = iANA_Country_Code_TLD;
	}
	
	
}
