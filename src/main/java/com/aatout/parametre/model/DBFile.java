package com.aatout.parametre.model;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;

import com.aatout.model.AppUser;
import com.aatout.modelBase.EntityBaseBean;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "t_Pieces")
public class DBFile extends EntityBaseBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;

	private String fileName;

	private String fullFileName;

	private String fileType;


    @ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE})
    private AppUser appUser;



	public DBFile() {
		super();
	}

	public DBFile(String fileName, String fileType) {
		this.fileName = fileName;
		this.fileType = fileType;
	}


	public String getFileName() {
		return fileName;
	}



	public void setFileName(String fileName) {
		this.fileName = fileName;
	}



	public String getFullFileName() {
		return fullFileName;
	}



	public void setFullFileName(String fullFileName) {
		this.fullFileName = fullFileName;
	}



	public String getFileType() {
		return fileType;
	}



	public void setFileType(String fileType) {
		this.fileType = fileType;
	}


	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}


	@JsonIgnore
	public AppUser getAppUser() {
		return appUser;
	}



	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}
	
    
	
}
