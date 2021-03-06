package com.aatout.web;


import java.io.IOException;
import java.util.List;


import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.aatout.dao.ProduitRepository;
import com.aatout.commande.model.DestockageForm;
import com.aatout.dao.BienRepository;
import com.aatout.dao.EchangeRepository;
import com.aatout.dao.ServiceRepository;
import com.aatout.model.AppUser;
import com.aatout.model.Bien;
import com.aatout.model.Echange;
import com.aatout.model.Produit;
import com.aatout.model.Services;
import com.aatout.pagination.dao.ProduitService;
import com.aatout.service.Service;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;






@RestController
public class BienRestController {

	@Autowired
	ProduitService service;

	@Autowired
	com.aatout.pagination.dao.ServiceService serviceService;

	@Autowired
	com.aatout.pagination.dao.EchangeService echangeService;

	@Autowired
	private ProduitRepository produitRepository;

	@Autowired
	private BienRepository bienRepository;

	@Autowired
	private EchangeRepository echangeRepository;

	@Autowired
	private ServiceRepository serviceRepository;

	@Autowired
	private UploadFile uploadFile;

	// les Biens
	/* ===== Début gestion des produits ====*/
	@GetMapping(value="/listProduits")
	public List<Produit> getProduits(){
		return produitRepository.findBySuprAndAccepterAndActive(false, false, true);
	}

	@GetMapping(value="/listes-produits-activer")
	public List<Produit> getProduitsActiver(){
		return produitRepository.findBySuprAndAccepterAndActive(false, true, true);
	}

	/*@GetMapping(value="/list-demande-produit-accepter")
		public List<Produit> getProduitsDemande(){
			return produitRepository.findBySuprAndAccepterAndActive(false, false, false);
		}*/

	@GetMapping(value="/listes-produits-non-activer")
	public List<Produit> getProduitsPasActiver(){
		return produitRepository.findBySuprAndAccepterAndActive(false, true, false);
	}

	@PostMapping("/activer-produit") 
	public Produit activerProduit(@RequestBody Produit produit) {
		produit.setActive(true);
		return produitRepository.saveAndFlush(produit);
	}

	/* ===== Fin gestion des produits ====*/

	@GetMapping(value="/detail-produit/{id}")
	public Produit getProduit(@PathVariable Long id){ 
		return produitRepository.listProduitById(id);
	} 


	/*@GetMapping(value="/stock-actuel-produit/{id}")
		public double getStockActuelProduit(@PathVariable Long id){ 
			Produit p = produitRepository.listProduitById(id);
			return p.getStock();
		}*/

	@PostMapping(value="/destockage")
	public boolean isDestocker(@RequestBody DestockageForm destockageForm){ 
		boolean destocker = false;
		Produit p = produitRepository.listProduitById(destockageForm.getId());

		if(p.getStock() - destockageForm.getQuantite() >= 0) {
			destocker = true;
			p.setStock(p.getStock() - destockageForm.getQuantite());
			produitRepository.saveAndFlush(p);
		}		

		return destocker;

	}



	/*@GetMapping(value="/produits-page")
		@PreAuthorize("permitAll")
		public Page<Produit> chercherBiens(
				@RequestParam(name="mc", defaultValue="")String mc,
				@RequestParam(name="page", defaultValue="0")int page,
				@RequestParam(name="size",defaultValue="48")int size,
				@RequestParam(name="sortBy", defaultValue = "id") String sortBy){
			String sortOrder = "desc";
	    	Sort sort = new Sort(Direction.fromString(sortOrder), sortBy);
			return produitRepository.chercherProduit("%"+mc+"%", new PageRequest(page, size, sort));
		}*/


