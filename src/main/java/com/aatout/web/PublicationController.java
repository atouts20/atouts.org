package com.aatout.web;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.aatout.dao.ProduitRepository;
import com.aatout.dao.PublicationRepository;
import com.aatout.dao.ServiceRepository;
import com.aatout.dao.UserRepository;
import com.aatout.model.AppUser;
import com.aatout.model.Bien;
import com.aatout.model.Produit;
import com.aatout.model.Publication;
import com.aatout.model.Services;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@RestController
@CrossOrigin("**")
@RequestMapping("/publication")
public class PublicationController {
	
	@Autowired
	private PublicationRepository publicationRepository;
	
	@Autowired
	private UploadFile uploadFile;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ProduitRepository produitRepository;
	
	@Autowired
	private ServiceRepository serviceRepository;

	
	
	
	/*@RequestMapping(value="/listProduitDemandePub")
	public List<Publication> listerServPub(){
		return publicationRepository.listPrdPub();
	}
	
	@RequestMapping(value="/listServiceDemandePub")
	public List<Publication> listerPrdPub(){
		return publicationRepository.listServPub();
	}*/
	 
	/* DEBUT *** LES METHODES UTILISEE PAR UTILISATEUR */
	
	/* METHODE DE GET DES BIENS REJETER */
	
	@GetMapping(value="/list-demande-pub-non-accept/{proprietaires}")
	public Page<Publication> listeDemandePublication(
			@RequestParam(name="mc", defaultValue="")String mc,
			@RequestParam(name="page", defaultValue="0")int page,
			@RequestParam(name="size",defaultValue="8")int size,
			@RequestParam(name="sortBy", defaultValue = "id") String sortBy,
			@PathVariable Long proprietaires){
		String sortOrder = "desc";
    	Sort sort = new Sort(Direction.fromString(sortOrder), sortBy);
		return publicationRepository.findByStatusAndSupUserAndAccepterAndRejeterAndCreerAndProprietaire_idAndNomLikeIgnoreCase( new PageRequest(page, size, sort), false, false, false, true, false, proprietaires, "%"+mc+"%");
	}
	
	/* METHODE DE GET DES BIENS ACCEPTER 
	@GetMapping(value="/list-demande-pub-non-creer/{proprietaires}")
	public Page<Publication> listerDemPubNCreer(
			@RequestParam(name="mc", defaultValue="")String mc,
			@RequestParam(name="page", defaultValue="0")int page,
			@RequestParam(name="size",defaultValue="8")int size,
			@RequestParam(name="sortBy", defaultValue = "id") String sortBy,
			@PathVariable Long proprietaires){
		String sortOrder = "desc";
    	Sort sort = new Sort(Direction.fromString(sortOrder), sortBy);
		return publicationRepository.findByStatusAndSupUserAndAccepterAndRejeterAndCreerAndProprietaire_idAndNomLikeIgnoreCase( new PageRequest(page, size, sort), false, false, true, false, false, proprietaires, "%"+mc+"%");
	}
	*/
	/* METHODE DE GET DES BIENS EN DEMANDE */
	@GetMapping(value="/list-demande-pub/{proprietaires}")
	public Page<Publication> listerDemPub(
			@RequestParam(name="mc", defaultValue="")String mc,
			@RequestParam(name="page", defaultValue="0")int page,
			@RequestParam(name="size",defaultValue="8")int size,
			@RequestParam(name="sortBy", defaultValue = "id") String sortBy,
			@PathVariable Long proprietaires){
		String sortOrder = "desc";
    	Sort sort = new Sort(Direction.fromString(sortOrder), sortBy);
		return publicationRepository.findByStatusAndSupUserAndAccepterAndRejeterAndCreerAndProprietaire_idAndNomLikeIgnoreCase( new PageRequest(page, size, sort), false, false, false, false, false, proprietaires, "%"+mc+"%");
	}
	/* METHODE DE GET DES BIENS CREER */
	@GetMapping(value="/list-demande-pub-accepter/{proprietaires}")
	public Page<Publication> listerDemPubAccepter(
			@RequestParam(name="mc", defaultValue="")String mc,
			@RequestParam(name="page", defaultValue="0")int page,
			@RequestParam(name="size",defaultValue="8")int size,
			@RequestParam(name="sortBy", defaultValue = "id") String sortBy,
			@PathVariable Long proprietaires){
		String sortOrder = "desc";
    	Sort sort = new Sort(Direction.fromString(sortOrder), sortBy);
		return publicationRepository.findByStatusAndSupUserAndAccepterAndRejeterAndCreerAndProprietaire_idAndNomLikeIgnoreCase( new PageRequest(page, size, sort), false, false, true, false, true, proprietaires, "%"+mc+"%");
	}
	
	/* FIN *** LES METHODES UTILISEE PAR UTILISATEUR */
	
	
	/* DEBUT *** LES METHODES UTILISEE PAR ADMIN */
	
	/* DEBUT *** LES METHODES UTILISEE PAR ADMIN listDemandeService */
	@GetMapping(value="/list-demande-service")
	public List<Publication> listDemandeService(){
		return publicationRepository.findByCatLikeAndStatusAndSupUserAndAccepterAndRejeterAndCreer("SERV", false, false, false,false, false);
		//return publicationRepository.findAll();
	}
	
