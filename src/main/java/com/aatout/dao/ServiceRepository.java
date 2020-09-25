package com.aatout.dao;

import java.util.List;

import com.aatout.model.Echange;
import com.aatout.model.Produit;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.aatout.model.Services;

public interface ServiceRepository extends JpaRepository<Services, Long> {
	@Query("From Services s Where s.supr= false and s.active = true")
	List<Services>listService();
	@Query("From Services s Where s.supr= false and s.active = false")
	List<Services>listServicePasActiver();
	
	public List<Services> findBySuprAndAccepterAndActive(boolean sup, boolean accepter, boolean activer);

	@Query("From Services s Where s.supr= false and s.active = true and s.id =:id")
	public Services listServiceById(@Param("id") Long id);

	@Query("From Services s Where s.supr= false and s.active = true and s.nom like :x")
	public Page<Services> chercherService(@Param("x")String mc, Pageable pageable);
	
	public Services findBySuprIsFalseAndActiveIsTrueAndId(Long id);
	
	//public List<Services> findBySuprIsFalseAndActiveIsTrueAndProprietaireUser_Id(Long id);
	
	
	
	//public Page<Services> findBySuprIsFalseAndActiveIsTrueAndProprietaireUser_IdAndNomLikeIgnoreCase(Pageable pageable,long proprietaires, String nom);

	public Page<Services> findBySuprIsFalseAndActiveIsTrueAndNomLikeIgnoreCase(Pageable pageable, String nom);
	
	
	
	
	
	@Query("from Services c where c.supr=false and c.active=true and c.nom like :x and c.proprietaire in (SELECT groupe FROM Grouper r where r.appUser.id =:id)")
	public Page<Services> listServiceByUser(Pageable pageable, @Param("x")String mc, @Param("id")Long id);
	
	
	
	
	public List<Services> findBySuprIsFalseAndActiveIsFalseAndAccepterIsFalseAndProprietaire_Id(long proprietaire);

	public List<Services> findBySuprIsFalseAndActiveIsFalseAndAccepterIsTrueAndProprietaire_Id(long proprietaire);

	public List<Services> findBySuprIsFalseAndActiveIsTrueAndAccepterIsTrueAndProprietaire_Id(long proprietaire);




}
