package com.aatout.pagination.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.aatout.model.Echange;

@Service
public class EchangeService {
	 
	 @Autowired
	 EchangePageRepository repository;
	
	    public List<Echange> getAllEchange(Integer pageNo, Integer pageSize, String sortBy)
	    {
	    	String sortOrder = "desc";
	    	Sort sort = new Sort(Direction.fromString(sortOrder), sortBy);
	        //Pageable paging = PageRequest.of(pageNo, pageSize, sort);
	        PageRequest paging = new PageRequest(pageNo, pageSize, sort);
	        Page<Echange> pagedResult = repository.findAll(paging);
	         
	        if(pagedResult.hasContent()) {
	            return pagedResult.getContent();
	        } else {
	            return new ArrayList<Echange>();
	        }
	    }
}