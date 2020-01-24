package com.aatout.evernement.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aatout.evernement.dao.EvernementDao;

@RestController
@CrossOrigin("**")
@RequestMapping(value="/evernement")
public class EvernementRestController {
	@Autowired
	private EvernementDao evernementDao;

}
