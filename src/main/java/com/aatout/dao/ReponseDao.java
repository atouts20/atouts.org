package com.aatout.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aatout.model.Reponse;

public interface ReponseDao extends JpaRepository<Reponse, Long> {

	public List<Reponse> findByStatusAndSuprUser(boolean status,boolean supr);
	public List<Reponse> findByStatusAndSuprUserAndMobilisationReponse_Id(boolean status,boolean suprUser, long idMobilisation);

}
