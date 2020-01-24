package com.aatout.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.aatout.model.AppUser;
import com.aatout.model.Compte;
import com.aatout.model.CompteMonnaie;
import com.aatout.model.CompteValeur;

public interface CompteValeurRepository extends JpaRepository<CompteValeur, String> {
	@Query("From Compte b Where b.supr= false and b.active = true and b.fermer= false and b.id =:id")
	public Compte listCompteValeurById(@Param("id") Long id);

	@Query("From Compte b Where b.supr= false and b.fermer= false and b.active = true")
	List<Compte>listCompteValeur();

	public CompteValeur findBySuprAndFermerAndActiveAndPin(boolean supr, boolean fermer, boolean active, long pin );
	public CompteValeur findByPin(long pin);

	public CompteValeur findByAppUserCompte(AppUser appUser);
	

	public CompteValeur findByTransAndAppUserCompte(boolean trans, AppUser appUser);
	
	public CompteValeur findBySuprAndFermerAndActiveAndTransAndNumCompte(boolean supr, boolean fermer, boolean active, boolean trans, String numCompte);
	public CompteValeur findBySuprAndFermerAndActiveAndTransAndAppUserCompte_Id(boolean supr, boolean fermer, boolean active, boolean trans, long appUser);
	
	
}
