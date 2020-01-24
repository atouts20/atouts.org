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
import com.aatout.model.CleGrouper;
import com.aatout.model.Groupe;
import com.aatout.model.Grouper;

@RestController
@CrossOrigin("**")
public class GrouperRestController {
	@Autowired
	private GrouperRepository grouperRepository;
	
	@Autowired
	private GroupeRepository groupeRepository;
	
	@RequestMapping(value="/groupers/{id}",method=RequestMethod.GET)
	public List<Grouper> getGroupers(@PathVariable String id){
		Groupe g= groupeRepository.findById(id);
       // CleGrouper pkGrouper= new CleGrouper(idG);
		return grouperRepository.findByGroupe(g);		
	}
	
	@RequestMapping(value="/groupers", method=RequestMethod.POST)
	public Grouper saveGrouper(@RequestBody Grouper gp){
		//factureRepository.save(fr.getFacture());
		/*System.out.println(fr.getPkFacturer());
		System.out.println(fr.getFacture().toString());
		System.out.println(fr.getBien().toString());
		System.out.println(fr.getQuantite());*/
		
		gp.setPkGrouper(new CleGrouper(gp.getAppUser().getId(), gp.getGroupe().getId()));
		
		return grouperRepository.save(gp);		
	}
	 
	@RequestMapping(value="/groupers/{idG}/{idU}", method=RequestMethod.PUT)
    public Grouper saveGrouper(@PathVariable String idG, @PathVariable Long idU, @RequestBody Grouper gp){
           CleGrouper pkGrouper= new CleGrouper(idU,idG); 
	       gp.setPkGrouper(pkGrouper);
	       return grouperRepository.save(gp);
       }
	
	
	@RequestMapping(value="/groupers/{idG}/{idU}",method=RequestMethod.DELETE)
	public boolean supprimerGrouper(@PathVariable String idG, @PathVariable Long idU){
         try {
		       CleGrouper pkGrouper= new CleGrouper(idU,idG);	   
	          //  grouperRepository.delete(pkGrouper);
               return true;
		} catch (Exception e) {
		 return false;
		}
                               	
	}

}
