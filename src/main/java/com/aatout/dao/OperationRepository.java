package com.aatout.dao;


import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.aatout.model.Operation;

public interface OperationRepository extends JpaRepository<Operation, Long>{
	/*@Query("select o from Operation o where o.compte.numCompte like :x order by o.createdAt desc")
	public Page<Operation> listOperation(@Param("x")String mc,Pageable pageable);*/
	public Operation findBySuprIsFalseAndId(Long id);
	
	public List<Operation> findBySuprIsFalse();
	
	public List<Operation> findBySuprIsFalseAndDateOp(Date dateOp);//egale
	
	public List<Operation> findBySuprIsFalseAndDateOpBetween(Date dateOp1, Date dateOp2);//entre 2 dates
	
	public List<Operation> findBySuprIsFalseAndDateOpBetweenAndCompte_Id(Date dateOp1, Date dateOp2, Long num);//entre 2 dates
	
	public List<Operation> findBySuprIsFalseAndDateOpGreaterThan(Date dateOp);// superieur
	
	public List<Operation> findBySuprIsFalseAndDateOpLessThan(Date dateOp);// inferieur
	
	public List<Operation> findBySuprAndAutorisedBy(boolean supr, String username);
	
	public List<Operation> findByCompte_NumCompte(String numcompte);
	
	@Query("select MAX(id) from Operation")
	public Long getMax();
	
	
	
		
}
