package com.aatout.metier;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aatout.dao.CompteRepository;
import com.aatout.dao.OperationRepository;
import com.aatout.model.Compte;
import com.aatout.model.CompteMonnaie;
import com.aatout.model.Depot;
import com.aatout.model.Operation;
import com.aatout.model.Retrait;

@Service 
@Transactional 
public class BanqueMetierImpl implements IBanqueMetier {
	@Autowired
	private CompteRepository compteRepository;
	
	@Autowired
	private OperationRepository operationRepository;

	@Override
	public Compte consulterCompte(String codeCte) {
		Compte  compte= compteRepository .findOne( codeCte ); 
		if(compte==null) throw  new  RuntimeException("Compte introuvable"); 
		 
		return compte; 
	}

	@Override
	public void verser(String codeCte, double montant) {
		Compte  compte=consulterCompte(codeCte ); 
	    Depot opv = new   Depot();
	    operationRepository.save(opv ); 
	    compte.setSolde(compte.getSolde());
	    compteRepository .save(compte); 
		
	}

	@Override
	public void retirer(String codeCte, double montant) {
		Compte  compte= consulterCompte( codeCte ); 
		
	    Retrait opr =new  Retrait();
	    
	    double faciliteCaisse = 0;  
	    
	    if(compte  instanceof CompteMonnaie)
	    	
	      faciliteCaisse =((CompteMonnaie)  compte).getProvision();
	    
	    if(compte.getSolde()+ faciliteCaisse < montant ) 
	    	
	      throw  new  RuntimeException("Solde Insuffisant !" ); 
	    
	    operationRepository.save(opr ); 
	    
	    compte.setSolde(compte.getSolde()- montant ); 
	    
	    compteRepository .save(compte); 		
		
	}

	@Override
	public void virement(String codeCte1, String codeCte2, double montant) {
		if(codeCte1.equals(codeCte2)) 
		      throw  new  RuntimeException("impossible de virer sur le meme compte !" ); 
		    retirer(codeCte1,montant ); 
		    verser( codeCte2,montant ); 
		
	}

	/*@Override
	public Page<Operation> listOperation(String codeCte, int page, int size) {
		return operationRepository.listOperation(codeCte , new  PageRequest( page, size));
	}
*/
	
}
