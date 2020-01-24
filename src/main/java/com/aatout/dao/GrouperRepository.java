package com.aatout.dao;

import java.util.List;


import com.aatout.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import com.aatout.model.CleGrouper;
import com.aatout.model.Groupe;
import com.aatout.model.Grouper;

public interface GrouperRepository extends JpaRepository<Grouper, Long> {
	public Grouper findByPkGrouper(CleGrouper pkGrouper);
	public List<Grouper> findByGroupe(Groupe groupe);
	
	public List<Grouper> findByAppUser_Id(Long id);
	
	public Grouper findByAppUser(AppUser appUser);
	
	

}
