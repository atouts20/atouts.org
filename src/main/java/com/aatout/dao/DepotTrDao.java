package com.aatout.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aatout.commande.model.Commande;
import com.aatout.model.DepotTR;

public interface DepotTrDao extends JpaRepository<DepotTR, Long> {
	public List<DepotTR> findByCommande(Commande commande);
}
