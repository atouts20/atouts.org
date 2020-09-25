package com.aatout.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aatout.dao.CompteFavoriDao;
import com.aatout.model.CompteFavori;

@RestController
@RequestMapping("/compte-favori")
public class CompteFavoriRestController {
	@Autowired
	private CompteFavoriDao compteFavoriDao;
	
	@GetMapping(value = "/list/{id}")
	public List<CompteFavori> getCompteFavoris(@PathVariable Long id) {
		return compteFavoriDao.findByStatusAndAppUserCompte_id(false, id);
	}

	@PostMapping(value = "/save")
	public CompteFavori save(@RequestBody CompteFavori compteFavori) { 
		System.out.println(compteFavori);
		System.out.println(compteFavori.getAppUserCompte().getId());
		CompteFavori unCompteFavori = compteFavoriDao.findByStatusAndNumCompteAndAppUserCompte_id(false, compteFavori.getNumCompte(), compteFavori.getAppUserCompte().getId());
		if(unCompteFavori != null) { 
			return null;
		} else {
			return	compteFavoriDao.saveAndFlush(compteFavori);
		}
		
	}

	@PostMapping(value = "/delete")
	public CompteFavori delete(@RequestBody CompteFavori CompteFavori) {

		CompteFavori.setStatus(true);
		return	compteFavoriDao.saveAndFlush(CompteFavori);
	}

}
