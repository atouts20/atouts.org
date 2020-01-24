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
import com.aatout.dao.RelanceDao;
import com.aatout.model.Relance;

@RestController
@CrossOrigin("**")
@RequestMapping("/relance")
public class RelanceRestController {
	@Autowired
	private RelanceDao relanceDao;
	
	@Autowired
	private MobilisationDao mobilisationDao;
	

	@GetMapping(value = "/list")
	public List<Relance> getRelances() {
		return relanceDao.findByStatusAndSuprUser(false, false);
	}
	
	@GetMapping(value = "/list-by-mobilisation/{id}")
	public List<Relance> getRelancesByMobilisation(@PathVariable long id) {
		return relanceDao.findByStatusAndSuprUserAndMobilisationRelance_Id(false, false, id);
	}


	@PostMapping(value = "/save")
	public Relance save(@RequestBody Relance relance) {



//		relance.setCreateBy();
		return	relanceDao.saveAndFlush(relance);
	}

	@PostMapping(value = "/delete")
	public Relance delete(@RequestBody Relance relance) {

		relance.setStatus(true);
		return	relanceDao.saveAndFlush(relance);
	}
	
	@PostMapping(value = "/delete-by-user")
	public Relance deleteByUser(@RequestBody Relance relance) {

		relance.setSuprUser(true);
		return	relanceDao.saveAndFlush(relance);
	}
	
	
	

}
