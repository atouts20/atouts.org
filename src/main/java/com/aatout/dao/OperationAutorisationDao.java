package com.aatout.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.aatout.enums.StatusName;
import com.aatout.model.OperationAutorisation;

public interface OperationAutorisationDao extends JpaRepository<OperationAutorisation, Long> {
	public List<OperationAutorisation> findByStatusAndSuprUserAndEtat(boolean status, boolean suprUser, StatusName etat);
	
	public OperationAutorisation findByStatusIsFalseAndSuprUserIsFalseAndId(Long id);
	
    @Query("select MAX(id) from OperationAutorisation")
	public Long getMaxEn();

}