	@GetMapping(value = "/produits-pageRRR")
	@PreAuthorize("permitAll")
	public Page<Produit> chercherBiens(
			@RequestParam(name = "mc", defaultValue = "") String mc,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "48") int size,
			@RequestParam(name = "sortBy", defaultValue = "id") String sortBy) {
		String sortOrder = "desc";
		Sort sort = new Sort(Direction.fromString(sortOrder), sortBy);
		return produitRepository.chercherProduit("%" + mc + "%", new PageRequest(page, size, sort));
	}


	///ramich//////

	@GetMapping(value = "/produits-page")
	@PreAuthorize("permitAll")
	public Page<Produit> chercherBiensProduit(@RequestParam(name = "mc", defaultValue = "") String mc,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "48") int size,
			@RequestParam(name = "sortBy", defaultValue = "id") String sortBy) {
		String sortOrder = "desc";
		Sort sort = new Sort(Direction.fromString(sortOrder), sortBy);
		return produitRepository.findBySuprIsFalseAndActiveIsTrueAndNomLikeIgnoreCase(new PageRequest(page, size, sort), "%" + mc + "%");
	}


	/*@GetMapping(value="/produits-page")
	    public ResponseEntity<List<Produit>> getAllProduit(
	                        @RequestParam(defaultValue = "0") Integer pageNo, 
	                        @RequestParam(defaultValue = "3") Integer pageSize,
	                        @RequestParam(defaultValue = "id") String sortBy) 
	    {
	        List<Produit> list = service.getAllProduit(pageNo, pageSize, sortBy);

	        return new ResponseEntity<List<Produit>>(list, new HttpHeaders(), HttpStatus.OK); 
	    }*/



	@PostMapping(value = "/postbiens")
	public Bien saveBien(@RequestParam("bien") String bien, @RequestParam("file") MultipartFile file)
			throws JsonParseException, JsonMappingException, IOException {

		BienForm biens = new ObjectMapper().readValue(bien, BienForm.class);
		System.out.println("yyyyyyyyyyyy");
		// System.out.println(file.toString());
		Bien unBien = new Produit();

		if (biens.getType().equals("PROD")) {

			Produit produit = new Produit(biens.getNom(), biens.getDescription(), biens.getPrix(), biens.gettBCCV(),
					biens.getProprietaire(), biens.getLocalisation(), biens.getStock(), biens.getStockAlert());
			//biens.getProprietaire(), biens.getProprietaireUser(), biens.getStock(), biens.getStockAlert());

			String fileName = file.getOriginalFilename();

			// String modifiedFileName =
			// "WE_UPLOADED_THIS"+FilenameUtils.getBaseName(fileName) + "."
			// +FilenameUtils.getExtension(fileName);
			String modifiedFileName = System.currentTimeMillis() +FilenameUtils.getBaseName(fileName) + "." +FilenameUtils.getExtension(fileName);
			uploadFile.uploadFile(file, modifiedFileName);

			produit.setPhoto(modifiedFileName);

			produitRepository.saveAndFlush(produit);

			unBien = produit;

		}

		if (biens.getType().equals("SERV")) {

			Services services = new Services(biens.getNom(), biens.getDescription(), biens.getPrix(), biens.gettBCCV(),
					biens.getProprietaire(), biens.getLocalisation(), biens.getCaution());
			//biens.getProprietaire(), biens.getProprietaireUser(), biens.getCaution());

			String fileName = file.getOriginalFilename();

			// String modifiedFileName =
			// "WE_UPLOADED_THIS"+FilenameUtils.getBaseName(fileName) + "."
			// +FilenameUtils.getExtension(fileName);
			String modifiedFileName = System.currentTimeMillis() +FilenameUtils.getBaseName(fileName) + "." +FilenameUtils.getExtension(fileName);
			uploadFile.uploadFile(file, modifiedFileName);

			services.setPhoto(modifiedFileName);

			serviceRepository.saveAndFlush(services);

			unBien = services;
		}

		return unBien;
	}

	@GetMapping(value = "/services-pageRRR")
	@PreAuthorize("permitAll")
	public Page<Services> chercherServices(@RequestParam(name = "mc", defaultValue = "") String mc,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "48") int size,
			@RequestParam(name = "sortBy", defaultValue = "id") String sortBy) {
		String sortOrder = "desc";
		Sort sort = new Sort(Direction.fromString(sortOrder), sortBy);
		return serviceRepository.chercherService("%" + mc + "%", new PageRequest(page, size, sort));
	}

	/////ramich/////

	@GetMapping(value = "/services-page")
	@PreAuthorize("permitAll")
	public Page<Services> chercherBienServices(@RequestParam(name = "mc", defaultValue = "") String mc,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "48") int size,
			@RequestParam(name = "sortBy", defaultValue = "id") String sortBy) {
		String sortOrder = "desc";
		Sort sort = new Sort(Direction.fromString(sortOrder), sortBy);
		return serviceRepository.findBySuprIsFalseAndActiveIsTrueAndNomLikeIgnoreCase(new PageRequest(page, size, sort), "%" + mc + "%");
	}

	//////


	@GetMapping(value="/listServices")
	public List<Services> getServices(){
		return serviceRepository.findBySuprAndAccepterAndActive(false, true, true);
	}

	@GetMapping(value="/listes-services-activer")
	public List<Services> getServicesActiver(){
		return serviceRepository.findBySuprAndAccepterAndActive(false, true, true);
	}

	@GetMapping(value="/listes-services-non-activer")
	public List<Services> getServicesPasActiver(){
		return serviceRepository.findBySuprAndAccepterAndActive(false, true, false);
	}

	@GetMapping(value="/detail-service/{id}")
	public Services getService(@PathVariable Long id){
		return serviceRepository.listServiceById(id);
	}

	@PostMapping("/activer-service") 
	public Services activerService(@RequestBody Services services) {
		services.setActive(true);
		return serviceRepository.saveAndFlush(services);
	}

	/* ===== Fin gestion des services ====*/

	// =================================== les echanges  ===============================================

	/*@GetMapping(value="/produits-echange")
	    public ResponseEntity<List<Echange>> getAllEchange(
	                        @RequestParam(defaultValue = "0") Integer pageNo, 
	                        @RequestParam(defaultValue = "3") Integer pageSize,
	                        @RequestParam(defaultValue = "id") String sortBy) 
	    {
	        List<Echange> list = echangeService.getAllEchange(pageNo, pageSize, sortBy);

	        return new ResponseEntity<List<Echange>>(list, new HttpHeaders(), HttpStatus.OK); 
	    }*/

	@GetMapping(value="/echanges-page")
	@PreAuthorize("permitAll")
	public Page<Echange> getAllEchanges(
			@RequestParam(name="mc", defaultValue="")String mc,
			@RequestParam(name="page", defaultValue="0")int page,
			@RequestParam(name="size",defaultValue="48")int size,
			@RequestParam(name="sortBy", defaultValue = "id") String sortBy){
		String sortOrder = "desc";
		Sort sort = new Sort(Direction.fromString(sortOrder), sortBy);
		return echangeRepository.chercherEchange("%"+mc+"%", new PageRequest(page, size, sort));
	}


	@GetMapping(value="/listEchangesAaccepter")
	public List<Echange> getEchangesAaccepter(){
		return echangeRepository.listEchangesAaccepter();
	}

	@GetMapping(value="/listEchangesAactiver")
	public List<Echange> getEchangesAactiver(){
		return echangeRepository.listEchangesAactiver();
	}

	@GetMapping(value="/listEchanges")
	public List<Echange> getEchanges(){
		return echangeRepository.listEchanges();
	}

	@GetMapping(value="/detail-echange/{id}")
	public Echange getEchange(@PathVariable Long id){
		return echangeRepository.listEchangeById(id);
	}
	// ***************** Demandes d'échanges par propriétaire (les à accepter) ******************
	@GetMapping(value="/listEchangeAAccepterParProprietaire/{proprietaires}")
	public Page<Echange> getEchangeByIProprietaire(
			@RequestParam(name="mc", defaultValue="")String mc,
			@RequestParam(name="page", defaultValue="0")int page,
			@RequestParam(name="size",defaultValue="48")int size,
			@RequestParam(name="sortBy", defaultValue = "id") String sortBy,
			@PathVariable Long proprietaires){
		String sortOrder = "desc";
		Sort sort = new Sort(Direction.fromString(sortOrder), sortBy);
		return echangeRepository.findBySuprIsFalseAndActiveIsFalseAndAccepterIsFalseAndProprietaires_Id( new PageRequest(page, size, sort), proprietaires);
	} 


	// ***************** Demandes d'échanges par propriétaire (les à activer) ******************
	/*@GetMapping(value="/listEchangeAActiverParProprietaire/{proprietaires}")
		public List<Echange> getEchangeAccepterByIProprietaire(@PathVariable Long proprietaires){
			return echangeRepository.findBySuprIsFalseAndActiveIsFalseAndAccepterIsTrueAndProprietaires_Id(proprietaires);
		}*/


	@GetMapping(value="/listEchangeAActiverParProprietaire/{proprietaires}")
	public Page<Echange> getEchangeAccepterByIProprietaire(
			@RequestParam(name="mc", defaultValue="")String mc,
			@RequestParam(name="page", defaultValue="0")int page,
			@RequestParam(name="size",defaultValue="48")int size,
			@RequestParam(name="sortBy", defaultValue = "id") String sortBy,
			@PathVariable Long proprietaires){
		String sortOrder = "desc";
		Sort sort = new Sort(Direction.fromString(sortOrder), sortBy);
		return echangeRepository.findBySuprIsFalseAndActiveIsFalseAndAccepterIsTrueAndProprietaires_Id( new PageRequest(page, size, sort), proprietaires);
	}


	// ***************** Demandes d'échanges par propriétaire (les dejà accepter et activer ) ******************
	/*@GetMapping(value="/listEchangeParProprietaire/{proprietaires}")
		public List<Echange> getEchangeActiverByIProprietaire(@PathVariable Long proprietaires){
			return echangeRepository.findBySuprIsFalseAndActiveIsTrueAndAccepterIsTrueAndProprietaires_Id(proprietaires);
		}*/


	@GetMapping(value="/listEchangeParProprietaire/{proprietaires}")
	public Page<Echange> getEchangeActiverByIProprietaire(
			@RequestParam(name="page", defaultValue="0")int page,
			@RequestParam(name="size",defaultValue="48")int size,
			@RequestParam(name="sortBy", defaultValue = "id") String sortBy,
			@PathVariable Long proprietaires){
		String sortOrder = "desc";
		Sort sort = new Sort(Direction.fromString(sortOrder), sortBy);
		return echangeRepository.findBySuprIsFalseAndActiveIsTrueAndAccepterIsTrueAndProprietaires_Id( new PageRequest(page, size, sort), proprietaires);
	}

	//******************** accepter echange *********************** 
	@PostMapping(value="/accepteEchange")
	public Echange accepteEchange(@RequestBody Echange echange) {
		long nbrEchange = echangeRepository.countBySuprIsFalseAndActiveIsTrueAndAccepterIsFalseAndProprietaires_Id(echange.getProprietaires().getId());
		System.out.println(nbrEchange);
		if (nbrEchange == 10 || nbrEchange > 10) {
			return null;				
		} else {
			Echange echang = echangeRepository.findOne(echange.getId());
			try {
				echang.setAccepter(true);
				echangeRepository.saveAndFlush(echang);
			} catch (Exception e) {
				e.printStackTrace();
			}

			return echange;
		}


	}
	// ***************** activer echange *****************
	@PostMapping(value="/activeEchange")
	public Echange activeEchange(@RequestBody Echange echange) {
		//long nbrEchange = echangeRepository.countBySuprIsFalseAndActiveIsTrueAndAccepterIsTrueAndProprietaires_Id(echange.getProprietaires().getId());
		List<Echange> nbrEchange = echangeRepository.findBySuprIsFalseAndActiveIsTrueAndAccepterIsTrueAndProprietaires_Id(echange.getProprietaires().getId());
		System.out.println(nbrEchange);
		if (nbrEchange.size() == 10 || nbrEchange.size() > 10) {
			return null;				
		} else {
			Echange echang = echangeRepository.findOne(echange.getId());

			try {
				echang.setActive(true);
				echangeRepository.save(echang);
			} catch (Exception e) {
				e.printStackTrace();
			}

			return echange;
		}
	}

	// ***************************** supression des echanges **********************

	@DeleteMapping("/deleteEchange/{id}") 
	public boolean deleteEchange(@PathVariable Long  id) {
		try {
			Echange echange = echangeRepository.findOne(id);
			echange.setSupr(true);
			echangeRepository.save(echange);
			return true;
		} catch (Exception e) {
			return false;
		}


	}

	/*@PostMapping(value="/postEchange")
		public Echange saveBien(@RequestBody Echange echanges)throws IOException{

			Echange echanges = new ObjectMapper().readValue(echange, Echange.class);

			String fileName = file.getOriginalFilename();

			String modifiedFileName = FilenameUtils.getBaseName(fileName) + "_" +System.currentTimeMillis() + "." +FilenameUtils.getExtension(fileName);

			uploadFile.uploadFile(file, modifiedFileName);

			echanges.setPhoto(modifiedFileName);
			return echangeRepository.save(echanges);
		}*/


	@PostMapping(value="/postEchange")
	public Echange saveEchange(@RequestParam("echange") String echange, @RequestParam("file") MultipartFile file)throws JsonParseException, JsonMappingException, IOException{
		Echange echanges = new ObjectMapper().readValue(echange, Echange.class);

		//long nbrEchange = echangeRepository.countBySuprIsFalseAndActiveIsTrueAndAccepterIsTrueAndProprietaires_Id(echanges.getProprietaires().getId());
		List<Echange> nbrEchange = echangeRepository.findBySuprIsFalseAndActiveIsTrueAndAccepterIsTrueAndProprietaires_Id(echanges.getProprietaires().getId());
		System.out.println(nbrEchange);
		if (nbrEchange.size() == 10 || nbrEchange.size() > 10) {
			return null;				
		} else {

			String fileName = file.getOriginalFilename();

			String modifiedFileName = System.currentTimeMillis() +FilenameUtils.getBaseName(fileName) + "." +FilenameUtils.getExtension(fileName);

			uploadFile.uploadFile(file, modifiedFileName);

			echanges.setPhoto(modifiedFileName);
			return echangeRepository.saveAndFlush(echanges);
		}
	}

	@PatchMapping(value="/biens/{id}")
	public Bien saveBien(@PathVariable Long id,@RequestBody Bien c){
		c.setId(id);
		return bienRepository.save(c);
	}

	// modification des

	@PatchMapping(value="/updateBien/{id}")
	public Bien editBien(@PathVariable Long id,@RequestBody Bien bien){

		Bien biens = bienRepository.listBienById(id);

		bien.setId(bien.getId());


		return bienRepository.saveAndFlush(biens);

	}


	@PatchMapping(value = "/bienImg/{id}")
	public Bien editBienImg(@PathVariable Long id, @RequestParam("file") MultipartFile file,
			@RequestParam("bien") String bien) throws JsonParseException, JsonMappingException, IOException {
		// Bien biens = new ObjectMapper().readValue(bien, Bien.class);

		// biens.setId(id);

		/*
		 * String filename=file.getOriginalFilename(); String
		 * modifiedFileName=FilenameUtils.getBaseName(filename)+"_"+System.
		 * currentTimeMillis()+"."+FilenameUtils.getExtension(filename);
		 * 
		 * uploadFile.uploadFile(file,modifiedFileName);
		 * 
		 * 
		 * biens.setPhoto(modifiedFileName); return bienRepository.save(biens);
		 */

		BienForm biens = new ObjectMapper().readValue(bien, BienForm.class);
		// biens.setId(id);
		System.out.println("yyyyyyyyyyyy");
		// System.out.println(file.toString());
		Bien unBien = new Produit();

		if (biens.getType().equals("PROD")) {

			Produit produit = new Produit(biens.getNom(), biens.getDescription(), biens.getPrix(), biens.gettBCCV(),
					biens.getProprietaire(), biens.getLocalisation(), biens.getStock(), biens.getStockAlert());
			//biens.getProprietaire(), biens.getProprietaireUser(), biens.getStock(), biens.getStockAlert());

			produit.setId(id);
			produit.setActive(true);
			produit.setAccepter(false);
			String fileName = file.getOriginalFilename();

			// String modifiedFileName =
			// "WE_UPLOADED_THIS"+FilenameUtils.getBaseName(fileName) + "."
			// +FilenameUtils.getExtension(fileName);			
			String modifiedFileName = System.currentTimeMillis() +FilenameUtils.getBaseName(fileName) + "." +FilenameUtils.getExtension(fileName);
			uploadFile.uploadFile(file, modifiedFileName);

			produit.setPhoto(modifiedFileName);

			produitRepository.saveAndFlush(produit);

			unBien = produit;

		}

		if (biens.getType().equals("SERV")) {

			Services services = new Services(biens.getNom(), biens.getDescription(), biens.getPrix(), biens.gettBCCV(),
					biens.getProprietaire(), biens.getLocalisation(), biens.getCaution());
			//biens.getProprietaire(),biens.getProprietaireUser(), biens.getCaution());

			services.setId(id);
			services.setActive(true);
			services.setAccepter(false);

			String fileName = file.getOriginalFilename();

			// String modifiedFileName =
			// "WE_UPLOADED_THIS"+FilenameUtils.getBaseName(fileName) + "."
			// +FilenameUtils.getExtension(fileName);			
			String modifiedFileName = System.currentTimeMillis() +FilenameUtils.getBaseName(fileName) + "." +FilenameUtils.getExtension(fileName);
			uploadFile.uploadFile(file, modifiedFileName);

			services.setPhoto(modifiedFileName);

			serviceRepository.saveAndFlush(services);

			unBien = services;
		}

		return unBien;

	}



	@DeleteMapping("/deleteBien/{id}") 
	public boolean deleteBien(@PathVariable Long  id) {
		try {
			Bien bien = bienRepository.listBienById(id);
			bien.setSupr(true);
			bienRepository.save(bien);
			return true;
		} catch (Exception e) {
			return false;
		}


	}

	// ***************** Demandes de mise en vente de produit par propriétaire (les à accepter) ******************
	@GetMapping(value="/listProduitAAccepterParProprietaire/{proprietaires}")
	public List<Produit> getProduitByIdProprietaire(@PathVariable Long proprietaires){
		return produitRepository.findBySuprIsFalseAndActiveIsFalseAndAccepterIsFalseAndProprietaire_Id(proprietaires);
	}

	// ***************** Demandes de mise en vente de produit par propriétaire (les à activer) ******************
	@GetMapping(value="/listProduitAActiverParProprietaire/{proprietaires}")
	public List<Produit> getProduitAccepterByIdProprietaire(@PathVariable Long proprietaires){
		return produitRepository.findBySuprIsFalseAndActiveIsFalseAndAccepterIsTrueAndProprietaire_Id(proprietaires);
	}

	// ***************** Demandes de mise en vente de produit par propriétaire (les dejà accepter et activer ) ******************
	@GetMapping(value="/listProduitParProprietaire/{proprietaires}")
	public List<Produit> getProduitActiverByIdProprietaire(@PathVariable Long proprietaires){
		return produitRepository.findBySuprIsFalseAndActiveIsTrueAndAccepterIsTrueAndProprietaire_Id(proprietaires);
	}


	// ***************** Demandes de mise en vente de Service par propriétaire (les à accepter) ******************
	@GetMapping(value="/listServiceAAccepterParProprietaire/{proprietaires}")
	public List<Services> getServiceByIProprietaire(@PathVariable Long proprietaires){
		return serviceRepository.findBySuprIsFalseAndActiveIsFalseAndAccepterIsFalseAndProprietaire_Id(proprietaires);
	}

	// ***************** Demandes de mise en vente de produit par propriétaire (les à activer) ******************
	@GetMapping(value="/listServiceAActiverParProprietaire/{proprietaires}")
	public List<Services> getServicetAccepterByIProprietaire(@PathVariable Long proprietaires){
		return serviceRepository.findBySuprIsFalseAndActiveIsFalseAndAccepterIsTrueAndProprietaire_Id(proprietaires);
	}

	// ***************** Demandes de mise en vente de produit par propriétaire (les dejà accepter et activer ) ******************
	@GetMapping(value="/listServiceParProprietaire/{proprietaires}")
	public List<Services> getServiceActiverByIProprietaire(@PathVariable Long proprietaires){
		return serviceRepository.findBySuprIsFalseAndActiveIsTrueAndAccepterIsTrueAndProprietaire_Id(proprietaires);
	}



	//////////////////////////// Ramich/////////////////////////////////

	// ***************** Liste des produits par propriétaire user
	// *****************//

	/*@GetMapping(value = "/list-produits-by-proprietaire-user/{proprietaireUser}")
	public List<Produit> getProduitByIdProprietaireUser(@PathVariable Long proprietaireUser) {
		return produitRepository.findBySuprIsFalseAndActiveIsTrueAndProprietaireUser_Id(proprietaireUser);
	}*/


	/*@GetMapping(value="/list-produits-by-user/{proprietaires}")
	public Page<Produit> listerProduitPageByUser(
			@RequestParam(name="mc", defaultValue="")String mc,
			@RequestParam(name="page", defaultValue="0")int page,
			@RequestParam(name="size",defaultValue="48")int size,
			@RequestParam(name="sortBy", defaultValue = "id") String sortBy,
			@PathVariable Long proprietaires){
		String sortOrder = "desc";
    	Sort sort = new Sort(Direction.fromString(sortOrder), sortBy);
		return produitRepository.findBySuprIsFalseAndActiveIsTrueAndProprietaireUser_IdAndNomLikeIgnoreCase( new PageRequest(page, size, sort),  proprietaires, "%"+mc+"%");
	}*/


	@GetMapping(value="/list-produits-by-user/{proprietaires}")
	public Page<Produit> listerProduitPageByUser(
			@RequestParam(name="mc", defaultValue="")String mc,
			@RequestParam(name="page", defaultValue="0")int page,
			@RequestParam(name="size",defaultValue="48")int size,
			@RequestParam(name="sortBy", defaultValue = "id") String sortBy,
			@PathVariable Long proprietaires){
		String sortOrder = "desc";
		Sort sort = new Sort(Direction.fromString(sortOrder), sortBy);
		return produitRepository.listProductByUser( new PageRequest(page, size, sort), "%"+mc+"%", proprietaires);
	}




	// ***************** Liste des services par propriétaire user// *****************//


	/*@GetMapping(value = "/list-services-by-proprietaire-user/{proprietaireUser}")
	public List<Services> getServiceByIdProprietaireUser(@PathVariable Long proprietaireUser) {
		return serviceRepository.findBySuprIsFalseAndActiveIsTrueAndProprietaireUser_Id(proprietaireUser);
	}*/




	/*@GetMapping(value="/list-Services-by-user/{proprietaires}")
	public Page<Services> listerServicesPageByUser(
			@RequestParam(name="mc", defaultValue="")String mc,
			@RequestParam(name="page", defaultValue="0")int page,
			@RequestParam(name="size",defaultValue="48")int size,
			@RequestParam(name="sortBy", defaultValue = "id") String sortBy,
			@PathVariable Long proprietaires){
		String sortOrder = "desc";
    	Sort sort = new Sort(Direction.fromString(sortOrder), sortBy);
		return serviceRepository.findBySuprIsFalseAndActiveIsTrueAndProprietaireUser_IdAndNomLikeIgnoreCase( new PageRequest(page, size, sort),  proprietaires, "%"+mc+"%");
	}*/


	@GetMapping(value="/list-Services-by-user/{proprietaires}")
	public Page<Services> listerServicesPageByUser(
			@RequestParam(name="mc", defaultValue="")String mc,
			@RequestParam(name="page", defaultValue="0")int page,
			@RequestParam(name="size",defaultValue="48")int size,
			@RequestParam(name="sortBy", defaultValue = "id") String sortBy,
			@PathVariable Long proprietaires){
		String sortOrder = "desc";
		Sort sort = new Sort(Direction.fromString(sortOrder), sortBy);
		return serviceRepository.listServiceByUser( new PageRequest(page, size, sort), "%"+mc+"%",  proprietaires);
	}



	///////////////////////////// ramich/////////////////////////////////



	@PutMapping(value="/produitE/{id}")
	public Bien editProduit(@PathVariable Long id,@RequestBody Produit c){
		c.setId(id);
		c.setActive(true);
		return bienRepository.saveAndFlush(c);
	}

	@PutMapping(value="/serviceE/{id}")
	public Bien editService(@PathVariable Long id,@RequestBody Services c){
		c.setId(id);
		c.setActive(true);
		return bienRepository.saveAndFlush(c);
	}	


	@DeleteMapping(value="/biens/{id}")
	public boolean supprimerBien(@PathVariable Long id){

		try {
			Bien b = bienRepository.listBienById(id);
			b.setSupr(true);
			bienRepository.save(b);
			//bienRepository.delete(id);
			return true;
		} catch (Exception e) {
			return false;
		}

	}


}
