package com.aatout.commande.web;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aatout.commande.dao.CommandeDao;
import com.aatout.commande.dao.CommandeServiceDao;
import com.aatout.commande.dao.CommanderDao;
import com.aatout.commande.model.Commande;
import com.aatout.commande.model.CommandeForm;
import com.aatout.commande.model.CommandeProduit;
import com.aatout.commande.model.CommandeService;
import com.aatout.commande.model.CommandeServiceForm;
import com.aatout.commande.model.Commander;
import com.aatout.dao.CompteMonnaieRepository;
import com.aatout.dao.CompteRepository;
import com.aatout.dao.CompteValeurRepository;
import com.aatout.dao.ProduitRepository;
import com.aatout.dao.UserRepository;
import com.aatout.model.AppUser;
import com.aatout.model.CompteMonnaie;
import com.aatout.model.CompteValeur;
import com.aatout.model.Produit;
import com.aatout.operation.OperationService;

@RequestMapping("/commande-service")
@RestController
@CrossOrigin("**")
public class CommandeServiceController {
	@Autowired
	private CommandeServiceDao commandeServiceDao;
	

	@Autowired
	private ProduitRepository produitRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CommandeDao commandeDao;
	@Autowired
	private CommanderDao commanderDao;
	@Autowired
	private CompteValeurRepository compteValeurRepository;
	@Autowired
	private CompteMonnaieRepository compteMonnaieRepository;
	@Autowired
	private CompteRepository compteRepository;
	@Autowired
	private OperationService operationService;
	
	
	
	
	
	
	
	@GetMapping("/list-admin-accepter")
	public List<CommandeService> listAdminAccepter() {
		
		return commandeServiceDao.findByStatusAndAnnulerAndAccepter(false, false, true);
	}
	
	@GetMapping("/list-admin-annuler")
	public List<CommandeService> listAdminAnnuler() {
		
		return commandeServiceDao.findByStatusAndAnnulerAndAccepter(false, true, false);
	}
	
	@GetMapping("/list-admin-non-accepter")
	public List<CommandeService> listAdminNonAccepter() {
		
		return commandeServiceDao.findByStatusAndAnnulerAndAccepter(false, false, false);
	}
	
	
	@DeleteMapping("/supprimer-admin/{id}") 
	public boolean supprimerCommandeServiceAdmin(@PathVariable Long  id) {
		try {
			CommandeService commandeService = commandeServiceDao.findOne(id);
			commandeService.setStatus(true);
			commandeServiceDao.save(commandeService);
			return true;
		} catch (Exception e) {
			return false;
		}

	}
	
	
	@GetMapping("/accepter-admin/{id}") 
	public boolean accepterCommandeServiceAdmin(@PathVariable Long  id) {
		try {
			CommandeService commandeService = commandeServiceDao.findOne(id);
			commandeService.setAccepter(true);
			commandeServiceDao.save(commandeService);
			return true;
		} catch (Exception e) {
			return false;
		}

	}
	
