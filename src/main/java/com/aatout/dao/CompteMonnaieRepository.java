package com.aatout.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.aatout.model.AppUser;
import com.aatout.model.Compte;
import com.aatout.model.CompteMonnaie;
import com.aatout.model.CompteValeur;

public interface CompteMonnaieRepository extends JpaRepository<CompteMonnaie, Long> {
	
	@Query("From Compte b Where b.supr= false and b.active = true and b.fermer= false and b.id =:id")
	public Compte listCompteMonnaieById(@Param("id") Long id);
	
	@Query("From Compte b Where b.supr= false and b.fermer= false and b.active = true")
	List<Compte>listCompteMonnaie();
	
	public CompteMonnaie findByNumCompte(String numCompte);
	
	public CompteMonnaie findBySuprAndFermerAndActiveAndAppUserCompte(boolean supr, boolean fermer, boolean active, AppUser appUser);


	public CompteMonnaie findByTransAndAppUserCompte(boolean trans, AppUser appUser);
}
