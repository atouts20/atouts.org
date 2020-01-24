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

import com.aatout.dao.MobilisationDao;
import com.aatout.dao.ReponseDao;
import com.aatout.model.Reponse;

@RestController
@CrossOrigin("**")
@RequestMapping("/reponse")
public class ReponseRestController {
	@Autowired
	private ReponseDao reponseDao;
	
	@Autowired
	private MobilisationDao mobilisationDao;
	

	@GetMapping(value = "/list")
	public List<Reponse> getReponses() {
		return reponseDao.findByStatusAndSuprUser(false, false);
	}
	
	@GetMapping(value = "/list-by-mobilisation/{id}")
	public List<Reponse> getReponsesByMobilisation(@PathVariable long id) {
		return reponseDao.findByStatusAndSuprUserAndMobilisationReponse_Id(false, false, id);
	}


	@PostMapping(value = "/save")
	public Reponse save(@RequestBody Reponse reponse) {



//		reponse.setCreateBy();
		return	reponseDao.saveAndFlush(reponse);
	}

	@PostMapping(value = "/delete")
	public Reponse delete(@RequestBody Reponse reponse) {

		reponse.setStatus(true);
		return	reponseDao.saveAndFlush(reponse);
	}
	
	@PostMapping(value = "/delete-by-user")
	public Reponse deleteByUser(@RequestBody Reponse reponse) {

		reponse.setSuprUser(true);
		return	reponseDao.saveAndFlush(reponse);
	}
	

}
