package com.aatout.dao;



import java.util.List;

import com.aatout.model.Echange;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.aatout.model.Produit;

public interface ProduitRepository extends JpaRepository<Produit, Long>{
	
	@Query("From Produit b Where b.supr= false and b.active = true and b.id =:id")
	public Produit listProduitById(@Param("id") Long id);
	
	@Query("From Produit b Where b.supr= false and b.active = true")
	List<Produit>listProduit();
	@Query("From Produit b Where b.supr= false and b.active = false")
	List<Produit>listProduitPasActiver();
	
	
	public List<Produit> findBySuprAndAccepterAndActive(boolean sup, boolean accepter, boolean activer);
	
	@Query("select c from Produit c where c.nom like :x")
	public Page<Produit> chercherProduit(@Param("x")String mc, Pageable pageable);
	
	public Produit findBySuprIsFalseAndActiveIsTrueAndId(Long id);

	public List<Produit> findBySuprIsFalseAndActiveIsFalseAndAccepterIsFalseAndProprietaire_Id(long proprietaire);

	public List<Produit> findBySuprIsFalseAndActiveIsFalseAndAccepterIsTrueAndProprietaire_Id(long proprietaire);

	public List<Produit> findBySuprIsFalseAndActiveIsTrueAndAccepterIsTrueAndProprietaire_Id(long proprietaire);
}
