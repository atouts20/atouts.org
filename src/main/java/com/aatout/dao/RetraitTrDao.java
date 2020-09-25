package com.aatout.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aatout.commande.model.Commande;
import com.aatout.commande.model.CommandeService;
import com.aatout.model.RetraitTR;

public interface RetraitTrDao extends JpaRepository<RetraitTR, Long> {
	public List<RetraitTR> findByCommande(Commande commande);
	public List<RetraitTR> findByCommandeService(CommandeService commandeService);
}
