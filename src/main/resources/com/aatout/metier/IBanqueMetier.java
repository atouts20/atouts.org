package com.aatout.metier;

import org.springframework.data.domain.Page;

import com.aatout.model.Compte;
import com.aatout.model.Operation;

public interface IBanqueMetier {
	
	public  Compte consulterCompte(String codeCte ); 
	public  void verser(String  codeCte ,double montant ); 
	public  void retirer(String codeCte ,double montant ); 
	public  void virement(String  codeCte1,String codeCte2,double montant ); 
	//public  Page<Operation> listOperation(String codeCte ,int  page,int  size); 

}
