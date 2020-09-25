package com.aatout.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aatout.model.CompteFavori;

public interface CompteFavoriDao extends JpaRepository<CompteFavori, Long>{
	List<CompteFavori> findByStatusAndAppUserCompte_id(boolean status, long id);
	CompteFavori findByStatusAndNumCompteAndAppUserCompte_id(boolean status, String numCompte, long id);

}
