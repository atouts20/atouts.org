package com.aatout.dao;

import java.util.List;

import com.aatout.model.Echange;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.aatout.model.Bien;

public interface BienRepository extends JpaRepository<Bien, Long> {
	@Query("From Bien b Where b.supr= false and b.active = true and b.id =:id")
	public Bien listBienById(@Param("id") Long id);
	
	/*@Query("From Bien b Where b.supr= false and b.active = true")
	List<Bien>listBien();*/
	
	/*@Query("From Bien b where  b.supr= false and b.active = true and b.nom like :x")
	public Page<Bien> chercherBien(@Param("x")String mc,Pageable pageable);*/

	public List<Bien> findBySuprIsFalseAndActiveIsTrueAndNomLike( String nom);
	public Bien findBySuprIsFalseAndActiveIsTrueAndId(Long id);
	public List<Bien> findBySuprIsFalseAndActiveIsFalseAndAccepterIsFalseAndProprietaire_Id(long proprietaire);

	public List<Bien> findBySuprIsFalseAndActiveIsFalseAndAccepterIsTrueAndProprietaire_Id(long proprietaire);

	public List<Bien> findBySuprIsFalseAndActiveIsTrueAndAccepterIsTrueAndProprietaire_Id(long proprietaire);


	//public List<Bien> findBySuprIsFalseAndActiveIsTrueAndAccepterIsTrueAndProprietaire_IdAnd



}
