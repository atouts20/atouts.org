package com.aatout.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.aatout.model.Pays;

import java.util.List;

@Repository
public interface PaysDao extends JpaRepository<Pays, Long> {
    Pays findBySortOrderIs(String id);
    //Pays findByIdLike(String id);
   // List<Pays> findByStatus(Boolean status);
}
