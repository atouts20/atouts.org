package com.aatout.web;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aatout.dao.FactureRepository;
import com.aatout.model.Facture;






@RestController
@CrossOrigin("*")
public class FactureRestController {
	@Autowired
	private FactureRepository factureRepository;
	
	@RequestMapping(value="/factures",method=RequestMethod.GET)
	public List<Facture> getFactures(){
		return factureRepository.findAll();
	}
	
	@RequestMapping(value="/factures/{id}",method=RequestMethod.GET)
	public Facture getOperation(@PathVariable Long id){
		return factureRepository.findOne(id);
	}
	
	@RequestMapping(value="/chercherFacturesDte",method=RequestMethod.GET)
	public Page<Facture> chercherFactDte(
			@RequestParam(name="mc",defaultValue="")Date mc,
			@RequestParam(name="page",defaultValue="0")int page,
			@RequestParam(name="size",defaultValue="5")int size){
		return factureRepository.chercherDate(mc, new PageRequest(page, size));
	}
	
	@RequestMapping(value="/factures",method=RequestMethod.POST)
	public Facture saveFacture(@RequestBody Facture c){
		return factureRepository.save(c);
	}
	
	@RequestMapping(value="/factures/{id}",method=RequestMethod.PUT)
	public Facture saveFacture(@PathVariable Long id,@RequestBody Facture c){
		c.setId(id);
		return factureRepository.save(c);
	}

}
