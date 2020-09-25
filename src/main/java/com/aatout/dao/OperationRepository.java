package com.aatout.dao;


import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.aatout.commande.model.Commande;
import com.aatout.commande.model.CommandeService;
import com.aatout.model.Operation;
import com.aatout.model.RetraitTR;

public interface OperationRepository extends JpaRepository<Operation, Long>{
	/*@Query("select o from Operation o where o.compte.numCompte like :x order by o.createdAt desc")
	public Page<Operation> listOperation(@Param("x")String mc,Pageable pageable);*/
	public Operation findBySuprIsFalseAndId(Long id);
	
	public List<Operation> findBySuprIsFalse(); 
	
	public List<Operation> findBySuprIsFalseAndCompteAtoutIsFalseAndDateOpAndCompte_Id(Date dateOp, Long num);//egale
	
	//public List<Operation> findBySuprIsFalseAndCompteAtoutIsFalseAndDateOpBetweenAndCompte_Id(Date dateOp1, Date dateOp2);//entre 2 dates
	
	public List<Operation> findBySuprIsFalseAndCompteAtoutIsFalseAndDateOpBetweenAndCompte_Id(Date dateOp1, Date dateOp2, Long num);//entre 2 dates
	
	public List<Operation> findBySuprIsFalseAndCompteAtoutIsFalseAndDateOpGreaterThanAndCompte_Id(Date dateOp, Long num);// superieur
	
	public List<Operation> findBySuprIsFalseAndCompteAtoutIsFalseAndDateOpLessThanAndCompte_Id(Date dateOp, Long num);// inferieur
	
	public List<Operation> findBySuprAndAutorisedBy(boolean supr, String username);
	
	public List<Operation> findByCompte_NumCompte(String numcompte);
	
	@Query("select MAX(id) from Operation o where o.opManuelle=true")
	public Long getMax();
	

	public List<Operation> findByCommande(Commande commande);
	public List<Operation> findByCommandeService(CommandeService commandeService);
	
		
}
