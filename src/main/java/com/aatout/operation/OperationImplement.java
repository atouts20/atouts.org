package com.aatout.operation;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aatout.bon.model.Bon;
import com.aatout.commande.dao.CommandeDao;
import com.aatout.commande.dao.CommandeServiceDao;
import com.aatout.commande.model.Commande;
import com.aatout.commande.model.CommandeService;
import com.aatout.dao.CompteRepository;
import com.aatout.dao.OperationAutorisationDao;
import com.aatout.dao.OperationRepository;
import com.aatout.model.Compte;
import com.aatout.model.CompteValeur;
import com.aatout.model.Depot;
import com.aatout.model.DepotEn;
import com.aatout.model.DepotTR;
import com.aatout.model.EmissionBon;
import com.aatout.model.EncaisseBon;
import com.aatout.model.Retrait;
import com.aatout.model.RetraitEn;
import com.aatout.model.RetraitTR;
@Service
@Transactional
public class OperationImplement implements OperationService {
	@Autowired
	private CompteRepository compteRepository;
	@Autowired
	private OperationRepository operationRepository;
	
	@Autowired
	private OperationAutorisationDao operationEn;
	@Autowired
	private CommandeDao commandeDao;
	@Autowired
	private OperationRepository operationTransactionDao;
	@Autowired
	private CommandeServiceDao commandeServiceDao; 
	

	@Override
	public Compte consulterCompte(String numCompte) {
		Compte unCompte = compteRepository.findCompteByActiveIsTrueAndSuprFalseAndFermerIsFalseAndNumCompte(numCompte);
		if(unCompte == null) throw new RuntimeException("Compte introuvable");
		return unCompte;
	}

	@Override
	public void verser(String numCompte, double montant, double balance, String narrative, Long badge, String createBy, String autorisedBy) {
		
		Compte unCompte = consulterCompte(numCompte);
		
		Depot depot = new Depot(null, new Date(), montant, narrative, badge, unCompte );
		depot.setBalanceInit(unCompte.getSolde());
		depot.setBalance(unCompte.getSolde() + montant);
		System.out.println(depot); 
		operationRepository.save(depot);
		
		unCompte.setSolde(unCompte.getSolde() + montant);
		
		compteRepository.saveAndFlush(unCompte);
		
	} 

	@Override
	public void retirer(String numCompte, double montant, double balance, String narrative, Long badge, String createBy, String autorisedBy) {
		Compte unCompte = consulterCompte(numCompte);
		double facilitesCaisse = 0;
		if(unCompte instanceof CompteValeur)
			facilitesCaisse = ((CompteValeur) unCompte).getProvision();
		if(unCompte.getSolde() + facilitesCaisse < montant)
			throw new RuntimeException("Solde insuffisant");
		Retrait retrait = new Retrait(null, new Date(), montant, narrative, badge, unCompte);
		retrait.setBalanceInit(unCompte.getSolde());
		retrait.setBalance(unCompte.getSolde() - montant);
		operationRepository.save(retrait);
		unCompte.setSolde(unCompte.getSolde() - montant);
		compteRepository.saveAndFlush(unCompte);
		
	}

	@Override
	public void virement(String numCompte1, String numCompte2, double montant, String narrative, Long badge, String createBy, String autorisedBy) {
		if(numCompte1.equals(numCompte2)){
			throw new RuntimeException("Impossibile de faire un virement sur le même compte");
		}
		retirer(numCompte1, montant, montant, narrative, badge, createBy, autorisedBy);
		verser(numCompte2, montant, montant,narrative, badge, createBy, autorisedBy);		
	}

	@Override
	public void augmenter(String numCompte, String numCompteSysteme, double montant, String narrative, Long badge, String createBy, String autorisedBy) {
		if(numCompte.equals(numCompteSysteme)){
			throw new RuntimeException("Impossibile de faire un virement sur le même compte");
		}
		verser(numCompte, montant, montant, narrative, badge, createBy, autorisedBy);	
		verser(numCompteSysteme, montant, montant, narrative, badge, createBy, autorisedBy);
		
	}

