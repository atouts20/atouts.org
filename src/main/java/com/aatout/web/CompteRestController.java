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

import com.aatout.dao.CompteRepository;
import com.aatout.dao.CompteValeurRepository;
import com.aatout.dao.OperationRepository;
import com.aatout.model.AppUser;
import com.aatout.model.Compte;
import com.aatout.model.CompteMonnaie;
import com.aatout.model.CompteValeur;
import com.aatout.model.Operation;


@RestController
@CrossOrigin("**")
@RequestMapping("/compte")
public class CompteRestController {
	@Autowired
	private CompteRepository compteRepository;
	
	@Autowired
	private CompteValeurRepository compteValeurRepository;
	@Autowired
	private OperationRepository operationDao;
		

	@GetMapping("/listes")
	public List<Compte> getComptes(){
		return compteRepository.findBySuprIsFalseAndFermerIsFalseAndActiveIsTrue();
		//return compteRepository.findAll();

	}
	
	@GetMapping("/listes-comptes-systemes")
	public List<CompteValeur> getComptesAatout(){
		return compteRepository.findCompteValeurBySuprIsFalseAndFermerIsFalseAndActiveIsTrueAndAatoutIsTrue();
		//return compteRepository.findAll();

	}
	
	

	@GetMapping("/listes-valeurs")
	public List<CompteValeur> getComptesValeurs(){
		return compteRepository.findCompteValeurBySuprIsFalseAndFermerIsFalseAndActiveIsTrue();
		//return compteRepository.findAll();

	}
	@GetMapping("/listes-monnaies")
	public List<CompteMonnaie> getComptesMonnaies(){
		return compteRepository.findCompteMonnaieBySuprIsFalseAndFermerIsFalseAndActiveIsTrue();
		//return compteRepository.findAll();

	}
	@GetMapping("/liste/{numCompte}")
	public Compte getCompte(@PathVariable String numCompte){
		return compteRepository.findCompteByActiveIsTrueAndSuprFalseAndFermerIsFalseAndNumCompte(numCompte);
	}
	@GetMapping("/listes/{idUser}")
	public  List<Compte> getComptesUser(@PathVariable Long idUser){
		
		return compteRepository.findBySuprIsFalseAndFermerIsFalseAndActiveIsTrueAndAppUserCompte_id(3);
	}
	@GetMapping(value="/mon-compte/{id}")
	public CompteValeur getMonCompteValeur(@PathVariable Long id){

		return compteValeurRepository.findBySuprAndFermerAndActiveAndTransAndAppUserCompte_Id(false, false, true, true, id);
	}
	
	@GetMapping("/un/{numCompte}")
	public Compte getComptesUserFavori(@PathVariable String numCompte){
		
		return compteValeurRepository.findBySuprAndFermerAndActiveAndTransAndNumCompte(false, false, true, true, numCompte);
	}
	
	@GetMapping("/consulte/{numCompte}")
	public Compte consulter(@PathVariable String numCompte){
		Compte unCompte = compteRepository.findCompteByActiveIsTrueAndSuprFalseAndFermerIsFalseAndNumCompte(numCompte);
		if (unCompte != null) {
			unCompte.getOperations();
			
			System.out.println(unCompte);
			
		}
		
		return unCompte;
	}
	
	@PostMapping("/save")
	public boolean saveCompte(@RequestBody Compte  compte) {

		Compte unCompte = compteRepository.findCompteByActiveIsTrueAndSuprFalseAndFermerIsFalseAndNumCompte(compte.getNumCompte());

		System.out.println(unCompte);

		if (compte instanceof CompteValeur && unCompte == null ) {

			CompteValeur unCompteValeur = (CompteValeur) compte;

			compteRepository.saveAndFlush(unCompteValeur);	

		} else if ( unCompte != null) {

			unCompte.setNumCompte(compte.getNumCompte());

			compteRepository.save(compte);

		}  if (compte instanceof CompteMonnaie && unCompte == null ) {

			CompteMonnaie unCompteMonnaie = (CompteMonnaie) compte;

			compteRepository.saveAndFlush(unCompteMonnaie);	

		} else if ( unCompte != null) {
			unCompte.setNumCompte(compte.getNumCompte());

			compteRepository.save(compte);

		}
		return true;  
	}

	/*@PostMapping(value="/active-compte/{numcompte}")
	public Compte acvtiver(@PathVariable String numcompte){
		Compte uncompte = compteRepository.findOne(numcompte);
		if(uncompte == null) {
			System.out.println("Vous ne pouvez pas activer un compte");
		}else {
			uncompte.setActive(true);
			compteRepository.save(uncompte);
		}
		return uncompte;
	}
	
	@PostMapping(value="/desactive-compte/{numcompte}")
	public Compte acvtiver(@PathVariable String numcompte){
		Compte uncompte = compteRepository.findOne(numcompte);
		if(uncompte == null) {
			System.out.println("Vous ne pouvez pas activer un compte");
		}else {
			uncompte.setActive(false);
			compteRepository.save(uncompte);
		}
		return uncompte;
	}*/


}
