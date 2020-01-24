package com.aatout.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aatout.model.Mobilisation;

public interface MobilisationDao extends JpaRepository<Mobilisation, String> {

	public List<Mobilisation> findByStatusAndSuprUserAndDemande(boolean status,boolean supr, boolean demande);
	public List<Mobilisation> findByStatusAndSuprUserAndDemandeAndProprietaire(boolean status,boolean supr, boolean demande, long idUser);
	public Mobilisation findByStatusAndSuprUserAndDemandeAndId(boolean status,boolean supr, boolean demande, long idMobilisation);
	public List<Mobilisation> findByStatusAndSuprUserAndDemandeAndProprietaire_Id(boolean status,boolean supr, boolean demande,long idUser);
	//public List<Mobilisation> findByStatusIsFalseAndSuprUserIsFalseAndDemandeIsFalseAndProprietaire_Id( Long idUser);


}
