package com.aatout.web;

import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.aatout.dao.MobilisationDao;
import com.aatout.dao.MobilisationDao;
import com.aatout.model.AppUser;
import com.aatout.model.Mobilisation;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@CrossOrigin("**")
@RequestMapping("/mobilisation")
public class MobilisationRestController {
	
	@Autowired
	private MobilisationDao mobilisationDao;
	
	@Autowired
	private UploadFile uploadFile;
	

	@GetMapping(value = "/list")
	public List<Mobilisation> getMobilisations() {
		return mobilisationDao.findByStatusAndSuprUserAndDemande(false, false, false);
	}
	
	@GetMapping(value = "/list-by-id/{id}")
	public Mobilisation getMobilisationsById(@PathVariable long id) {
		return mobilisationDao.findByStatusAndSuprUserAndDemandeAndId(false, false, false, id);
	}
	
	@GetMapping(value = "/list-by-utilisateur/{user}")
	public List<Mobilisation> getMobilisationsByUser(@PathVariable long user) {
		return mobilisationDao.findByStatusAndSuprUserAndDemandeAndProprietaire_Id(false, false, false,user);
	}


	@PostMapping(value = "/save")
	public Mobilisation save(@RequestParam("bien") String bien, @RequestParam("file") MultipartFile file)throws JsonParseException, JsonMappingException, IOException{
		Mobilisation biens = new ObjectMapper().readValue(bien, Mobilisation.class);
		String fileName = file.getOriginalFilename();
		System.out.println(biens);
		
		String modifiedFileName = "WE_UPLOADED_THIS"+FilenameUtils.getBaseName(fileName) + "." +FilenameUtils.getExtension(fileName);

		uploadFile.uploadFile(file, modifiedFileName);

		biens.setPhoto(modifiedFileName);

//		mobilisation.setCreateBy();
		return	mobilisationDao.saveAndFlush(biens);
	}

	@PostMapping(value = "/delete")
	public Mobilisation delete(@RequestBody Mobilisation mobilisation) {

		mobilisation.setStatus(true);
		return	mobilisationDao.saveAndFlush(mobilisation);
	}
	
	@PostMapping(value = "/delete-by-user")
	public Mobilisation deleteByUser(@RequestBody Mobilisation mobilisation) {

		mobilisation.setSuprUser(true);
		return	mobilisationDao.saveAndFlush(mobilisation);
	}
	
}
