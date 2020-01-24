package com.aatout.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import com.aatout.model.Facturer;

public interface FacturerRepository extends JpaRepository<Facturer, Long>{
	
}
