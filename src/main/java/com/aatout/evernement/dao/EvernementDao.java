package com.aatout.evernement.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aatout.evernement.model.Evernement;

public interface EvernementDao extends JpaRepository<Evernement, Long>{

}
