package com.aatout.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aatout.model.AppUser;
import com.aatout.model.Bloquer;


public interface BloquerRepository extends JpaRepository<Bloquer, Long> {

	public List<Bloquer> findByAppUserBloquer_Id(long id);
}
