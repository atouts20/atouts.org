package com.aatout.pagination.dao;


import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.aatout.model.Services;
@Repository
public interface ServicePageRepository extends PagingAndSortingRepository<Services, Long> {
	
}
