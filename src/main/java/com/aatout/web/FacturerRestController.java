
package com.aatout.web;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.aatout.dao.BienRepository;
import com.aatout.dao.CompteMonnaieRepository;
import com.aatout.dao.CompteRepository;
import com.aatout.dao.CompteValeurRepository;
import com.aatout.dao.DepotRepository;
import com.aatout.dao.FactureRepository;
import com.aatout.dao.FacturerRepository;
import com.aatout.dao.GrouperRepository;
import com.aatout.dao.RetraitRepository;
import com.aatout.dao.UserRepository;
import com.aatout.model.AppUser;
import com.aatout.model.CleFacturer;
import com.aatout.model.Facture;
import com.aatout.model.Facturer;

@RestController
@CrossOrigin("**")
public class FacturerRestController {
	/*@Autowired
	private FacturerRepository facturerRepository;  
	
	@Autowired
	private FactureRepository factureRepository;
	
	@Autowired
	private GrouperRepository grouperRepository;  
	
	@Autowired
	private CompteRepository compteRepository;
	
	@Autowired
	private CompteMonnaieRepository compteMonnaieRepository; 
	
	private CompteValeurRepository compteValeurRepository;
	
	@Autowired
	private BienRepository bienRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RetraitRepository retraitRepository;
	
	@Autowired
	private DepotRepository depotRepository; 
	
	@Autowired
	private OperationRestController operationRestController;
	
	@Autowired
	private OperationServiceImpl operationServiceImpl;
	
	
	@RequestMapping(value="/facturers/{id}",method=RequestMethod.GET)
	public List<Facturer> getFacturers(@PathVariable String id){
		Facture f= factureRepository.findById(id);
       // CleFacturer pkFacturer= new CleFacturer(idF);
		return facturerRepository.findByFacture(f);		
	}	
		
	
	@RequestMapping(value="/facturers",method=RequestMethod.GET)
	public List<Facturer> getFacturers(){
		return facturerRepository.findAll();
		//return facturerRepository.findBySupr(false);
	}
	
	
	
	
	@RequestMapping(value="/resolde/{idbien}/{qte}", method=RequestMethod.POST)
    public boolean retourSolde(@PathVariable Long idbien, @PathVariable Double qte, @RequestBody String username){
		AppUser use = userRepository.findByUsername(username);
		//Double smn = compteRepository.cherchermn(use).getSolde();
		Double smn = compteMonnaieRepository.findByUtilisateur(use).getSolde();  
		//Double svl = compteRepository.cherchervl(use).getSolde();
		Double svl = compteValeurRepository.findByUtilisateur(use).getSolde();
		
		Double px = bienRepository.findById(idbien).getPrix();
		Double tb = bienRepository.findById(idbien).gettBCCV();
		
		Double mtvl = px*tb*0.01*qte;
		Double mtmn = px*(1 - tb*0.01)*qte;
		
		Double difvl = svl - mtvl;
		Double difmn = smn - mtmn;
		
		
		if(difvl>0 && difmn>0) {
			return true;
		}else {	
			return false;
		}		
	
	}
	
	@RequestMapping(value="/resolde", method=RequestMethod.POST)
    public boolean retourSolde(@RequestBody ResoldeForm resoldeForm){
		AppUser use = userRepository.findByUsername(resoldeForm.getUsername());
		
		Double smn = compteMonnaieRepository.findByUtilisateur(use).getSolde();  
		
		Double svl = compteValeurRepository.findByUtilisateur(use).getSolde();
		
		Double mtvl=resoldeForm.getMntVal();
		Double mtmn=resoldeForm.getMntMon();
		
		Double difvl = svl - mtvl;
		Double difmn = smn - mtmn;
		
		
		if(difvl>0 && difmn>0) {
			return true;
		}else {	
			return false;
		}		
	
	}
	
	@RequestMapping(value="/envoyerSoldes", method=RequestMethod.GET)
    public List<?> envoyerSoldes(@RequestBody String sername){
		AppUser use = userRepository.findByUsername(sername);
		
		Double smn = compteMonnaieRepository.findByUtilisateur(use).getSolde();  
		
		Double svl = compteValeurRepository.findByUtilisateur(use).getSolde();
		
		//Double tabsldes[];
	    List<Double> tabsldes=new ArrayList<>();
	    tabsldes.add(smn);
	    tabsldes.add(svl);

			return tabsldes;				
	}
	
	
	
	
	
	@RequestMapping(value="/autofact/{idbien}/{qte}", method=RequestMethod.POST)
    public void saveFacturer(@PathVariable Long idbien, @PathVariable Double qte, @RequestBody String user){
           Bien b = bienRepository.findById(idbien);
            Double px = b.getPrix();
    		Double tbccv = b.gettBCCV();
    		
    		Groupe fourn = b.getProprietaire();
    		
    		
    		b.setStock(b.getStock() - qte);
    		bienRepository.save(b);
            

            AppUser clt =userRepository.findByUsername(user);
            Compte cvlC = compteRepository.cherchervl(clt);
    		Compte cmnC = compteRepository.cherchermn(clt);
    		
    		Double mntvl = px*tbccv*0.01*qte;
    		
    		String nOp = "OP"+System.currentTimeMillis();
    		Date d = new Date();
    		Retrait rtvl = new Retrait(nOp, d, mntvl, false, cvlC);
    		operationRestController.saveOperation(rtvl);
    		
    		
    		System.out.println("le compte de valeur de "+clt.getNom()+" est débité de : "+ mntvl );
    		
    		
    		
    		Double mntmn = px*(1 - tbccv*0.01)*qte;
    		
    		Retrait rtmn = new Retrait("OP"+System.currentTimeMillis(), d, mntmn, false, cmnC);
    		operationRestController.saveOperation(rtmn);
    		
    		
    		System.out.println("le compte de monnaie de "+clt.getNom()+" est débité de : "+ mntmn );    		
    		
    		
    		
    		
    		
    			
    		
    		
    		List<Grouper> possedants = grouperRepository.findByGroupe(fourn);   
    		possedants.forEach(pd->{
    			
    			Compte cvl = compteRepository.cherchervl(pd.getAppUser());
    			Double partvl = px*tbccv*0.01*pd.getPartvl()*qte;
    			
    			String nOpt = "OP"+System.currentTimeMillis();
    			Date dt = new Date();
    			Depot dpvl = new Depot(nOpt, dt, partvl, false, cvl);
    			operationRestController.saveOperation(dpvl);
    			
    			
    			System.out.println("le compte de valeur de "+pd.getAppUser().getNom()+" est crédité de : "+ partvl );
    			
    			
    			
    			Compte cmn = compteRepository.cherchermn(pd.getAppUser());
    			Double partmn = px*(1 - tbccv*0.01)*pd.getPartmn()*qte;
    			
    			Depot dpmn = new Depot("OP"+System.currentTimeMillis(), dt, partmn, false, cmn);
    			operationRestController.saveOperation(dpmn);
    			
    			
    			System.out.println("le compte monnaie de "+pd.getAppUser().getNom()+" est crédité de : "+ partmn );
    		});
    		
    		
	   
    		
          
  }
	
	
	
	
	
	@RequestMapping(value="/facturers", method=RequestMethod.POST)
	public Facturer saveFacturer(@RequestBody Facturer fr){
		
		
		fr.setPkFacturer(new CleFacturer(fr.getBien().getId(), fr.getFacture().getId()));
		Double px = fr.getBien().getPrix();
		Double tbccv = fr.getBien().gettBCCV();
		Double qte =fr.getQuantite();
		
		
		
		fr.getBien().setStock(fr.getBien().getStock() - fr.getQuantite());
		bienRepository.save(fr.getBien());
		
		
		
		if(fr.getFacture().getGroupe()==null) {
		
		AppUser client = fr.getFacture().getUtilisateur();
		//Compte cvlC = compteRepository.cherchervl(client);
		Compte cvlC = compteValeurRepository.findByUtilisateur(client);
		//Compte cmnC = compteRepository.cherchermn(client);
		Compte cmnC = compteMonnaieRepository.findByUtilisateur(client);
		
		Double mntvl = px*tbccv*0.01*qte;		
		//String nOp = "OP"+System.currentTimeMillis();
		//Date d = new Date();
		
		//Retrait rtvl = new Retrait(nOp, d, mntvl, false, cvlC);
		//operationRestController.saveOperation(rtvl);   
		operationServiceImpl.retirer(new Date(), mntvl, cvlC);
		
		retraitRepository.save(rt);		
		cvlC.setSolde(cvlC.getSolde() - mntvl);  
		compteRepository.save(cvlC);
		System.out.println("le compte de valeur de "+client.getNom()+" est débité de : "+ mntvl );
		
		
		Double mntmn = px*(1 - tbccv*0.01)*qte;
		
		//Retrait rtmn = new Retrait("OP"+System.currentTimeMillis(), d, mntmn, false, cmnC);
		//operationRestController.saveOperation(rtmn);
		operationServiceImpl.retirer(new Date(), mntmn, cmnC);
		
		cmnC.setSolde(cmnC.getSolde() - mntmn);  
		compteRepository.save(cmnC);	
		System.out.println("le compte de monnaie de "+client.getNom()+" est débité de : "+ mntmn );
		
		}else {
			
			Groupe gpclient = fr.getFacture().getGroupe();
			List<Grouper> possedants = grouperRepository.findByGroupe(gpclient);   
			possedants.forEach(pd->{
								
				//Compte cvl = compteRepository.cherchervl(pd.getAppUser());
				Compte cvl = compteValeurRepository.findByUtilisateur(pd.getAppUser());
				Double partvl = px*tbccv*0.01*pd.getPartvl()*qte;
				
				//String nOp = "OP"+System.currentTimeMillis();
				//Date d = new Date();
				//Retrait rtvl = new Retrait(nOp, d, partvl, false, cvl);
				//operationRestController.saveOperation(rtvl);
				operationServiceImpl.retirer(new Date(), partvl, cvl);
				
				cvl.setSolde(cvl.getSolde() - partvl);
				compteRepository.save(cvl);
				System.out.println("le compte de valeur de "+pd.getAppUser().getNom()+" est débité de : "+ partvl );
								
				
				//Compte cmn = compteRepository.cherchermn(pd.getAppUser());
				Compte cmn = compteMonnaieRepository.findByUtilisateur(pd.getAppUser());
				Double partmn = px*(1 - tbccv*0.01)*pd.getPartmn()*qte;
				
				//Retrait rtmn = new Retrait("OP"+System.currentTimeMillis(), d, partmn, false, cmn);
				//operationRestController.saveOperation(rtmn);
				operationServiceImpl.retirer(new Date(), partmn, cmn);
				
				cmn.setSolde(cmn.getSolde() - partmn);
				compteRepository.save(cmn);
				System.out.println("le compte monnaie de "+pd.getAppUser().getNom()+" est débité de : "+ partmn );
			});
		}
		
		
		
		if(fr.getBien().getUser()==null) {
		
		Groupe propietaire = fr.getBien().getGroupe();
		Groupe propietaire = fr.getBien().getProprietaire();
		List<Grouper> possedants = grouperRepository.findByGroupe(propietaire);   
		
		possedants.forEach(pd->{
			Compte c = compteRepository.findByUtilisateur(pd.getAppUser());
			Double partuse = px*pd.getPart();
			c.setSolde(c.getSolde() + partuse);	
			compteRepository.save(c);
			System.out.println("le compte de "+pd.getAppUser().getNom()+" est crédité de : "+ partuse ); 
			
			//Compte cvl = compteRepository.cherchervl(pd.getAppUser());
			Compte cvl = compteValeurRepository.findByUtilisateur(pd.getAppUser());
			Double partvl = px*tbccv*0.01*pd.getPartvl()*qte;
			
			//String nOp = "OP"+System.currentTimeMillis();
			//Date d = new Date();
			//Depot dpvl = new Depot(nOp, d, partvl, false, cvl);
			//operationRestController.saveOperation(dpvl);
			operationServiceImpl.deposer(new Date(), partvl, cvl);
			
			cvl.setSolde(cvl.getSolde() + partvl);
			compteRepository.save(cvl);
			System.out.println("le compte de valeur de "+pd.getAppUser().getNom()+" est crédité de : "+ partvl );
			
			
			//Compte cmn = compteRepository.cherchermn(pd.getAppUser());
			Compte cmn = compteMonnaieRepository.findByUtilisateur(pd.getAppUser());
			Double partmn = px*(1 - tbccv*0.01)*pd.getPartmn()*qte;
			
			//Depot dpmn = new Depot("OP"+System.currentTimeMillis(), d, partmn, false, cmn);
			//operationRestController.saveOperation(dpmn);
			operationServiceImpl.deposer(new Date(), partmn, cmn);
			
			cmn.setSolde(cmn.getSolde() + partmn);
			compteRepository.save(cmn);
			System.out.println("le compte monnaie de "+pd.getAppUser().getNom()+" est crédité de : "+ partmn );
		});
		
		}else {
			
			AppUser propietaire = fr.getBien().getUser();
			
			Compte cvl = compteRepository.cherchervl(propietaire);
			Double partvl = px*tbccv*0.01*qte;
			
			String nOp = "OP"+System.currentTimeMillis();
			Date d = new Date();
			Depot dpvl = new Depot(nOp, d, partvl, false, cvl);
			operationRestController.saveOperation(dpvl);
			
			cvl.setSolde(cvl.getSolde() + partvl);
			compteRepository.save(cvl);
			System.out.println("le compte de valeur de "+propietaire.getNom()+" est crédité de : "+ partvl );
			
			
			Compte cmn = compteRepository.cherchermn(propietaire);
			Double partmn = px*(1 - tbccv*0.01)*qte;
			
			Depot dpmn = new Depot("OP"+System.currentTimeMillis(), d, partmn, false, cmn);
			operationRestController.saveOperation(dpmn);
			
			cmn.setSolde(cmn.getSolde() + partmn);
			compteRepository.save(cmn);
			System.out.println("le compte monnaie de "+propietaire.getNom()+" est crédité de : "+ partmn );
		}
		
		
		
		
		
		return facturerRepository.save(fr);		
	}
	
	
	
	@RequestMapping(value="/facturers/{idF}/{idB}", method=RequestMethod.PUT)
    public Facturer saveFacturer(@PathVariable String idF, @PathVariable Long idB, @RequestBody Facturer fr){
           CleFacturer pkFacturer= new CleFacturer(idB,idF); 
	       fr.setPkFacturer(pkFacturer);
	       return facturerRepository.save(fr);
       }
	
	
	
	
	@RequestMapping(value="/facturers/{idF}/{idB}",method=RequestMethod.DELETE)
	public boolean supprimerFacturer(@PathVariable String idF, @PathVariable Long idB){
         try {
		       CleFacturer pkFacturer= new CleFacturer(idB,idF);	   
	            facturerRepository.delete(pkFacturer);
               return true;
		} catch (Exception e) {
		 return false;
		}
                               	
	}
	
	
	*/

}
