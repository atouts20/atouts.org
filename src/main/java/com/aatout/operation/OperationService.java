package com.aatout.operation;

import java.util.Date;

import com.aatout.bon.model.Bon;
import com.aatout.model.Compte;

public interface OperationService {
	public Compte consulterCompte(String numCompte);
	public void verser(String numCompte, double montant, double balance, String narrative, Long badge, String createBy, String autorisedBy);
	public void retirer(String numCompte, double montant, double balance, String narrative, Long badge, String createBy, String autorisedBy);
	public void virement(String numCompte1, String numCompte2, double montant, String narrative, Long badge, String createBy, String autorisedBy);
	public void virementTroisCompte(String numCompte1, String numCompte2, String numCompteProduitInteret, double montant, double frais, String narrative, Long badge, String createBy, String autorisedBy);

	
	public void verserEn(String numCompte, String numCompteSysteme, double montant, Long badge, String narrative, String createBy);
	public void retirerEn(String numCompte, String numCompteSysteme, double montant, Long badge, String narrative,String createBy);
	public void virementEn(String numCompte1, String numCompte2, double montant, Long badge, String narrative, String createBy);
	
	
	public void verserBon(String numCompte, double montant, double balance, Long badge, String narrative, String createBy, Bon bon);
	public void retirerBon(String numCompte, double montant, double balance, Long badge, String narrative,String createBy, Bon bon);
	public void virementBon(String numCompte1, double montant, double balance, Long badge, String narrative, String createBy, Bon bon);
	
	
	public void augmenter(String numCompte,String numCompteSysteme, double montant, String narrative, Long badge, String createBy, String autorisedBy);
	public void diminuer(String numCompte, String numCompteSysteme, double montant, String narrative, Long badge, String createBy, String autorisedBy);
	
	public void verserTr( String numCompte1, double montant, long id);
	public void retirerTr( String numCompte1, double montant, long id);	
	public void retirerServiceTr( String numCompte1, double montant, long id);	
	public void verserServiceTr( String numCompte1, double montant, long id);
	  
	
	public Date ajouterJour(Date date, int nbJour);
	
	public Date ajouterMinute(Date date, int minute);
		

}
