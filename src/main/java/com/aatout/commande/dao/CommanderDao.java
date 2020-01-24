package com.aatout.commande.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aatout.commande.model.Commander;

public interface CommanderDao extends JpaRepository<Commander, Long>{

}