	@GetMapping("/annuler-admin/{id}") 
	public boolean annulerCommandeServiceAdmin(@PathVariable Long  id) {
		try {
			CommandeService commandeService = commandeServiceDao.findOne(id);
			commandeService.setAnnuler(true);
			commandeService.setAccepter(false);
			commandeServiceDao.save(commandeService);
			return true;
		} catch (Exception e) {
			return false;
		}

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
		
	@GetMapping("/list-user-accepter/{client}")
	public List<CommandeService> listUserAccepter(@PathVariable Long client) {
		
		return commandeServiceDao.findByStatusAndSuprAndAccepterAndClient_Id(false, false, true, client);
	}
	
	@GetMapping("/list-user-non-accepter/{client}")
	public List<CommandeService> listUserNonAccepter(@PathVariable Long client) {
		
		return commandeServiceDao.findByStatusAndSuprAndAccepterAndClient_Id(false, false, false, client);
	}	
	
	
	
	@DeleteMapping("/supprimer-user/{id}") 
	public boolean supprimerCommandeServiceUser(@PathVariable Long  id) {
		try {
			CommandeService commandeService = commandeServiceDao.findOne(id);
			commandeService.setSupr(true);
			commandeServiceDao.save(commandeService);
			return true;
		} catch (Exception e) {
			return false;
		}

	}
	
	
	
	@PostMapping("/ajout-commande-service")
	@Transactional
	public CommandeServiceForm saveCommandeService(@RequestBody CommandeServiceForm commandeServiceForm){
		System.out.println(commandeServiceForm);
		CommandeServiceForm unecommandeServiceForm = new CommandeServiceForm();
		AppUser client = userRepository.findByUsername(commandeServiceForm.getClient().getUsername()); 
		System.out.println("client recherchre");
		System.out.println(client);
		System.out.println(unecommandeServiceForm);
		String livraison = commandeServiceForm.getClient().getLivraison();
		String detail = commandeServiceForm.getClient().getDetail();

		long pin = commandeServiceForm.getClient().getPin();
		System.out.println(pin);
		CompteValeur unCompteValeur = compteValeurRepository.findByPin(pin);
		System.out.println("ttttttttttttttt");
		System.out.println(unCompteValeur);
		CompteValeur compteValeurClient = compteRepository.findBySuprAndFermerAndActiveAndAppUserCompte(false, false, true, client);
		//CompteMonnaie compteMonnaieClient = compteMonnaieRepository.findBySuprAndFermerAndActiveAndAppUserCompte(false, false, true, client);
		System.out.println("uuuuuuuuuuuuuu");
		System.out.println(compteValeurClient);
		CommandeService commandeService = new CommandeService();

		if (unCompteValeur == null) {

			unecommandeServiceForm.setPin(true);

			//return uneCommandeForm;

		} else {			

			if (compteValeurClient == null) {   

				unecommandeServiceForm.setVerifieCompteClientOK(true);
				//return uneCommandeForm;

			} else {

				if (unCompteValeur != compteValeurClient) {

					unecommandeServiceForm.setCompteClientOk(true);
					//return uneCommandeForm;

				} else {

					double soldeCompteValeurClient = 0;
					soldeCompteValeurClient = compteValeurClient.getSolde();

					double caution = commandeServiceForm.getService().getCaution();

					/*for (CommandeProduit p : commandeForm.getProduits()) {
						
						Produit produit = produitRepository.findOne(p.getId());			        	
						//totalCommande += p.getQuantite() * produit.getPrix();
						
						totalCommandeVl += produit.gettBCCV()*0.01*produit.getPrix()*p.getQuantite();
						
					}*/

					if (soldeCompteValeurClient < caution) {

						unecommandeServiceForm.setSoldeOk(true);
						//return uneCommandeForm;

					}else {

						commandeService.setClient(client);
						commandeService.setService(commandeServiceForm.getService());
						commandeService.setLivraison(livraison);
						commandeService.setDetail(detail);
						
						commandeService.setDate(new Date());
						commandeService = commandeServiceDao.save(commandeService);

						if (commandeService.getId() != null) {

							//double total=0;
							//double totalMntVlCMD=0;
							//double totalMntMnCMD=0;
							

							/*for(CommandeProduit p : commandeForm.getProduits()){

								Commander commander = new Commander();
								//commander.getCreateBy()
								commander.setCommande(commande);

								Produit produit = produitRepository.findOne(p.getId());
								
								
								
								commander.setProduit(produit);

								commander.setPrix(produit.getPrix());
								commander.setQuantite(p.getQuantite());
								
								Double mntVlCMD = produit.gettBCCV()*0.01*produit.getPrix()*p.getQuantite();
								
								Double mntMnCMD = (1 - produit.gettBCCV()*0.01)*produit.getPrix()*p.getQuantite();

								commanderDao.save(commander);
								produit.setStock(produit.getStock() - p.getQuantite());

								produitRepository.saveAndFlush(produit);
								
								total += p.getQuantite() * produit.getPrix();
								
								totalMntVlCMD += mntVlCMD;
								totalMntMnCMD += mntMnCMD;

							}*/
							
							//commande.setTotalAmount(total);
							//commandeDao.saveAndFlush(commande);	   
							System.out.println("ididididididi");	   
							System.out.println(commandeService.getId());

							operationService.retirerServiceTr(compteValeurClient.getNumCompte(), caution, commandeService.getId());
							operationService.verserServiceTr(compteValeurClient.getNumCompte(), caution, commandeService.getId());
							
							
							//operationService.retirerTr(compteMonnaieClient.getNumCompte(), totalMntMnCMD, commande.getId());
							
							unecommandeServiceForm.setDate(commandeService.getDate());
							unecommandeServiceForm.setId(commandeService.getId());
							//return uneCommandeForm;
						}			        
					}           
				}
			}
		}   
		System.out.println(unecommandeServiceForm);
		return unecommandeServiceForm;
	}
	
	
	

}
