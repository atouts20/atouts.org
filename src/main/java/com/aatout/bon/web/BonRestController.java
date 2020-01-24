package com.aatout.bon.web;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aatout.bon.dao.BonDao;
import com.aatout.bon.model.Bon;
import com.aatout.dao.CompteRepository;
import com.aatout.dao.UserRepository;
import com.aatout.model.AppUser;
import com.aatout.model.CompteValeur;
import com.aatout.model.Depot;
import com.aatout.model.EmissionBon;
import com.aatout.model.EncaisseBon;
import com.aatout.model.LiquidationBon;
import com.aatout.operation.OperationService;

@RestController
@CrossOrigin("*")
@RequestMapping(value="/bon")
@Transactional
public class BonRestController {
	@Autowired
	private BonDao bonDao;
	
	@Autowired
	private CompteRepository compteRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OperationService operationService;
	
	
	@GetMapping(value = "/list")
	public List<Bon> getBons() {
		return bonDao.findByStatus(false);
	}
	
	@GetMapping(value = "/verifier-un-bon/{numeroBon}")
	public Bon getVerifierBon(@PathVariable String numeroBon) {
		Bon unBon = bonDao.findByStatusIsFalseAndEncaisseIsFalseAndLiquideIsFalseAndNumeroBonIs(numeroBon);
		System.out.println();
		return unBon;
	}

	@GetMapping(value = "/lists-bons")
	public List<Bon> getBon() {
		return bonDao.findByStatusIsFalseAndEncaisseIsFalseAndLiquideIsFalse();
	}
	
	@GetMapping(value = "/lists-encaisses")
	public List<Bon> getEncaisse() {
		return bonDao.findByStatusIsFalseAndEncaisseIsTrueAndLiquideIsFalse();
	}
	
	@GetMapping(value = "/lists-liquides")
	public List<Bon> getLiquide() {
		return bonDao.findByStatusIsFalseAndEncaisseIsTrueAndLiquideIsTrue();
	}
	@Transactional
	@PostMapping(value = "/save")
	public Bon save(@RequestBody Bon bon) {
		Bon unBon = bonDao.findByStatusIsFalseAndEncaisseIsFalseAndLiquideIsFalseAndId(bon.getId());
		
		AppUser appUser = userRepository.findByUsername(bon.getCreateBy());
		
		CompteValeur compteValeur = compteRepository.findBySuprAndFermerAndActiveAndAppUserCompte(false, false, true, appUser);	
		
		Random rcd = new Random();
		Long valeurMin = 10000L;
		Long valeur = valeurMin + rcd.nextLong();
		
		Long n = (long) (Math.random() * valeur);
		
		
		if (unBon == null) {
			
			Double monSolde = compteValeur.getSolde();	
			
			
			System.out.println(monSolde);
			
			Double montant =  1.01 * bon.getMontant();
			
			System.out.println(montant);	
			
			
			
			if (monSolde >= montant && bon.getMontant() >= 500) {
				
				Double topMontant = monSolde - montant;
				
				Double frais = montant - bon.getMontant();
				
				/*System.out.println(topMontant);
				
				compteValeur.setSolde(topMontant);
				
				compteRepository.save(compteValeur);
								
				//bon.getCompteValeurs().setSolde(topMontant);
*/				
				bon.setCompteValeurs(compteValeur);
				bon.setNumeroBon(UUID.randomUUID().toString());
				bon.setSecret(n); 
				bon.setMontant(bon.getMontant());
				bon.setDateExpiration(operationService.ajouterJour(new Date(), 3));
				bonDao.saveAndFlush(bon);
				
				operationService.retirerBon(compteValeur.getNumCompte(), montant, topMontant, n, bon.getDescription(), bon.getCreateBy(), bon);
				operationService.verserBon("CHARGE_INTERET_003", frais, topMontant, n, bon.getDescription(), bon.getCreateBy(), bon);
				
			}	
			   
		}
		else { 
			System.out.println("bbbbbbbbb");
		}
		return bon;
	}


	@PostMapping(value = "/encaisse")
	public Bon encaisser(@RequestBody Bon bon) {
		System.out.println(bon);
		Bon unBon = bonDao.findByStatusIsFalseAndEncaisseIsFalseAndLiquideIsFalseAndSecret(bon.getSecret());
		System.out.println("UnBon trouver hooo =====!!!!!");
		System.out.println(unBon);
		if (unBon != null) {
			
			unBon.setEncaisse(true);
			
			unBon.setEncaisseBy(bon.getEncaisseBy());
			
			bonDao.saveAndFlush(unBon);	
			
			//operationBonDao.save(new EncaisseBon(unBon.getMontant(), unBon));		
			
		}
		else {
			System.out.println("bbbbbbbbb");
		}
		return unBon;
	}

	@PostMapping(value = "/liquide")
	public Bon liquider(@RequestBody Bon bon) {
		
		System.out.println(bon.getId());
		
		System.out.println(bon);
		
		Bon unBon = bonDao.findByStatusIsFalseAndEncaisseIsFalseAndLiquideIsFalseAndNumeroBonIs(bon.getNumeroBon());
		System.out.println(unBon);
		Random rcd = new Random();
		Long valeurMin = 10000L;
		Long valeur = valeurMin + rcd.nextLong();
		
		Long n = (long) (Math.random() * valeur);
		
		if (unBon != null) {
			
			unBon.setLiquideBy(bon.getLiquideBy());
			
			unBon.setLiquide(true);
			
			AppUser appUser = userRepository.findByUsername(unBon.getEncaisseBy());
			
			CompteValeur compteValeur = compteRepository.findBySuprAndFermerAndActiveAndAppUserCompte(false, false, true, appUser);
			double topMontant = compteValeur.getSolde() + unBon.getMontant();
			bon.setCompteValeurs(compteValeur);
			bon.setNumeroBon(UUID.randomUUID().toString());
			bon.setSecret(n); 
			bon.setMontant(bon.getMontant());
			bon.setDateExpiration(operationService.ajouterJour(new Date(), 3));
			bonDao.saveAndFlush(bon);
			operationService.verserBon(compteValeur.getNumCompte(), unBon.getMontant(), topMontant, n, bon.getDescription(), bon.getCreateBy(), unBon);
			//operationService.virerBon(compteValeur.getNumCompte(), unBon.getMontant(), compteValeur.getSolde(), n, bon.getDescription(), bon.getCreateBy(), bon);
			 
			//operationBonDao.save(new LiquidationBon(unBon.getMontant(), unBon));
			
			 
		}
		else {
			System.out.println("bbbbbbbbb");
		}
		return unBon;
		
	}

	@PostMapping(value = "/delete")
	public Bon delete(@RequestBody Bon bon) {

		bon.setStatus(true);
		return	bonDao.saveAndFlush(bon); 
	}

}
