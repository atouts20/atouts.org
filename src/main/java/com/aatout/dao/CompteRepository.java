package com.aatout.dao;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.aatout.model.AppUser;
import com.aatout.model.Compte;
import com.aatout.model.CompteMonnaie;
import com.aatout.model.CompteValeur;




public interface CompteRepository extends JpaRepository<Compte, String>{
	@Query("select c from Compte c where c.appUserCompte.id = :x")
	public Page<Compte> chercherDate(@Param("x")Long mc,Pageable pageable);
	
	public List<Compte> findBySuprIsFalseAndFermerIsFalseAndActiveIsTrue();
	
	public List<Compte> findBySuprIsFalseAndFermerIsFalseAndActiveIsTrueAndAppUserCompte_id(long idUser);
	
	public  Compte findCompteByActiveIsTrueAndSuprFalseAndFermerIsFalseAndNumCompte(String numCompte);
	
	public  Compte findCompteByActiveIsFalseAndSuprFalseAndFermerIsFalseAndNumCompte(String numCompte);
	
	public List<CompteMonnaie> findCompteMonnaieBySuprIsFalseAndFermerIsFalseAndActiveIsTrue();
	
	public List<CompteValeur> findCompteValeurBySuprIsFalseAndFermerIsFalseAndActiveIsTrue();
	
	public List<CompteValeur> findCompteValeurBySuprIsFalseAndFermerIsFalseAndActiveIsTrueAndAatoutIsTrue();
	
	//public CompteValeur findByAppUserCompte(AppUser appUser);
	

	public CompteValeur findBySuprAndFermerAndActiveAndAppUserCompte(boolean supr, boolean fermer, boolean active, AppUser appUser);
	
	/*@Query("select * from Compte")
	public List<Compte> selectTout();*/
	
	/*@Query("select MAX(id) from Compte c where c.codePays")
	public Long getMax();*/
	
	/*@Query("select c from CompteValeur c where c.codePays and c.MAX(id)")
	public Compte getCompte(String mc);
	*/
	
	/*public Compte findByNumCompteIs(String num);
	
	public Compte findById(long num);
	
	
	//public Compte findTopByIdAndCodePaysLike(String  cc);

	public Compte findTopByIdAndCodePays(String cc);
*/
	//public CompteValeur findByCodePaysAndTopId(String cc);
	
	@Query("select MAX(id) from Compte c where c.codePays =?1")
	public Long getMax(String cc);
	
	public Compte findById(long id);
	
	
}