	@Override
	public void diminuer(String numCompte, String numCompteSysteme, double montant, String narrative, Long badge, String createBy, String autorisedBy) {
		if(numCompte.equals(numCompteSysteme)){
			throw new RuntimeException("Impossibile de faire un virement sur le même compte");
		}	
		retirer(numCompteSysteme, montant, montant, narrative, badge, createBy, autorisedBy);
		retirer(numCompte, montant, montant, narrative, badge, createBy, autorisedBy);
		
	}
	@Override
	public void verserEn(String numCompte, String numCompteSysteme, double montant, Long badge, String narrative, String createBy) {
		
		Compte unCompte = consulterCompte(numCompte);
		DepotEn depotEn = new DepotEn(null,  new Date(), montant, narrative, badge, numCompteSysteme, unCompte);
		operationEn.save(depotEn);
		
		/*unCompte.setSolde(unCompte.getSolde() + montant);
		
		compteRepository.saveAndFlush(unCompte);*/ 
		
	}

	@Override
	public void retirerEn(String numCompte, String numCompteSysteme, double montant, Long badge, String narrative, String createBy) {
		Compte unCompte = consulterCompte(numCompte);
		double facilitesCaisse = 0;
		if(unCompte instanceof CompteValeur)
			facilitesCaisse = ((CompteValeur) unCompte).getProvision();
		if(unCompte.getSolde() + facilitesCaisse < montant)
			throw new RuntimeException("Solde insuffisant");
		RetraitEn retrait = new RetraitEn(null, new Date(), montant, narrative, badge, numCompteSysteme, unCompte);
		operationEn.save(retrait);
		/*unCompte.setSolde(unCompte.getSolde() - montant);
		compteRepository.saveAndFlush(unCompte);*/
		
	}

	@Override
	public void virementEn(String numCompte1, String numCompte2, double montant, Long badge, String narrative, String createBy) {
		if(numCompte1.equals(numCompte2)){
			throw new RuntimeException("Impossibile de faire un virement sur le même compte");
		}
		//retirerEn(numCompte1, numCompte2, montant, narrative);
		//verserEn(numCompte2, numCompte2, montant, narrative);		
	}

	@Override
	public void verserTr(String numCompte1, double montant, long id) {
		Compte unCompte = consulterCompte(numCompte1);
		Commande uneCommande = commandeDao.findOne(id);
		DepotTR depot = new DepotTR();
		depot.setDateOp(new Date());
		depot.setMontantOp(montant);
		depot.setCompte(unCompte);
		depot.setCommande(uneCommande);
		operationTransactionDao.save(depot);

		unCompte.setSolde(unCompte.getSolde() + montant);

		compteRepository.saveAndFlush(unCompte);		
	}
	
	@Override
	public void verserServiceTr(String numCompte1, double montant, long id) {
		Compte unCompte = consulterCompte(numCompte1);
		Commande uneCommande = commandeDao.findOne(id);
		DepotTR depot = new DepotTR();
		depot.setDateOp(new Date());
		depot.setMontantOp(montant);
		depot.setCompte(unCompte);
		depot.setCommande(uneCommande);
		operationTransactionDao.save(depot);

		unCompte.setSolde(unCompte.getSolde() + montant);

		compteRepository.saveAndFlush(unCompte);		
	}

	@Override
	public void retirerTr(String numCompte1, double montant, long id) {
		Compte unCompte = consulterCompte(numCompte1);
		Commande uneCommande = commandeDao.findOne(id);
		double facilitesCaisse = 0;
		if(unCompte instanceof CompteValeur)
			facilitesCaisse = ((CompteValeur) unCompte).getProvision();
		if(unCompte.getSolde() + facilitesCaisse < montant)
			throw new RuntimeException("Solde insuffisant");
		RetraitTR retrait = new RetraitTR();
		
		retrait.setDateOp(new Date());
		retrait.setMontantOp(montant);
		retrait.setCompte(unCompte);
		retrait.setCommande(uneCommande);
		operationTransactionDao.save(retrait);
		unCompte.setSolde(unCompte.getSolde() - montant);
		compteRepository.saveAndFlush(unCompte);		
	}

