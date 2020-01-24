package com.aatout.dao;


import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.aatout.model.Facture;


public interface FactureRepository extends JpaRepository<Facture, Long>{
	@Query("select c from Facture c where c.dateFacture = :x")
	public Page<Facture> chercherDate(@Param("x")Date mc,Pageable pageable);
}
