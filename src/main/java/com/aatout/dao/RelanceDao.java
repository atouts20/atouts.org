package com.aatout.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aatout.model.Relance;

public interface RelanceDao extends JpaRepository<Relance, Long>{
	
	public List<Relance> findByStatusAndSuprUser(boolean status,boolean supr);
	public List<Relance> findByStatusAndSuprUserAndMobilisationRelance_Id(boolean status, boolean suprUser, long id);

}
