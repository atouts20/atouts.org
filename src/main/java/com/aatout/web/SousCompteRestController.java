package com.aatout.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aatout.dao.SousCompteDao;
import com.aatout.model.SousCompte;

@RestController
@CrossOrigin("**")
 @RequestMapping("/sous-compte")
public class SousCompteRestController {
	@Autowired
	SousCompteDao sousCompteDao;
	
	@GetMapping(value = "/list")
	public List<SousCompte> getSousComptes() {
		return sousCompteDao.findByStatus(false);
	}


	@PostMapping(value = "/save")
	public SousCompte save(@RequestBody SousCompte sousCompte) {
		System.out.println(sousCompte);


//		sousCompte.setCreateBy();
		return	sousCompteDao.saveAndFlush(sousCompte);
	}

	@PostMapping(value = "/delete")
	public SousCompte delete(@RequestBody SousCompte sousCompte) {

		sousCompte.setStatus(true); 
		return	sousCompteDao.saveAndFlush(sousCompte);
	}
	
}
