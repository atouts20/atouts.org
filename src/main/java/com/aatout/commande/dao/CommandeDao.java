package com.aatout.commande.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aatout.commande.model.Commande;
import com.aatout.model.AppUser;


public interface CommandeDao extends JpaRepository<Commande, Long>{
	public Commande findById(Long id);
	public List<Commande> findByStatusAndPayer(boolean status, boolean payer);
	public List<Commande> findByStatusAndSupr(boolean status, boolean supr);
	public List<Commande> findByStatusAndSuprAndPayerAndClient_Id(boolean status, boolean supr,boolean payer, Long client );
	
}
