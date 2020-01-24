package com.aatout.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.aatout.parametre.model.DBFile;

//import sun.management.Agent;

import java.util.List;

@Repository
public interface DBFileRepository extends JpaRepository<DBFile, String> {

    List<DBFile> findByFileNameLikeAndFileTypeEquals(String param1, Boolean Param2);


}
