package com.aatout.commande.web;

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

import com.aatout.commande.dao.CommandeDao;
import com.aatout.commande.dao.PaymentDao;
import com.aatout.commande.model.Commande;
import com.aatout.commande.model.Payment;
import com.aatout.dao.CompteMonnaieRepository;
import com.aatout.dao.CompteValeurRepository;
import com.aatout.model.CompteMonnaie;
import com.aatout.model.CompteValeur;
import com.aatout.operation.OperationService;


@RequestMapping("/payment")
@RestController
@CrossOrigin("**")
public class PaymentController {
	public double totalMntMn;	
	@Autowired
	private PaymentDao paymentDao;

	@Autowired
	private CommandeDao commandeDao;
	
	@Autowired
	private CompteValeurRepository compteValeurRepository;
	
	@Autowired
	private CompteMonnaieRepository compteMonnaieRepository;
	
	@Autowired
	private OperationService operationService;
	
	
	
	
	@GetMapping("/list/{codePayement}")
	public Payment getUnPayment(@PathVariable String codePayement) {		
		return paymentDao.findByStatusAndSuprAndCodePayment(false, false, codePayement);
	}
	
	
	
	
	@GetMapping("/list")
	public List<Payment> list() {
		
		return paymentDao.findByStatus(false);
	}
	
	
	@GetMapping("/list-by-user")
	public List<Payment> listUser() {
		
		return paymentDao.findByStatusAndSupr(false, false);
	}
	
	
	
	
	
	@PostMapping("/save")
	@Transactional
	public Payment save(@RequestBody Payment payment) {	
		
		System.out.println("************************");
		System.out.println(payment.toString());
		System.out.println("*************************");
		
		Random rcd = new Random();
		int valeurMin = 10000;
		int valeurMax = 99999999;
		int valeur = valeurMin + rcd.nextInt(valeurMax - valeurMin);		
		int n = (int)(Math.random() * valeur);
		
		
		
		String uuid = UUID.randomUUID().toString();		
		int t=uuid.length();
		String chaine = uuid.substring(32,t);
		
		
		String code = chaine.toUpperCase() + n;
		
		
		
		Commande uneCommande = commandeDao.getOne(payment.getCommande().getId());	
		
		payment.setDatePayment(new Date());
		payment.setMontant(uneCommande.getTotalAmount());
		payment.setCodePayment(code);
		

		System.out.println("************************");
		System.out.println(payment.toString());
		System.out.println("*************************");	
		
		
		//dispashing		
		uneCommande.getCommanders().forEach(c ->{
			//c.getProduit().gettBCCV();
			Double mtVl = c.getProduit().gettBCCV()*0.01*c.getPrix()*c.getQuantite();
			Double mtMn = (1 - c.getProduit().gettBCCV()*0.01)*c.getPrix()*c.getQuantite();
			
			
			System.out.println("************************");
			System.out.println("dddddddddddddddddDDDDDDDDDDDuuuuuuuuuuuuuu");
			System.out.println("*************************");
			
			
						
			c.getProduit().getProprietaire().getGroupers().forEach(g ->{
				
			CompteValeur cv = compteValeurRepository.findByTransAndAppUserCompte(true, g.getAppUser());
			String numCv = cv.getNumCompte();
			Double partVlUser = mtVl*g.getPartvl();
			
			
			System.out.println("************************");
			System.out.println( numCv +"VVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVuuuuuuu");
			System.out.println("*************************");
			

			CompteMonnaie cm = compteMonnaieRepository.findByTransAndAppUserCompte(true, g.getAppUser());
			String numCm = cm.getNumCompte();
			Double partMnUser = mtMn*g.getPartmn();
			
			System.out.println("************************");
			System.out.println(    numCm     +" MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMuuuuuuuuuuuuuu");
			System.out.println("*************************");
			
			operationService.verserTr(numCv, partVlUser, uneCommande.getId());
			operationService.verserTr(numCm, partMnUser, uneCommande.getId());
			
			
			System.out.println("************************");
			System.out.println("OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOuuuuuuuuuuuuuu");
			System.out.println("*************************");
			
			});					

			totalMntMn += mtMn;
			
		});
		
		System.out.println("************************");
		System.out.println("uuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuu");
		System.out.println("*************************");
		
		
		
		
		CompteMonnaie cmClient = compteMonnaieRepository.findByTransAndAppUserCompte(true, uneCommande.getClient());
		
		operationService.verserTr(cmClient.getNumCompte(), totalMntMn, uneCommande.getId());
		
		
		
				
		System.out.println("************************");
		System.out.println(payment.toString());
		System.out.println("*************************");
		
	    Payment unPayment =	paymentDao.saveAndFlush(payment);
	        
	    
	    
		Commande commande = new Commande();		
		commande = unPayment.getCommande();
		commande.setPayer(true);
		commandeDao.saveAndFlush(commande);
		
		
				
		
		return unPayment;
	}
	
	
	
	@PostMapping("/delete")
	public Payment delete(@RequestBody Payment payment) {
		payment.setStatus(true);
		paymentDao.saveAndFlush(payment);
		return payment;
	}
	
	
	@PostMapping("/detete-by-user")
	public Payment deleteUser(@RequestBody Payment payment) {
		payment.setSupr(true);
		paymentDao.saveAndFlush(payment);
		return payment;
	}

}
