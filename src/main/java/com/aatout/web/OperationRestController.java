package com.aatout.web;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.aatout.commande.dao.CommandeDao;
import com.aatout.commande.dao.CommandeServiceDao;
import com.aatout.commande.model.Commande;
import com.aatout.commande.model.CommandeService;
import com.aatout.dao.CompteRepository;
import com.aatout.dao.DepotRepository;
import com.aatout.dao.DepotTrDao;
import com.aatout.dao.OperationAutorisationDao;
import com.aatout.dao.OperationRepository;
import com.aatout.dao.RetraitRepository;
import com.aatout.dao.RetraitTrDao;
import com.aatout.enums.StatusName;
import com.aatout.model.Compte;
import com.aatout.model.Depot;
import com.aatout.model.DepotTR;
import com.aatout.model.Operation;
import com.aatout.model.OperationAutorisation;
import com.aatout.model.Retrait;
import com.aatout.model.RetraitTR;
import com.aatout.operation.OperationService;
import com.aatout.payload.OperationForm;

@RestController
@RequestMapping("/operation")
public class OperationRestController {
	@Autowired
	private OperationRepository operationRepository; 
	
	@Autowired
	private DepotRepository depotRepository;

	@Autowired
	private RetraitRepository retraitRepository;
	
	@Autowired
	private OperationService operationService;
	
	@Autowired
	private OperationAutorisationDao opAutoDao;

	@Autowired
	private CompteRepository compteRepository;
	
	@Autowired
	private DepotTrDao depotTrDao;
	
	@Autowired
	private RetraitTrDao retraitTrDao;

	@Autowired
	private CommandeDao commandeDao;
	
	@Autowired
	private CommandeServiceDao commandeServiceDao;


	@GetMapping(value="/list")
	public List<Operation> getOperations(){
		return operationRepository.findAll();
		//return operationRepository.findBySuprIsFalse();
	}
	
	@GetMapping(value="/list-en")
	public List<OperationAutorisation> getOperationsEn(){
		return opAutoDao.findByStatusAndSuprUserAndEtat(false, false, StatusName.NON_AUTORISEE);
		//return operationRepository.findBySuprIsFalse();
	}
	
	@GetMapping(value="/list-ens")
	public List<OperationAutorisation> getOperationsEns(){
		return opAutoDao.findAll();
		//return operationRepository.findBySuprIsFalse();
	}

	@GetMapping(value="/list/{id}")
	public Operation getOperation(@PathVariable Long id){

		//return operationRepository.findOne(id);
		Operation uneOperation = operationRepository.findBySuprIsFalseAndId(id);

		return uneOperation;
	}
	
	@GetMapping(value="/mes-operation/{username}")
	public List<Operation> getMesOperation(@PathVariable String username){
		return operationRepository.findBySuprAndAutorisedBy(false, username);
	}	

	@GetMapping(value="/list-depot")
	public List<Depot> getDepot(){
		//return operationRepository.findOne(id);

		return depotRepository.findBySuprIsFalse();
	}


	@GetMapping(value="/list-retrait")
	public List<Retrait> getRetrait(){

		//return operationRepository.findOne(id);

		return retraitRepository.findBySuprIsFalse();
	}
	
	//get depots tr et retrait tr by commande
	
			@GetMapping(value="/list-depotTr-by-commande/{idCommande}")
			public List<DepotTR> getDepotTrByCommande(@PathVariable Long idCommande){
				Commande commande= commandeDao.findById(idCommande);

				return depotTrDao.findByCommande(commande);
			}


			@GetMapping(value="/list-retraitTr-by-commande/{idCommande}")
			public List<RetraitTR> getRetraitTrByCommande(@PathVariable Long idCommande){
				Commande commande= commandeDao.findById(idCommande);

				return retraitTrDao.findByCommande(commande);
			}
			
			
			/*@GetMapping(value="/list-retraitTr-by-commande-service/{idCommandeService}")
			public List<RetraitTR> getRetraitTrByCommandeService(@PathVariable Long idCommandeService){
				CommandeService commandeService= commandeServiceDao.findById(idCommandeService);

				return retraitTrDao.findByCommandeService(commandeService);
			}*/
			
