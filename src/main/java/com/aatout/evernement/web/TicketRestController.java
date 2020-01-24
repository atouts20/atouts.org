package com.aatout.evernement.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aatout.evernement.dao.TicketDao;
@RestController
@CrossOrigin("**")
@RequestMapping(name="/ticket")
public class TicketRestController {
	@Autowired
	private TicketDao ticketDao;
	
	

}
