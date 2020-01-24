package com.aatout.bon.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aatout.bon.model.Bon;

public interface BonDao extends JpaRepository<Bon, Long> {
	List<Bon> findByStatus(Boolean status);
	
	Bon findByStatusIsFalseAndEncaisseIsFalseAndLiquideIsFalseAndId(Long id);
	List<Bon> findByStatusIsFalseAndEncaisseIsFalseAndLiquideIsFalse();
	Bon findByStatusIsFalseAndEncaisseIsFalseAndLiquideIsFalseAndNumeroBonIs(String id);
	List<Bon> findByStatusIsFalseAndEncaisseIsTrueAndLiquideIsFalse();
	List<Bon> findByStatusIsFalseAndEncaisseIsTrueAndLiquideIsTrue();
	Bon findByStatusIsFalseAndEncaisseIsFalseAndLiquideIsFalseAndSecret(Long secret);
			  
 }