	@Override
	public void retirerServiceTr(String numCompte1, double montant, long id) {
		Compte unCompte = consulterCompte(numCompte1);
		CommandeService uneCommandeService = commandeServiceDao.findOne(id);
		double facilitesCaisse = 0;
		if(unCompte instanceof CompteValeur) {
			facilitesCaisse = ((CompteValeur) unCompte).getProvision();
			if(unCompte.getSolde() + facilitesCaisse < montant)
				throw new RuntimeException("Solde insuffisant");
		}
		
		RetraitTR retrait = new RetraitTR();	
		retrait.setDateOp(new Date());
		retrait.setMontantOp(montant);
		retrait.setCompte(unCompte);
		retrait.setCommandeService(uneCommandeService);
		operationTransactionDao.save(retrait);
		unCompte.setSolde(unCompte.getSolde() - montant);
		compteRepository.saveAndFlush(unCompte);		
	}

	@Override
	public void virementTroisCompte(String numCompte1, String numCompte2, String numCompteProduitInteret,
			double montant, double frais, String narrative, Long badge, String createBy, String autorisedBy) {
		retirer(numCompte1, montant, montant, narrative, badge, createBy, autorisedBy);
		verser(numCompte2, montant, montant,narrative, badge, createBy, autorisedBy);
		verser(numCompteProduitInteret, frais, montant, narrative, badge, createBy, autorisedBy);
		
	}

	@Override
	public Date ajouterJour(Date date, int nbJour) {
		Calendar cal = Calendar.getInstance(); 
		  
		cal.setTime(date);
		  cal.add(Calendar.DATE, nbJour);
		  return cal.getTime();
	}

	@Override
	public void verserBon(String numCompte, double montant, double balance, Long badge, String narrative,
			String createBy, Bon bon) {
		Compte unCompte = consulterCompte(numCompte);
		
		EmissionBon emissionBon = new EmissionBon();
		emissionBon.setBon(bon);
		emissionBon.setMontantOp(montant);
		emissionBon.setBadge(badge);
		emissionBon.setNarrative(narrative);
		emissionBon.setCreateBy(createBy);
		emissionBon.setCompte(unCompte);
		emissionBon.setBalance(balance);
		
		operationRepository.save(emissionBon);
		
		unCompte.setSolde(unCompte.getSolde() + montant);
		
		compteRepository.saveAndFlush(unCompte);
		
	}

	@Override
	public void retirerBon(String numCompte, double montant, double balance, Long badge, String narrative,
			String createBy, Bon bon) {
		Compte unCompte = consulterCompte(numCompte);
		double facilitesCaisse = 0;
		if(unCompte instanceof CompteValeur)
			facilitesCaisse = ((CompteValeur) unCompte).getProvision();
		if(unCompte.getSolde() + facilitesCaisse < montant)
			throw new RuntimeException("Solde insuffisant");
		EncaisseBon encaisseBon = new EncaisseBon();
		encaisseBon.setBon(bon);
		encaisseBon.setMontantOp(montant/1.01);
		encaisseBon.setBadge(badge);
		encaisseBon.setNarrative(narrative);  
		encaisseBon.setCreateBy(createBy);
		encaisseBon.setCompte(unCompte);
		encaisseBon.setBalance(balance);
		
		operationRepository.save(encaisseBon);
		unCompte.setSolde(unCompte.getSolde() - montant);
		compteRepository.saveAndFlush(unCompte);
		
	}  

	@Override
	public void virementBon(String numCompte1, double montant, double balance, Long badge, String narrative,
			String createBy, Bon bon) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Date ajouterMinute(Date date, int minute) {
		Calendar cal = Calendar.getInstance(); 
		  
		cal.setTime(date);
	
		  cal.add(Calendar.MINUTE, minute);
		  return cal.getTime();
	}	
	
	
}
