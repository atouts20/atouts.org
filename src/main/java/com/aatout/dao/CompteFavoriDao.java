package com.aatout.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aatout.model.CompteFavori;

public interface CompteFavoriDao extends JpaRepository<CompteFavori, Long>{
	List<CompteFavori> findByStatus(boolean status);

}
