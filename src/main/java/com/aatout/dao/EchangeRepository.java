package com.aatout.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.aatout.model.Echange;

public interface EchangeRepository extends JpaRepository<Echange, Long>{
	
	@Query("From Echange Ec Where Ec.supr = false and Ec.accepter = false and Ec.active = false")
	List<Echange>listEchangesAaccepter();
	
	@Query("From Echange Ec Where Ec.supr = false and Ec.accepter = true and Ec.active = false")
	List<Echange>listEchangesAactiver();
	
	@Query("From Echange Ec Where Ec.supr = false and Ec.accepter = true and Ec.active = true")
	List<Echange>listEchanges();
	
	@Query("From Echange Ec Where Ec.supr = false and Ec.accepter = true and Ec.active = true and Ec.id =:id")
	public Echange listEchangeById(@Param("id") Long id);
	
	@Query("From Echange Ec Where Ec.supr= false and Ec.active = true and Ec.nom like :x")
	public Page<Echange> chercherEchange(@Param("x")String mc, Pageable pageable);
	
	
	public Page<Echange> findBySuprIsFalseAndActiveIsFalseAndAccepterIsFalseAndProprietaires_Id( Pageable pageable, long proprietaires);
	
	public Page<Echange> findBySuprIsFalseAndActiveIsFalseAndAccepterIsTrueAndProprietaires_Id( Pageable pageable, long proprietaires);
	
	public Page<Echange> findBySuprIsFalseAndActiveIsTrueAndAccepterIsTrueAndProprietaires_Id( Pageable pageable, long proprietaires);
	
	public List<Echange> findBySuprIsFalseAndActiveIsTrueAndAccepterIsTrue();


}
