package com.aatout.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aatout.model.Depot;

public interface DepotRepository extends JpaRepository<Depot, Long> {
	public Depot findById(Long id);
	public List<Depot> findBySuprIsFalse();
	

}
