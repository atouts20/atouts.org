package com.aatout.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aatout.model.SousCompte;

public interface SousCompteDao extends JpaRepository<SousCompte, Long> {
	List<SousCompte> findByStatus(boolean status);
}