	/* DEBUT *** LES METHODES UTILISEE PAR ADMIN listDemandeProduit */
	@GetMapping(value="/list-demande-produit")
	public List<Publication> listDemandeProduit(){
		return publicationRepository.findByCatLikeAndStatusAndSupUserAndAccepterAndRejeterAndCreer("PROD", false, false, false,false, false);
		//return publicationRepository.findAll();
	}
	
	
	/* DEBUT *** LES METHODES UTILISEE PAR ADMIN listDemandeProduitRejeter */
	@GetMapping(value="/list-demande-produit-rejeter")
	public List<Publication> listDemandeProduitRejeter(){
		return publicationRepository.findByCatLikeAndStatusAndSupUserAndAccepterAndRejeterAndCreer("PROD", false, false, false,true, false);
	}
	/* DEBUT *** LES METHODES UTILISEE PAR ADMIN listDemandeService */
	@GetMapping(value="/list-demande-service-rejeter")
	public List<Publication> listDemandeServiceRejeter(){
		return publicationRepository.findByCatLikeAndStatusAndSupUserAndAccepterAndRejeterAndCreer("SERV", false, false, false,true, false);
	}
	
	
	/* DEBUT *** LES METHODES UTILISEE PAR ADMIN listDemandeProduitAccepter */
	@GetMapping(value="/list-demande-produit-accepter")
	public List<Publication> listDemandeProduitAccepter(){
		return publicationRepository.findByCatLikeAndStatusAndSupUserAndAccepterAndRejeterAndCreer("PROD", false, false, true,false, false);
	}
	/* DEBUT *** LES METHODES UTILISEE PAR ADMIN listDemandeService */
	@GetMapping(value="/list-demande-service-accepter")
	public List<Publication> listDemandeServiceAccepter(){
		return publicationRepository.findByCatLikeAndStatusAndSupUserAndAccepterAndRejeterAndCreer("SERV", false, false, true,false, false);
	}
	
	
	
	/* FIN *** LES METHODES UTILISEE PAR UTILISATEUR */
	
	
	
	
	
	
	
	
	
	
	
	/*@GetMapping(value="/listDemandePubNonAccept")
	public List<Publication> listerDemPubNAc(){
		return publicationRepository.findByAccepterIsFalse();
	}*/
	
	@GetMapping(value="/listDemandePubNonCreer")
	public List<Publication> listerDemPubNCreer(){
		return publicationRepository.findByAccepterIsTrueAndCreerIsFalse();
	}
	
	
	
	/*@RequestMapping(value="/listDemandePub")
	public List<Publication> listerDmPub(@RequestBody AppUser proprietaire){
		return publicationRepository.findByProprietaire(proprietaire);
	}*/
	
	/*@PostMapping(value="/listDemandePub")
	public List<Publication> listerDmPub(@RequestBody String proprietaire){
		AppUser use = userRepository.findByUsername(proprietaire);
		return publicationRepository.findByProprietaire(use);
	}*/
	
	
	
	/*@PostMapping(value="/listDemandePubAc")
	public List<Publication> listerDmAPub(@RequestBody String proprietaire){
		AppUser use = userRepository.findByUsername(proprietaire);
		return publicationRepository.findByProprietaireLikeAndAccepterIsTrueAndCreerIsTrue(use);
	}*/
	
	
	
	@PostMapping(value="/listDemandePubNAc")
	public List<Publication> listerDmNAPub(@RequestBody String proprietaire){
		AppUser use = userRepository.findByUsername(proprietaire);
		return publicationRepository.findByProprietaireLikeAndAccepterIsFalseAndCreerIsFalse(use);
	}
	
	@PostMapping(value="/save")
	public Publication savesPublication(@RequestParam("file") MultipartFile file,
			@RequestParam("publication") String publication) throws JsonParseException, JsonMappingException, IOException  {
		
		Publication publicationForm = new ObjectMapper().readValue(publication, Publication.class);
		
		System.out.println(publicationForm);

		String fileName = file.getOriginalFilename();

		//String modifiedFileName = FilenameUtils.getBaseName(fileName) + "_" +System.currentTimeMillis() + "." +FilenameUtils.getExtension(fileName);
		String modifiedFileName = "WE_UPLOADED_THIS"+FilenameUtils.getBaseName(fileName) + "." +FilenameUtils.getExtension(fileName);

		uploadFile.uploadFile(file, modifiedFileName); 

		publicationForm.setPhoto(modifiedFileName);
		System.out.println(publicationForm);
		return publicationRepository.save(publicationForm);
	}
	
	@DeleteMapping(value = "/deletePublication")
	public boolean deletePublication(@PathVariable String id) {
		try {
			publicationRepository.delete(id);
			
			return true;
			
		} catch (Exception e) {
			return false;
		}
		
	}
	
	@PutMapping(value="/publicationAccepte/{id}")
	public Publication editPublicationAc(@PathVariable Long id,@RequestBody Publication e){
		e.setId(id);
		e.setAccepter(true);
		return publicationRepository.save(e);
	}
	
	@PutMapping(value="/publicationCreer/{id}")
	public Publication editPublicationCr(@PathVariable Long id,@RequestBody Publication e){
		e.setId(id);
		e.setCreer(true);
		return publicationRepository.save(e);
	}
	
	@PutMapping(value="/publication-produit-rejeter/{id}")
	public Publication produitRejeter(@PathVariable Long id,@RequestBody Publication e){
		e.setId(id);
		e.setRejeter(true);
		return publicationRepository.save(e);
	}
	@PutMapping(value="/publication-service-rejeter/{id}")
	public Publication qerviceRejeter(@PathVariable Long id,@RequestBody Publication e){
		e.setId(id);
		e.setRejeter(true);
		return publicationRepository.save(e);
	}
	
	
}
