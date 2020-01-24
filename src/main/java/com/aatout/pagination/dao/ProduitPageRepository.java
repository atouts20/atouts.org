package com.aatout.pagination.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.aatout.model.Produit;
@Repository
public interface ProduitPageRepository extends PagingAndSortingRepository<Produit, Long> {
	

}
