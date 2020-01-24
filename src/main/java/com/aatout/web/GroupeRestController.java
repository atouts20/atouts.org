package com.aatout.web;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.aatout.dao.GroupeRepository;
import com.aatout.dao.GrouperRepository;
import com.aatout.model.Groupe;

@RestController
@CrossOrigin("**")
public class GroupeRestController {
	@Autowired
	private GroupeRepository groupeRepository;
	
	@Autowired
	private GrouperRepository grouperRepository;
	
	@RequestMapping(value="/groupes",method=RequestMethod.GET)
	public List<Groupe> getGroupes(){
		//return groupeRepository.findAll();
		//return groupeRepository.findBySupr(false);
		return groupeRepository.findBySuprIsFalse();
	}
	
	@RequestMapping(value="/groupes/{id}",method=RequestMethod.GET)
	public Groupe getGroupe(@PathVariable String id){
		return groupeRepository.findById(id);
	}
	
	@RequestMapping(value="/groupes", method=RequestMethod.POST)
	public Groupe saveGroupe(@RequestBody Groupe g){  
		
				
		return groupeRepository.saveAndFlush(g);		
		
	}
	
	
	@RequestMapping(value="/editgroupes", method=RequestMethod.PUT)
    public Groupe editGroupe(@RequestBody Groupe g){  
		
		Groupe grp = groupeRepository.findById(g.getId());
		grp.getGroupers().forEach(pd->{
			grouperRepository.delete(pd);
		});
		
	       return groupeRepository.save(g);
       }
	
	
	@RequestMapping(value="/editgroupe", method=RequestMethod.PUT)
    public Groupe editGroupes(@RequestBody Groupe g){  
		
	       return groupeRepository.save(g);
       }
	
	
	@RequestMapping(value="/groupes/{id}",method=RequestMethod.DELETE)
	public boolean supprimerGroupe(@PathVariable String id){
		
		try {
			Groupe g = groupeRepository.findById(id);
			g.setSupr(true);
			groupeRepository.save(g);
			//groupeRepository.delete(id);
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}

}
