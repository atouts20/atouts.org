package com.aatout.commande.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aatout.commande.model.CommandeService;
import com.aatout.model.AppUser;

public interface CommandeServiceDao extends JpaRepository<CommandeService, Long>{
	public CommandeService findById(Long id);
	public List<CommandeService> findByStatusAndAnnulerAndAccepter(boolean status, boolean annuler , boolean accepter);
	public List<CommandeService> findByStatusAndSuprAndAccepterAndClient_Id(boolean status, boolean Supr, boolean accepter, Long client);

}
