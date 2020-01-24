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
@CrossOrigin("**")
@RequestMapping("/compte-favori")
public class CompteFavoriRestController {
	@Autowired
	private CompteFavoriDao compteFavoriDao;
	
	@GetMapping(value = "/list/{id}")
	public List<CompteFavori> getCompteFavoris(@PathVariable Long id) {
		return compteFavoriDao.findByStatus(false);
	}

	@PostMapping(value = "/save")
	public CompteFavori save(@RequestBody CompteFavori compteFavori) {
		System.out.println(compteFavori);

		return	compteFavoriDao.saveAndFlush(compteFavori);
	}

	@PostMapping(value = "/delete")
	public CompteFavori delete(@RequestBody CompteFavori CompteFavori) {

		CompteFavori.setStatus(true);
		return	compteFavoriDao.saveAndFlush(CompteFavori);
	}

}
