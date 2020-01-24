package com.aatout.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.aatout.model.Retrait;

public interface RetraitRepository extends JpaRepository<Retrait, Long> {
	public Retrait findById(Long id);
	public List<Retrait> findBySuprIsFalse();

}
