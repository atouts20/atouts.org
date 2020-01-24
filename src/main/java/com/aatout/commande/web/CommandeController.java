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
import com.aatout.commande.dao.CommanderDao;
import com.aatout.commande.model.Commande;
import com.aatout.commande.model.CommandeForm;
import com.aatout.commande.model.CommandeProduit;
import com.aatout.commande.model.Commander;
import com.aatout.commande.model.Payment;
import com.aatout.dao.CompteMonnaieRepository;
import com.aatout.dao.CompteRepository;
import com.aatout.dao.CompteValeurRepository;
import com.aatout.dao.ProduitRepository;
import com.aatout.dao.UserRepository;
import com.aatout.model.AppUser;
import com.aatout.model.CompteMonnaie;
import com.aatout.model.CompteValeur;
import com.aatout.model.Echange;
import com.aatout.model.Produit;
import com.aatout.operation.OperationService;


@RequestMapping("/commande")
@RestController
@CrossOrigin("**")
public class CommandeController {
	public boolean OkStock = false;
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
	
	/*@Autowired
	private DetailPanierDao detailPanierDao;*/
	
	
	
	
	@GetMapping("/list-admin-payer")
	public List<Commande> listAdminPayer() {
		
		return commandeDao.findByStatusAndPayer(false, true);
	}
	
	@GetMapping("/list-admin-non-payer")
	public List<Commande> listAdminNonPayer() {
		
		return commandeDao.findByStatusAndPayer(false, false);
	}
	
	@DeleteMapping("/supprimer-admin/{id}") 
	public boolean supprimerCommandeAdmin(@PathVariable Long  id) {
		try {
			Commande commande = commandeDao.findOne(id);
			commande.setStatus(true);
			commandeDao.save(commande);
			return true;
		} catch (Exception e) {
			return false;
		}

	}	
	
	
	@GetMapping("/list-user-payer/{client}")
	public List<Commande> listUserPayer(@PathVariable Long client) {
		
		return commandeDao.findByStatusAndSuprAndPayerAndClient_Id(false, false, true, client);
	}
	
	@GetMapping("/list-user-non-payer/{client}")
	public List<Commande> listUserNonPayer(@PathVariable Long client) {
		
		return commandeDao.findByStatusAndSuprAndPayerAndClient_Id(false, false, false, client);
	}	
	
	
	
	@DeleteMapping("/supprimer-user/{id}") 
	public boolean supprimerCommandeUser(@PathVariable Long  id) {
		try {
			Commande commande = commandeDao.findOne(id);
			commande.setSupr(true);
			commandeDao.save(commande);
			return true;
		} catch (Exception e) {
			return false;
		}

	}
	
	
	
	
	@PostMapping("/reverse")
	public CommandeForm retourStock(@RequestBody CommandeForm commandeForm){
		
		for(CommandeProduit p : commandeForm.getProduits()){

			Produit produit = produitRepository.findOne(p.getId());
						
			produit.setStock(produit.getStock() + p.getQuantite());

			produitRepository.saveAndFlush(produit);

		}
		   
				System.out.println(commandeForm);
				return commandeForm;
	}
	
	
	

	@PostMapping("/ajout-commandes")
	@Transactional
	public CommandeForm saveCommande(@RequestBody CommandeForm commandeForm){
		System.out.println(commandeForm);
		CommandeForm uneCommandeForm = new CommandeForm();
		AppUser client = userRepository.findByUsername(commandeForm.getClient().getUsername()); 
		System.out.println("client recherchre");
		System.out.println(client);
		System.out.println(uneCommandeForm);
		String livraison = commandeForm.getClient().getLivraison();
		//Panier panier = client.getPanier();
		
		//List<DetailPanier> detailPaniers  = detailPanierDao.findByIdUser(client.getId());

		long pin = commandeForm.getClient().getPin();

		CompteValeur unCompteValeur = compteValeurRepository.findByPin(pin);
		System.out.println("ttttttttttttttt");
		System.out.println(unCompteValeur);
		CompteValeur compteValeurClient = compteRepository.findBySuprAndFermerAndActiveAndAppUserCompte(false, false, true, client);
		CompteMonnaie compteMonnaieClient = compteMonnaieRepository.findBySuprAndFermerAndActiveAndAppUserCompte(false, false, true, client);
		System.out.println("uuuuuuuuuuuuuu");
		System.out.println(compteValeurClient);
		Commande commande = new Commande();

		if (unCompteValeur == null) {

			uneCommandeForm.setPin(true);

			//return uneCommandeForm;

		} else {			

			if (compteValeurClient == null) {   

				uneCommandeForm.setVerifieCompteClientOK(true);
				//return uneCommandeForm;

			} else {

				if (unCompteValeur != compteValeurClient) {

					uneCommandeForm.setCompteClientOk(true);
					//return uneCommandeForm;

				} else {

					double soldeCompteValeurClient = 0;
					soldeCompteValeurClient = compteValeurClient.getSolde();

					double totalCommandeVl=0;

					for (CommandeProduit p : commandeForm.getProduits()) {
						
						Produit produit = produitRepository.findOne(p.getId());			        	
						//totalCommande += p.getQuantite() * produit.getPrix();  OkStock
						
						if(produit.getStock() - p.getQuantite() < 0) {
							OkStock = true;
						}
						
						totalCommandeVl += produit.gettBCCV()*0.01*produit.getPrix()*p.getQuantite();
						
					}
					
					if (OkStock == true) {

						uneCommandeForm.setStockOk(true);
						//return uneCommandeForm;

					}else {

					if (soldeCompteValeurClient < totalCommandeVl) {

						uneCommandeForm.setSoldeOk(true);
						//return uneCommandeForm;

					}else {

						commande.setClient(client);
						commande.setLivraison(livraison);
						//commande.setPanier(panier);
						
						/*detailPaniers.forEach(r->{
							r.setComd(true);
							detailPanierDao.save(r);
						});*/

						commande.setDate(new Date());
						commande = commandeDao.save(commande);

						//if (commande.getId() != null && commande.getPanier().getItems().size() != 0) {
						//if (commande.getId() != null && detailPaniers.size() != 0) {
						if (commande.getId() != null) {
							double total=0;
							double totalMntVlCMD=0;
							double totalMntMnCMD=0;
							

							for(CommandeProduit p : commandeForm.getProduits()){

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
								
								
								//destockage
								produit.setStock(produit.getStock() - p.getQuantite());
								produitRepository.saveAndFlush(produit);
								
								
								
								total += p.getQuantite() * produit.getPrix();
								
								totalMntVlCMD += mntVlCMD;
								totalMntMnCMD += mntMnCMD;

							}
							
							commande.setTotalAmount(total);
							commandeDao.saveAndFlush(commande);	

							operationService.retirerTr(compteValeurClient.getNumCompte(), totalMntVlCMD, commande.getId());
							operationService.retirerTr(compteMonnaieClient.getNumCompte(), totalMntMnCMD, commande.getId());
							
							uneCommandeForm.setDate(commande.getDate());
							uneCommandeForm.setId(commande.getId());
							//return uneCommandeForm;
							
							
							
							/*detailPaniers.forEach(r->{
								r.setComd(true);
								detailPanierDao.save(r);
							});*/
							
							
						}else {
							
						}
					}           
				}
			  }
			}
		}   
		System.out.println(uneCommandeForm);
		OkStock = false;
		return uneCommandeForm;
	}

	
}
