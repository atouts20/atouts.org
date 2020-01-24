package com.aatout.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.aatout.model.AppUser;
import com.aatout.model.Publication;

public interface PublicationRepository extends JpaRepository<Publication, String> {
	public Publication findById(Long id);
	public List<Publication> findByaccepter(boolean accepter);
	
	/*@Query("select c from Publication c where c.categorie='PROD' and c.accepter=false")
	public List<Publication> listPrdPub();
	
	@Query("select c from Publication c where c.categorie='SERV' and c.accepter=false")
	public List<Publication> listServPub();*/
	
	
	//public List<Publication> findByAccepterIsFalse();
	public Page<Publication> findByStatusAndSupUserAndAccepterAndRejeterAndCreerAndProprietaire_idAndNomLikeIgnoreCase(Pageable pageable,boolean status, boolean supUser,boolean accepter, boolean creer, boolean rejeter, long proprietaires, String nom);
	public List<Publication> findByCatLikeAndStatusAndSupUserAndAccepterAndRejeterAndCreer(String cat, boolean status, boolean suprUser, boolean accepter, boolean rejeter, boolean creer);
	public List<Publication> findByAccepterIsTrueAndCreerIsFalse();
	
	
	public List<Publication> findByProprietaire(AppUser proprietaire);
	
	public List<Publication> findByProprietaireLikeAndAccepterIsTrueAndCreerIsTrue(AppUser proprietaire);
	
	public List<Publication> findByProprietaireLikeAndAccepterIsFalseAndCreerIsFalse(AppUser proprietaire);
	

}
