package com.aatout.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aatout.model.Groupe;

public interface GroupeRepository extends JpaRepository<Groupe, Long> {
	public Groupe findById(String id);
	//public List<Groupe> findBySupr(boolean supr);
	public List<Groupe> findBySuprIsFalse();
}
