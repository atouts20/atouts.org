package com.aatout.dao;

import com.aatout.model.AppRole;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoleRepository extends JpaRepository<AppRole, Long> {
	public AppRole findByRoleName(String roleName);
	public Optional<AppRole>  findById(Long id);
	/*@Query("select * From AppRole")
	public AppRole findAppRole(AppRole appRole);
	*/
	
}
