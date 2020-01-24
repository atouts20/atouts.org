package com.aatout.pagination.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.aatout.model.Echange;
@Repository
public interface EchangePageRepository extends PagingAndSortingRepository<Echange, Long> {
	

}