			@GetMapping(value="/list-retraitTr-by-commande-service/{idCommandeService}")
			public List<Operation> getRetraitTrByCommandeService(@PathVariable Long idCommandeService){
				CommandeService commandeService= commandeServiceDao.findById(idCommandeService);

				return operationRepository.findByCommandeService(commandeService);
			}



	//Methodes pour obtenir la Liste des comptes monnaie 

	@GetMapping(value = "/consulter-compte/{numCompte}")
	public Compte getCompte(@PathVariable String numCompte){
		return compteRepository.findCompteByActiveIsTrueAndSuprFalseAndFermerIsFalseAndNumCompte(numCompte);
	}


	@PostMapping(value= "/save")
	public boolean  saveOperation(@RequestBody FormOperation operation){ 

		System.out.println("foutou---->"+operation );



		//Recherche un compte vie le numCompte donné par operation		
		Compte compte = getCompte(operation.getUnCompte().getNumCompte());

		System.out.println("*******kkkkkkk ==>>>: "+compte);


		System.out.println(compte);

		if(operation.getType().equals("DEPO")) {
			try {
				System.out.println("*******ppppp ==>>>: "+compte);
				//operationService.verser(compte , compte.getSolde() );
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 

		}




		/*try  { 

			if(operation.getType().equals("RETR" )) 

				operationService.retirer(compte, compte.getSolde()); 


			else if(operation.getType().equals("DEPO")) 

				operationService.verser(compte , compte.getSolde() ); 
				//operationMetierImpl.depot(depot);

			else  if (operation.getType().equals("Virement")) 

				operationService.virement(compte, compte, compte.getSolde());

		} catch  (Exception e) { 
			//return  "redirect:/consultercompte?codeCte=" +foutou.getNumCompte() +"&error=" +e.getMessage();  

		} 
		 */
		return   true;
		//return (Operation)foutou;
	}


	//edit depot
	@RequestMapping(value="/depot/{id}",method=RequestMethod.PUT)
	public Operation saveOperation(@PathVariable Long id,@RequestBody Depot c){

		Operation op= operationRepository.findBySuprIsFalseAndId(id);			
		op.getCompte().setSolde(op.getCompte().getSolde() - op.getMontantOp() + c.getMontantOp());
		compteRepository.save(op.getCompte());

		c.setId(id);
		return operationRepository.save(c);
	}
	//edit retrait
	@RequestMapping(value="/retrait/{id}",method=RequestMethod.PUT)
	public Operation saveOperation(@PathVariable Long id,@RequestBody Retrait c){

		Operation op= operationRepository.findBySuprIsFalseAndId(id);			
		op.getCompte().setSolde(op.getCompte().getSolde() + op.getMontantOp() - c.getMontantOp());
		compteRepository.save(op.getCompte());

		c.setId(id);
		return operationRepository.save(c);
	}


	@RequestMapping(value="/depot/{id}",method=RequestMethod.DELETE)
	public boolean supprimerDepot(@PathVariable Long id){

		try {
			Operation op= operationRepository.findBySuprIsFalseAndId(id);			
			op.getCompte().setSolde(op.getCompte().getSolde() - op.getMontantOp());
			compteRepository.save(op.getCompte());

			op.setSupr(true);
			operationRepository.save(op);
			//operationRepository.delete(id);
			return true;
		} catch (Exception e) {
			return false;
		}

	}


	@RequestMapping(value="/retrait/{id}",method=RequestMethod.DELETE)
	public boolean supprimerRetrait(@PathVariable Long id){

		try {
			Operation op= operationRepository.findBySuprIsFalseAndId(id);			
			op.getCompte().setSolde(op.getCompte().getSolde() + op.getMontantOp());
			compteRepository.save(op.getCompte());

			op.setSupr(true);
			operationRepository.save(op);
			//operationRepository.delete(id);
			return true;
		} catch (Exception e) {
			return false;
		}

	}


	@Transactional
	@PostMapping(value="/save-depot")
	public boolean saveDepotd(@RequestBody Operation operation) throws RemoteException { 

		System.out.println("Mon depot ======>>>: "+operation);

		Operation uneOperation = operationRepository.findBySuprIsFalseAndId(operation.getId());
		

		if (operation instanceof Depot && uneOperation == null) {


			Depot unDepot = (Depot) operation;


			unDepot.getCompte().setSolde(unDepot.getCompte().getSolde() + unDepot.getMontantOp());

			compteRepository.save(unDepot.getCompte());
			//operationService.verser(unDepot);


			operationRepository.saveAndFlush(unDepot);

		}

		if (operation instanceof Retrait) {

			Retrait unRetrait = (Retrait) operation;
			
			unRetrait.getCompte().setSolde(unRetrait.getCompte().getSolde() - unRetrait.getMontantOp());
			
			compteRepository.save(unRetrait.getCompte());  

			operationRepository.saveAndFlush(unRetrait);

		}

		return true;
	}
	@PostMapping(value="/save-operation-vire-user")
	@Transactional
	public OperationForm saveOperationUser(@RequestBody OperationForm operationForm){
		
		//Random rcd = new Random();
		System.out.println(operationForm.getNumCompte());
		System.out.println(operationForm.getNumCompte2());
		Long idMax =  operationRepository.getMax();
		Long badge = 0L;
		Operation operation =  operationRepository.findBySuprIsFalseAndId(idMax);
		
		if (operation == null ) { 
			badge = 1001L;
		}else {
			badge = operation.getBadge() + 1;
		}
		
		
		try{ 
			
			if (operationForm.getType().equals("VIRE")){
				operationService.virementUser(operationForm.getNumCompte(), operationForm.getNumCompte2(), operationForm.getMontantOp(),  operationForm.getNarrative(), badge, operationForm.getCreateBy(), operationForm.getAutorisedBy());
			}
		} catch(Exception e) {
			//model.addAttribute("error", e);
			/*return "redirect:/consulterCompte?operationForm.getNumCompte()="+operationForm.getNumCompte()+
					"&error="+e.getMessage();*/
			return null;
		}
		
		/*return "redirect:/consulterCompte?operationForm.getNumCompte()="+operationForm.getNumCompte();*/
		return operationForm;
	}
	
	@PostMapping(value="/save-operation")
	@Transactional
	public OperationForm saveOperation(@RequestBody OperationForm operationForm){
		System.out.println("-----------------------");
		System.out.println(operationForm);
		System.out.println("=====-------=====");
		System.out.println(operationForm.getNumCompte());
		System.out.println(operationForm.getId());
		System.out.println(operationForm.getMontantOp());
		System.out.println(operationForm.getNumCompte2());
		System.out.println("=====---TYPE----=====");
		System.out.println(operationForm.getType());
		System.out.println(operationForm.getBadge());
		System.out.println(operationForm);
		//Random rcd = new Random();
		Long idMax =  operationRepository.getMax();
		
		System.out.println(idMax); 
		
		Operation operation =  operationRepository.findBySuprIsFalseAndId(idMax);
		//System.out.println("bbbhhhhh:"+operationForm.getId());
		
		Long badge = 0L;
		
		if (operation == null ) {
			badge = 1001L;
		}else {
			badge = operation.getBadge() + 1;
		}
		/*if (operationForm.getId() ==0) {
			
			OperationAutorisation op = opAutoDao.findByStatusIsFalseAndSuprUserIsFalseAndId(operationForm.getId());
			System.out.println("bbbhhhhh:"+op);
			op.setEtat(StatusName.VALIDEE);
			opAutoDao.saveAndFlush(op); 			
		}*/
				
		try{ 
			if(operationForm.getType().equals("DEPO")){
				operationService.augmenter(operationForm.getNumCompte(), operationForm.getNumCompte2(), operationForm.getMontantOp(), operationForm.getNarrative(), badge, operationForm.getCreateBy(), operationForm.getAutorisedBy());
			}
			else if(operationForm.getType().equals("RETR")){
				operationService.diminuer(operationForm.getNumCompte(), operationForm.getNumCompte2(), operationForm.getMontantOp(), operationForm.getNarrative(), badge, operationForm.getCreateBy(), operationForm.getAutorisedBy());
			} 
			if (operationForm.getType().equals("VIRE")){
				operationService.virement(operationForm.getNumCompte(), operationForm.getNumCompte2(), operationForm.getMontantOp(),  operationForm.getNarrative(), badge, operationForm.getCreateBy(), operationForm.getAutorisedBy());
			}
		} catch(Exception e) {
			//model.addAttribute("error", e);
			/*return "redirect:/consulterCompte?operationForm.getNumCompte()="+operationForm.getNumCompte()+
					"&error="+e.getMessage();*/
			return null;
		}
		
		/*return "redirect:/consulterCompte?operationForm.getNumCompte()="+operationForm.getNumCompte();*/
		return operationForm;
	}
	
	
	@PostMapping(value="/save-operation-virement")
	public OperationForm saveOperationVirement(@RequestBody OperationForm operationForm){
		System.out.println(operationForm);
		System.out.println(operationForm);
		//Random rcd = new Random();
		Long idMax =  operationRepository.getMax();
		
		System.out.println(idMax); 
		
		Operation operation =  operationRepository.findBySuprIsFalseAndId(idMax);
		
		Long badge = 0L;
		
		if (operation == null ) {
			badge = 1001L;
		}else {
			badge = operation.getBadge() + 1;
		}
		
		try{  
			if (operationForm.getType().equals("VIRE")){
				operationService.virement(operationForm.getNumCompte(), operationForm.getNumCompte2(), operationForm.getMontantOp(),  operationForm.getNarrative(), badge, operationForm.getCreateBy(), operationForm.getAutorisedBy());
			}
		} catch(Exception e) {
			//model.addAttribute("error", e);
			/*return "redirect:/consulterCompte?operationForm.getNumCompte()="+operationForm.getNumCompte()+
					"&error="+e.getMessage();*/
			return null;
		}
		
		/*return "redirect:/consulterCompte?operationForm.getNumCompte()="+operationForm.getNumCompte();*/
		return operationForm;
	}
	
	
	/*@RequestMapping(value="/save-operation", method=RequestMethod.POST)
	public OperationAutorisation saveOperation(@RequestBody OperationAutorisation operationForm){
		System.out.println(operationForm);
		try{
			if(operationForm.getType().equals("DEPO")){
				operationService.verser(operationForm.getCompte().getNumCompte(), operationForm.getMontantOp(), operationForm.getNarrative() );
			}
			else if(operationForm.getType().equals("RETR")){
				operationService.retirer(operationForm.getCompte().getNumCompte(), operationForm.getMontantOp(), operationForm.getNarrative());
			} 
			if (operationForm.getType().equals("VIRE")){
				operationService.virement(operationForm.getCompte().getNumCompte(), operationForm.getCompte().getNumCompte(), operationForm.getMontantOp(),  operationForm.getNarrative());
			}
		} catch(Exception e) {
			//model.addAttribute("error", e);
			return "redirect:/consulterCompte?operationForm.getNumCompte()="+operationForm.getNumCompte()+
					"&error="+e.getMessage();
			return null;
		}
		
		return "redirect:/consulterCompte?operationForm.getNumCompte()="+operationForm.getNumCompte();
		return operationForm;
	}*/
	
	@PostMapping(value="/save-operation-autorisation")
	public OperationForm saveOperationAuto(@RequestBody OperationForm operationForm){
		
		Long idMax =  opAutoDao.getMaxEn();
		
		System.out.println(idMax);
		
		OperationAutorisation operation =  opAutoDao.findByStatusIsFalseAndSuprUserIsFalseAndId(idMax);
		
		Long badge = 0L;
		
		if (operation == null ) {
			badge = 1001L;
		}else {
			badge = operation.getBadge() + 1;
		}
		try{
			if(operationForm.getType().equals("DEPO")){
				
				operationService.verserEn(operationForm.getNumCompte(), operationForm.getNumCompte2(), operationForm.getMontantOp(), badge, operationForm.getNarrative(), operationForm.getCreateBy());
			}
			else if(operationForm.getType().equals("RETR")){
				operationService.retirerEn(operationForm.getNumCompte(), operationForm.getNumCompte2(), operationForm.getMontantOp(), badge, operationForm.getNarrative(), operationForm.getCreateBy());
			} 
			if (operationForm.getType().equals("VIRE")){
				operationService.virementEn(operationForm.getNumCompte(), operationForm.getNumCompte2(), operationForm.getMontantOp(),  badge, operationForm.getNarrative(), operationForm.getCreateBy());
			}
		} catch(Exception e) {
			//model.addAttribute("error", e);
			/*return "redirect:/consulterCompte?operationForm.getNumCompte()="+operationForm.getNumCompte()+
					"&error="+e.getMessage();*/
			return null;
		}
		
		/*return "redirect:/consulterCompte?operationForm.getNumCompte()="+operationForm.getNumCompte();*/
		return operationForm;
	}
	
	@PostMapping(value="/accepter-operation")
	public OperationAutorisation accepterOperation(@RequestBody OperationAutorisation operationAutorisation){
		OperationAutorisation op = opAutoDao.findByStatusIsFalseAndSuprUserIsFalseAndId(operationAutorisation.getId());
		op.setEtat(StatusName.AUTORISEE);
		op.setAutorisedBy(operationAutorisation.getAutorisedBy());
		return opAutoDao.saveAndFlush(op);
	}
	
	@PostMapping(value="/rejeter-operation")
	public OperationAutorisation rejeterOperation(@RequestBody OperationAutorisation operationAutorisation){
		OperationAutorisation op = opAutoDao.findByStatusIsFalseAndSuprUserIsFalseAndId(operationAutorisation.getId());
		op.setEtat(StatusName.REJETEE);
		op.setAutorisedBy(operationAutorisation.getAutorisedBy());
		return opAutoDao.saveAndFlush(op);
	}
	
	@PostMapping(value="/valide-operation")
	public OperationAutorisation finOperation(@RequestBody OperationAutorisation operationAutorisation){
		
		OperationAutorisation op = opAutoDao.findByStatusIsFalseAndSuprUserIsFalseAndId(operationAutorisation.getId());
		System.out.println("///////");
		System.out.println(op);
		op.setEtat(StatusName.VALIDEE);
	
		return opAutoDao.saveAndFlush(op); 
	}
	
	//@@@@@@ Recupération des opérations par Date @@@@// 
	
	@GetMapping(value="/op-2-dates/{dateOp}/{num}")
	public List<Operation> getOperationBy(@PathVariable Date[] dateOp, @PathVariable Long num){
		System.out.println(dateOp[0]);
		System.out.println(num);
		System.out.println(dateOp[1]);
		return operationRepository.findBySuprIsFalseAndCompteAtoutIsFalseAndDateOpBetweenAndCompte_Id(dateOp[0], dateOp[1], num);
	}
	
	@GetMapping(value="/mes-operation-by-date-between/{dateOp}/{num}")
	public List<Operation> getMesOperationByDateBetween(@PathVariable Date[] dateOp, @PathVariable Long num){
		System.out.println(dateOp);
		return operationRepository.findBySuprIsFalseAndCompteAtoutIsFalseAndDateOpBetweenAndCompte_Id(dateOp[0], dateOp[1], num);
	}
	
	@GetMapping(value="/mes-operation-by-date/{dateOp}/{num}")
	public List<Operation> getMesOperationByDate(@PathVariable Date dateOp, @PathVariable Long num){
		return operationRepository.findBySuprIsFalseAndCompteAtoutIsFalseAndDateOpAndCompte_Id(dateOp, num);
	}
	
	@GetMapping(value="/mes-operation-by-date-greater-than/{dateOp}/{num}")
	public List<Operation> getMesOperationByDateGreaterThan(@PathVariable Date dateOp, @PathVariable Long num){
		return operationRepository.findBySuprIsFalseAndCompteAtoutIsFalseAndDateOpGreaterThanAndCompte_Id(dateOp, num);
	}
	
	@GetMapping(value="/mes-operation-by-date-less-than/{dateOp}/{num}")
	public List<Operation> getMesOperationByDateLessThan(@PathVariable Date dateOp, @PathVariable Long num){
		return operationRepository.findBySuprIsFalseAndCompteAtoutIsFalseAndDateOpLessThanAndCompte_Id(dateOp, num);
	}
	
	
	

}
