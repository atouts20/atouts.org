package com.aatout.evernement.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aatout.evernement.model.Ticket;

public interface TicketDao extends JpaRepository<Ticket, Long> {

}
