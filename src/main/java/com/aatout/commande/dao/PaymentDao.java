package com.aatout.commande.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aatout.commande.model.Payment;

public interface PaymentDao extends JpaRepository<Payment, Long>{
	public Payment findByStatusAndSuprAndCodePayment(boolean status, boolean supr, String codePayement);
	public List<Payment> findByStatus(boolean status);
	public List<Payment> findByStatusAndSupr(boolean status, boolean supr);
	
}
