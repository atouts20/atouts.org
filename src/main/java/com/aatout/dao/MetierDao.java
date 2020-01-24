package com.aatout.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aatout.model.Metier;

public interface MetierDao extends JpaRepository<Metier, Long> {

}
