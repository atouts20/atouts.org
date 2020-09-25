package com.aatout.web;

import com.aatout.dao.BloquerRepository;
import com.aatout.dao.CompteRepository;
import com.aatout.dao.MetierDao;
import com.aatout.dao.PaysDao;
import com.aatout.dao.RoleRepository;
import com.aatout.dao.SousCompteDao;
import com.aatout.dao.UserRepository;
import com.aatout.enums.TypeName;
import com.aatout.model.AppRole;
import com.aatout.model.AppUser;
import com.aatout.model.Bloquer;
import com.aatout.model.Compte;
import com.aatout.model.CompteMonnaie;
import com.aatout.model.CompteValeur;
import com.aatout.model.Metier;
import com.aatout.model.Pays;
import com.aatout.model.SousCompte;
import com.aatout.parametre.model.ActiveUserStore;
import com.aatout.random.RandomCodeService;
import com.aatout.security.SecurityConstants;
import com.aatout.service.AccountService;
import com.aatout.service.AccountServiceImpl;
import com.aatout.service.EmailService;
import com.aatout.service.MesConstants;
import com.aatout.payload.LoginRequest;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
@RestController
public class AccountRestController {
	
	@Autowired
	private AccountService accountService;
	@Autowired
	private AccountServiceImpl accountServiceImpl;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private EmailService emailService;
	@Autowired
	private RandomCodeService randomService; 
	
	@Autowired
    ActiveUserStore activeUserStore;
	
	@Autowired
    AuthenticationManager authenticationManager;

	@Autowired
	private UploadFile uploadFile;
	@Autowired
	private SousCompteDao sousCompteDao;
	@Autowired
	private PaysDao paysDao;
	
	@Autowired
	private MetierDao metierDao;
	
	@Autowired
	CompteRepository compteRepository;	

	@Autowired
	BloquerRepository bloquerRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	
	
	@GetMapping(value="/pays")
	public List<Pays> getPays(){
		return paysDao.findAll();
	} 
	
	@GetMapping(value="/metier")
	public List<Metier> getMetier(){
		return metierDao.findAll();
	} 
	
	@GetMapping(value="/user-names")
	public List<String> getUserNames(){
		return userRepository.findByUsername();
	} 
	
	@RequestMapping(value="/chercherUserTest",method=RequestMethod.GET)
	public List<AppUser> chercherUserTest(@RequestParam (name="username",defaultValue="") String username){
		return userRepository.findBySupprimeIsFalseAndEnabledIsTrueAndNomLike("%"+username+"%");
	}

	@RequestMapping(value="/chercherUser",method=RequestMethod.GET)
	public AppUser chercherUser(@RequestParam(name="username",defaultValue="")String username){
		return accountService.findUserByUsername(username);
	}

	@GetMapping("/listUtilisateurs")
	public List<AppUser> getAppUser(){
		return userRepository.findBySupprimeIsFalseAndStatusIsFalseAndEnabledIsTrueAndActiveIsTrueAndAccountNonLockedIsTrueAndAdminstratifIsTrue();
	}
	
	@GetMapping("/list-utilisateurs")
	public List<AppUser> listAppUsers(){
		return userRepository.findBySupprimeIsFalseAndStatusIsFalseAndEnabledIsTrueAndActiveIsTrueAndAccountNonLockedIsTrueAndAdminstratifIsTrue();
	}
	
	@GetMapping("/listUtilisateurs-bloques")
	public List<AppUser> getLockedAppUser(){
		return userRepository.findBySupprimeIsFalseAndStatusIsFalseAndEnabledIsTrueAndActiveIsTrueAndAccountNonLockedIsFalseAndAdminstratifIsTrue();
	}
	
	
	@GetMapping("/listMembres")
	public List<AppUser> getMembre(){
		return userRepository.findBySupprimeIsFalseAndStatusIsFalseAndEnabledIsTrueAndActiveIsTrueAndAccountNonLockedIsTrueAndAdminstratifIsFalse();
	}
	
	@GetMapping("/listMembres-bloques")
	public List<AppUser> getLockedMembre(){
		return userRepository.findBySupprimeIsFalseAndStatusIsFalseAndEnabledIsTrueAndActiveIsTrueAndAccountNonLockedIsFalseAndAdminstratifIsFalse();
	}
	
	
	
	@GetMapping("/listUtilisateurs-non-actives")
	public List<AppUser> getAppUserNonactives(){
		return userRepository.findBySupprimeIsFalseAndEnabledIsTrueAndActiveIsFalse();
	}
	
	
	
	
	
	@PatchMapping("/utilisateur-edit/{id}")
	public AppUser editUser(@PathVariable Long id,@RequestBody AppUser c){
		
		AppUser appUser = userRepository.findById(id);
			c.setId(appUser.getId());
			c.setPassword(appUser.getPassword());
			c.setPhoto(appUser.getPhoto());
			c.setScane(appUser.getScane());
			c.setSignature(appUser.getSignature());
			c.setCreatedAt(appUser.getCreatedAt());
			c.setEnabled(true);
			c.setActive(true);
			c.setAdminstratif(appUser.isAdminstratif());

			
		return userRepository.saveAndFlush(c);
		
	}
	
	@PatchMapping("/utilisateur-edit-img/{id}")
	public AppUser editUserImg(@PathVariable Long id,@RequestParam("file") MultipartFile file,@RequestParam("file1") MultipartFile file1,@RequestParam("file2") MultipartFile file2, @RequestParam("user") String user)throws JsonParseException, JsonMappingException, IOException{
		AppUser users = new ObjectMapper().readValue(user, AppUser.class);
		AppUser appUser = userRepository.findById(id);
		users.setId(appUser.getId());
		users.setPassword(appUser.getPassword());
		users.setCreatedAt(appUser.getCreatedAt());
		users.setEnabled(true);
		users.setActive(true);
		users.setAdminstratif(appUser.isAdminstratif());
		

		String fileName = file.getOriginalFilename();
		

		String modifiedFileName = FilenameUtils.getBaseName(fileName) + "_" +System.currentTimeMillis() + "." +FilenameUtils.getExtension(fileName);

		String fileName1 = file1.getOriginalFilename();

		String modifiedFileName1 = FilenameUtils.getBaseName(fileName1) + "_" +System.currentTimeMillis() + "." +FilenameUtils.getExtension(fileName1);
		
		String fileName2 = file2.getOriginalFilename();

		String modifiedFileName2 = FilenameUtils.getBaseName(fileName2) + "_" +System.currentTimeMillis() + "." +FilenameUtils.getExtension(fileName2);

		uploadFile.uploadFile(file, modifiedFileName);

		uploadFile.uploadFile(file1, modifiedFileName1);
		
		uploadFile.uploadFile(file2, modifiedFileName2);

		users.setPhoto(modifiedFileName);

		users.setScane(modifiedFileName1);
		
		users.setSignature(modifiedFileName2);
		
		
		return userRepository.save(users);
	}
	
	

	@PostMapping("/register-final/{id}")
	@Transactional
	public AppUser registerFinal( @PathVariable Long id, @RequestParam("file") MultipartFile file, @RequestParam("file1") MultipartFile file1, @RequestParam("file2") MultipartFile file2, 
			@RequestParam("user") String user) throws JsonParseException, JsonMappingException, IOException {

		AppUser userForm = new ObjectMapper().readValue(user, AppUser.class);
		System.out.println(userForm);
		AppUser appUser = userRepository.findById(id);
		userForm.setId(appUser.getId());
		userForm.setPassword(appUser.getPassword());
		userForm.setActive(true);
		
		

		// Générer un jeton de chaîne aléatoire de 36 caractères pour le lien de confirmation
		
		//userForm.setConfirmationToken(UUID.randomUUID().toString());

		String fileName = file.getOriginalFilename();

		String modifiedFileName = FilenameUtils.getBaseName(fileName) + "_" +System.currentTimeMillis() + "." +FilenameUtils.getExtension(fileName);

		String fileName1 = file1.getOriginalFilename();

		String modifiedFileName1 = FilenameUtils.getBaseName(fileName1) + "_" +System.currentTimeMillis() + "." +FilenameUtils.getExtension(fileName1);
		
		String fileName2 = file2.getOriginalFilename();

		String modifiedFileName2 = FilenameUtils.getBaseName(fileName2) + "_" +System.currentTimeMillis() + "." +FilenameUtils.getExtension(fileName2);

		uploadFile.uploadFile(file, modifiedFileName);

		uploadFile.uploadFile(file1, modifiedFileName1);
		
		uploadFile.uploadFile(file2, modifiedFileName2);

		userForm.setPhoto(modifiedFileName);

		userForm.setScane(modifiedFileName1);
		
		userForm.setSignature(modifiedFileName2);
		
		
		
		
		AppUser test = userRepository.save(userForm);
		//AppUser test = userRepository.saveAndFlush(userForm);
		System.out.println("Enregistrement avec Success !!!!");
		
		if(test != null) {
			String codePays = userForm.getNationalite().getSub_Type();
			
			String codePays1 = userForm.getNationalite().getSub_Type() + userForm.getNationalite().getSommo_Name() ;
			

			Long num1 = compteRepository.getMax(codePays1);
			System.out.println(num1);
			if (num1 == null) {
				num1 = 0L;
			}
			Compte unCompte =  compteRepository.findById(num1); 
			
			
			long dernierNumCompte; 
			if(unCompte == null   ) {
				dernierNumCompte = 100000000000L;
			} else {
				//unCompte = compteRepository.findTopByIdAndCodePays(codePays);
				dernierNumCompte = unCompte.getMonNum() + 9;
			}	
			
			
			
			
			//int codePays = userForm.getNationalite().getCodePays();
			
			Compte cptmn = new CompteMonnaie();
			cptmn.setTrans(true);
			
			cptmn.setCodePays(codePays1);
			cptmn.setAppUserCompte(userForm);
			Long numcptmn = dernierNumCompte + 1;
			cptmn.setMonNum(numcptmn);
			cptmn.setNumCompte("01"+codePays+numcptmn);
			cptmn.setActive(true);
			compteRepository.save(cptmn);
			
			Compte cptvl = new CompteValeur();
			cptvl.setTrans(true);
			cptvl.setCodePays(codePays1);
			cptvl.setAppUserCompte(userForm);
			Long numcptvl = dernierNumCompte + 2;
			cptvl.setMonNum(numcptvl);
			cptvl.setPin(randomService.pinString(8));
			cptvl.setNumCompte("00"+codePays+numcptvl);
			cptvl.setActive(true);
			compteRepository.save(cptvl);
			
			// les sous comptes de user
			SousCompte sousCompte1 = new SousCompte();
			Long numsousCompte1 = dernierNumCompte + 3;
			sousCompte1.setNumCompte("90"+codePays+numsousCompte1);
			sousCompte1.setAppUserSousCompte(userForm);
			sousCompte1.setType(TypeName.COMPTE_DE_PROVISIONS);
			sousCompte1.setPin(randomService.pin());
			sousCompte1.setActive(true);
			sousCompteDao.save(sousCompte1);
			
			SousCompte sousCompte2 = new SousCompte();
			Long numsousCompte2 = dernierNumCompte + 4;
			sousCompte2.setNumCompte("80"+codePays+numsousCompte2);
			sousCompte2.setAppUserSousCompte(userForm);
			sousCompte2.setType(TypeName.COMPTE_TRESOR);
			sousCompte2.setPin(randomService.pin());
			sousCompte2.setActive(true);
			sousCompteDao.save(sousCompte2);
			
			SousCompte sousCompte6 = new SousCompte();
			Long numsousCompte6 = dernierNumCompte + 5;
			sousCompte6.setNumCompte("70"+codePays+numsousCompte6);
			sousCompte6.setAppUserSousCompte(userForm);
			sousCompte6.setType(TypeName.COMPTE_ECHANGE_A_TERME);
			sousCompte6.setPin(randomService.pin());
			sousCompte6.setActive(true);
			sousCompteDao.save(sousCompte6);
			
			SousCompte sousCompte4 = new SousCompte();
			Long numsousCompte4 = dernierNumCompte + 6;
			sousCompte4.setNumCompte("60"+codePays+numsousCompte4);
			sousCompte4.setAppUserSousCompte(userForm);
			sousCompte4.setType(TypeName.AVANCE_SUR_ED);
			sousCompte4.setPin(randomService.pin());
			sousCompte4.setActive(true);
			sousCompteDao.save(sousCompte4);
			
			
			SousCompte sousCompte7 = new SousCompte();
			Long numsousCompte7 = dernierNumCompte + 7;
			sousCompte7.setNumCompte("50"+codePays+numsousCompte7);
			sousCompte7.setAppUserSousCompte(userForm);
			sousCompte7.setType(TypeName.COMPTE_D_IMPEYER_ET);
			sousCompte7.setPin(randomService.pin());
			sousCompte7.setActive(true);
			sousCompteDao.save(sousCompte7);
			
			SousCompte sousCompte8 = new SousCompte();
			Long numsousCompte8 = dernierNumCompte + 8;
			sousCompte8.setNumCompte("40"+codePays+numsousCompte8);
			sousCompte8.setAppUserSousCompte(userForm);
			sousCompte8.setType(TypeName.COMPTE_D_IMPEYER_AVANCE_ED);
			sousCompte8.setPin(randomService.pin());
			sousCompte8.setActive(true);
			sousCompteDao.save(sousCompte8);
			
			
			SousCompte sousCompte9 = new SousCompte();
			Long numsousCompte9 = dernierNumCompte + 9;
			sousCompte9.setNumCompte("89"+codePays+numsousCompte9);
			sousCompte9.setAppUserSousCompte(userForm);
			sousCompte9.setType(TypeName.AUTRE_TRESOR);
			sousCompte9.setPin(randomService.pin());
			sousCompte9.setActive(true);
			sousCompteDao.save(sousCompte9);
			
			
			SousCompte sousCompte5 = new SousCompte();
			Long numsousCompte5 = dernierNumCompte + 10;
			sousCompte5.setNumCompte("79"+codePays+numsousCompte5);
			sousCompte5.setAppUserSousCompte(userForm);
			sousCompte5.setType(TypeName.AUTRE_COMPTE_ET);
			sousCompte5.setPin(randomService.pin());
			sousCompte5.setActive(true);
			sousCompteDao.save(sousCompte5);

			
			
			
			SousCompte sousCompte3 = new SousCompte();
			Long numsousCompte3 = dernierNumCompte + 11;
			sousCompte3.setNumCompte("30"+codePays+numsousCompte3);
			sousCompte3.setAppUserSousCompte(userForm);
			sousCompte3.setType(TypeName.AUTRE_COMPTE_D_IMPEYER);
			sousCompte3.setPin(randomService.pin());
			sousCompte3.setActive(true);
			sousCompteDao.save(sousCompte3);
				
			
			
			
			 
			String nom = userForm.getPrenom() +" "+userForm.getNom();
			String msg = "Votre numero de compte atouts est :\n"+ cptvl.getNumCompte()+". \n Votre code pin est :\n"+ cptvl.getPin()+". \n Connectez vous sur https://www.atouts.org";
			emailService.sendMailHtml(userForm.getEmail(), "ADHESION REUSSIE", msg, nom);
			
			
			  
			/*String appUrl = request.getScheme() +"://" + request.getServerName() + ":" +request.getServerPort();

			SimpleMailMessage registrationEmail = new SimpleMailMessage();

			registrationEmail.setTo(userForm.getEmail());

			registrationEmail.setSubject("Registration Confirmation");

			registrationEmail.setText("Pour confirmer votre adresse e-mail, veuillez cliquer sur le lien aatout ci-dessous:\n"
					+ appUrl + "/confirm?token=" + userForm.getConfirmationToken());
			// Adresse email de aatout
			registrationEmail.setFrom("aamadoudiko@gmail.com");



			emailService.sendEmail(registrationEmail);
			System.out.println("Un e-mail de confirmation a été envoyé à :" +userForm.getEmail() );*/
			

			
		}
		
		return userForm;

	}
	
	
	
	@PostMapping("/register-membre")
	@Transactional
	public AppUser registerMembre( @RequestParam("file") MultipartFile file, @RequestParam("file1") MultipartFile file1, @RequestParam("file2") MultipartFile file2, 
			@RequestParam("user") String user) throws JsonParseException, JsonMappingException, IOException {

		AppUser userForm = new ObjectMapper().readValue(user, AppUser.class);
		System.out.println(userForm);		

		userForm.setEnabled(true);
		userForm.setActive(true);

		//userForm.setConfirmationToken(UUID.randomUUID().toString());

		String fileName = file.getOriginalFilename();

		String modifiedFileName = FilenameUtils.getBaseName(fileName) + "_" +System.currentTimeMillis() + "." +FilenameUtils.getExtension(fileName);

		String fileName1 = file1.getOriginalFilename();

		String modifiedFileName1 = FilenameUtils.getBaseName(fileName1) + "_" +System.currentTimeMillis() + "." +FilenameUtils.getExtension(fileName1);
		
		String fileName2 = file2.getOriginalFilename();

		String modifiedFileName2 = FilenameUtils.getBaseName(fileName2) + "_" +System.currentTimeMillis() + "." +FilenameUtils.getExtension(fileName2);


		
		
		uploadFile.uploadFile(file, modifiedFileName);

		uploadFile.uploadFile(file1, modifiedFileName1);
		
		uploadFile.uploadFile(file2, modifiedFileName2);
		

		userForm.setPhoto(modifiedFileName);

		userForm.setScane(modifiedFileName1);
		
		userForm.setSignature(modifiedFileName2);
		

		
		
		
		//AppRole role = roleRepository.findByRoleName("USER");
		//userForm.getRoles().add(role);
		//System.out.println("hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh !!!!");
	    accountService.saveUser(userForm);
	    System.out.println("hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh !!!!");
		//userRepository.save(userForm);
		
	    //if(userForm.getRoles().isEmpty()) {
			accountService.addRoleToUse(userForm.getUsername(), "USER");
		//}	
		
		userRepository.saveAndFlush(userForm);
		
		System.out.println("Enregistrement avec Success !!!!");
		
		
		String codePays = userForm.getNationalite().getSub_Type();
		
		String codePays1 = userForm.getNationalite().getSub_Type() + userForm.getNationalite().getSommo_Name() ;
		//System.out.println(codePays);
		//System.out.println(codePays);
		//Long idMax = compteRepository.getMax();
		//System.out.println(idMax);
		//String vv = 004;

		Long num1 = compteRepository.getMax(codePays1);
		System.out.println(num1);
		if (num1 == null) {
			num1 = 0L;
		}
		Compte unCompte =  compteRepository.findById(num1); 
		System.out.println(unCompte);
		System.out.println("==================== debut");
		System.out.println(unCompte);
		System.out.println("==================== fin");
		
		long dernierNumCompte; 
		if(unCompte == null   ) {
			dernierNumCompte = 100000000000L;
		} else {
			//unCompte = compteRepository.findTopByIdAndCodePays(codePays);
			dernierNumCompte = unCompte.getMonNum() + 9;
		}	
		
		
		
		
		//int codePays = userForm.getNationalite().getCodePays();
		
		Compte cptmn = new CompteMonnaie();
		cptmn.setTrans(true);
		
		cptmn.setCodePays(codePays1);
		cptmn.setAppUserCompte(userForm);
		Long numcptmn = dernierNumCompte + 1;
		cptmn.setMonNum(numcptmn);
		cptmn.setNumCompte("01"+codePays+numcptmn);
		cptmn.setActive(true);
		compteRepository.save(cptmn);
		
		Compte cptvl = new CompteValeur();
		cptvl.setTrans(true);
		cptvl.setCodePays(codePays1);
		cptvl.setAppUserCompte(userForm);
		Long numcptvl = dernierNumCompte + 2;
		cptvl.setPin(randomService.pinString(8));
		cptvl.setMonNum(numcptvl);
		cptvl.setNumCompte("00"+codePays+numcptvl);
		cptvl.setActive(true);
		compteRepository.save(cptvl);
		
		// les sous comptes de user
		SousCompte sousCompte1 = new SousCompte();
		Long numsousCompte1 = dernierNumCompte + 3;
		sousCompte1.setNumCompte("90"+codePays+numsousCompte1);
		sousCompte1.setAppUserSousCompte(userForm);
		sousCompte1.setType(TypeName.COMPTE_DE_PROVISIONS);
		sousCompte1.setPin(randomService.pin());
		sousCompte1.setActive(true);
		sousCompteDao.save(sousCompte1);
		
		SousCompte sousCompte2 = new SousCompte();
		Long numsousCompte2 = dernierNumCompte + 4;
		sousCompte2.setNumCompte("80"+codePays+numsousCompte2);
		sousCompte2.setAppUserSousCompte(userForm);
		sousCompte2.setType(TypeName.COMPTE_TRESOR);
		sousCompte2.setPin(randomService.pin());
		sousCompte2.setActive(true);
		sousCompteDao.save(sousCompte2);
		
		SousCompte sousCompte6 = new SousCompte();
		Long numsousCompte6 = dernierNumCompte + 5;
		sousCompte6.setNumCompte("70"+codePays+numsousCompte6);
		sousCompte6.setAppUserSousCompte(userForm);
		sousCompte6.setType(TypeName.COMPTE_ECHANGE_A_TERME);
		sousCompte6.setPin(randomService.pin());
		sousCompte6.setActive(true);
		sousCompteDao.save(sousCompte6);
		
		SousCompte sousCompte4 = new SousCompte();
		Long numsousCompte4 = dernierNumCompte + 6;
		sousCompte4.setNumCompte("60"+codePays+numsousCompte4);
		sousCompte4.setAppUserSousCompte(userForm);
		sousCompte4.setType(TypeName.AVANCE_SUR_ED);
		sousCompte4.setPin(randomService.pin());
		sousCompte4.setActive(true);
		sousCompteDao.save(sousCompte4);
		
		
		SousCompte sousCompte7 = new SousCompte();
		Long numsousCompte7 = dernierNumCompte + 7;
		sousCompte7.setNumCompte("50"+codePays+numsousCompte7);
		sousCompte7.setAppUserSousCompte(userForm);
		sousCompte7.setType(TypeName.COMPTE_D_IMPEYER_ET);
		sousCompte7.setPin(randomService.pin());
		sousCompte7.setActive(true);
		sousCompteDao.save(sousCompte7);
		
		SousCompte sousCompte8 = new SousCompte();
		Long numsousCompte8 = dernierNumCompte + 8;
		sousCompte8.setNumCompte("40"+codePays+numsousCompte8);
		sousCompte8.setAppUserSousCompte(userForm);
		sousCompte8.setType(TypeName.COMPTE_D_IMPEYER_AVANCE_ED);
		sousCompte8.setPin(randomService.pin());
		sousCompte8.setActive(true);
		sousCompteDao.save(sousCompte8);
		
		
		SousCompte sousCompte9 = new SousCompte();
		Long numsousCompte9 = dernierNumCompte + 9;
		sousCompte9.setNumCompte("89"+codePays+numsousCompte9);
		sousCompte9.setAppUserSousCompte(userForm);
		sousCompte9.setType(TypeName.AUTRE_TRESOR);
		sousCompte9.setPin(randomService.pin());
		sousCompte9.setActive(true);
		sousCompteDao.save(sousCompte9);
		
		
		SousCompte sousCompte5 = new SousCompte();
		Long numsousCompte5 = dernierNumCompte + 10;
		sousCompte5.setNumCompte("79"+codePays+numsousCompte5);
		sousCompte5.setAppUserSousCompte(userForm);
		sousCompte5.setType(TypeName.AUTRE_COMPTE_ET);
		sousCompte5.setPin(randomService.pin());
		sousCompte5.setActive(true);
		sousCompteDao.save(sousCompte5);

		
		
		
		SousCompte sousCompte3 = new SousCompte();
		Long numsousCompte3 = dernierNumCompte + 11;
		sousCompte3.setNumCompte("30"+codePays+numsousCompte3);
		sousCompte3.setAppUserSousCompte(userForm);
		sousCompte3.setType(TypeName.AUTRE_COMPTE_D_IMPEYER);
		sousCompte3.setPin(randomService.pin());
		sousCompte3.setActive(true);
		sousCompteDao.save(sousCompte3);			
				
		
		 
		/*String nom = userForm.getPrenom() +" "+userForm.getNom();
		String msg = "Votre numero de compte atouts est :\n"+ cptvl.getNumCompte()+". \n Connectez vous sur <a> www.atouts.com/connexion</a>";
		emailService.sendMailHtml(userForm.getEmail(), "ADHESION REUSSIE", msg, nom);*/
						

		return userForm;
	}
	
	
	@PostMapping("/register-admin")
	@Transactional
	public AppUser registerAdmin( @RequestParam("file") MultipartFile file, @RequestParam("file1") MultipartFile file1, @RequestParam("file2") MultipartFile file2, 
			@RequestParam("user") String user) throws JsonParseException, JsonMappingException, IOException {

		AppUser userForm = new ObjectMapper().readValue(user, AppUser.class);
		System.out.println(userForm);

		String fileName = file.getOriginalFilename();

		String modifiedFileName = FilenameUtils.getBaseName(fileName) + "_" +System.currentTimeMillis() + "." +FilenameUtils.getExtension(fileName);

		String fileName1 = file1.getOriginalFilename();

		String modifiedFileName1 = FilenameUtils.getBaseName(fileName1) + "_" +System.currentTimeMillis() + "." +FilenameUtils.getExtension(fileName1);
		
		String fileName2 = file2.getOriginalFilename();

		String modifiedFileName2 = FilenameUtils.getBaseName(fileName2) + "_" +System.currentTimeMillis() + "." +FilenameUtils.getExtension(fileName2);

		uploadFile.uploadFile(file, modifiedFileName);

		uploadFile.uploadFile(file1, modifiedFileName1);
		
		uploadFile.uploadFile(file2, modifiedFileName2);

		userForm.setPhoto(modifiedFileName);

		userForm.setScane(modifiedFileName1);
		
		userForm.setSignature(modifiedFileName2);
		

		userForm.setEnabled(true);
		userForm.setActive(true);
		userForm.setAdminstratif(true);

	    AppUser saveUser =	accountService.saveUser(userForm);
		//userRepository.saveAndFlush(userForm);
		System.out.println("Enregistrement avec Success !!!!");
		
		

		return saveUser;
	}
	
	
	
	@PostMapping("/register")
	@Transactional
	public AppUser register( @RequestParam("file") MultipartFile file, @RequestParam("file1") MultipartFile file1, @RequestParam("file2") MultipartFile file2, 
			@RequestParam("user") String user) throws JsonParseException, JsonMappingException, IOException {

		AppUser userForm = new ObjectMapper().readValue(user, AppUser.class);
		System.out.println(userForm);

		// Générer un jeton de chaîne aléatoire de 36 caractères pour le lien de confirmation
		
		userForm.setConfirmationToken(UUID.randomUUID().toString());

		String fileName = file.getOriginalFilename();

		String modifiedFileName = FilenameUtils.getBaseName(fileName) + "_" +System.currentTimeMillis() + "." +FilenameUtils.getExtension(fileName);

		String fileName1 = file1.getOriginalFilename();

		String modifiedFileName1 = FilenameUtils.getBaseName(fileName1) + "_" +System.currentTimeMillis() + "." +FilenameUtils.getExtension(fileName1);
		
		String fileName2 = file2.getOriginalFilename();

		String modifiedFileName2 = FilenameUtils.getBaseName(fileName2) + "_" +System.currentTimeMillis() + "." +FilenameUtils.getExtension(fileName2);

		uploadFile.uploadFile(file, modifiedFileName);

		uploadFile.uploadFile(file1, modifiedFileName1);
		
		uploadFile.uploadFile(file2, modifiedFileName2);

		userForm.setPhoto(modifiedFileName);

		userForm.setScane(modifiedFileName1);
		
		userForm.setSignature(modifiedFileName2);
		userForm.setDateExpiration(new Date(System.currentTimeMillis()+MesConstants.EXPIRATION_TIME_TOKEN));
		
		
		accountService.saveUser(userForm);
		System.out.println("Enregistrement avec Success !!!!");
		if(userForm.getRoles().isEmpty()) {
			accountService.addRoleToUse(userForm.getUsername(), "USER");
		}
		
		userRepository.saveAndFlush(userForm);
		
		
		
		
		
		  
		/*String appUrl = request.getScheme() +"://" + request.getServerName() + ":" +request.getServerPort();

		SimpleMailMessage registrationEmail = new SimpleMailMessage();

		registrationEmail.setTo(userForm.getEmail());

		registrationEmail.setSubject("Registration Confirmation");

		registrationEmail.setText("Pour confirmer votre adresse e-mail, veuillez cliquer sur le lien aatout ci-dessous:\n"
				+ appUrl + "/confirm?token=" + userForm.getConfirmationToken());
		// Adresse email de aatout
		registrationEmail.setFrom("aamadoudiko@gmail.com");
		



		emailService.sendEmail(registrationEmail);
		System.out.println("Un e-mail de confirmation a été envoyé à :" +userForm.getEmail() );*/
		//String appUrl = request.getScheme() +"://" + request.getServerName() + ":" +request.getServerPort();
		//String appUrl = "https://api.atouts.org";
//api.atouts.org
		String msg = "Pour confirmer votre inscription, veuillez cliquer sur le lien atouts ci-dessous:\n"+"https://"+request.getServerName() + "/confirm?token=" + userForm.getConfirmationToken();
		String nom = userForm.getPrenom() +" "+userForm.getNom();
		emailService.sendMailHtml(userForm.getEmail(), "CONFIRMATION", msg, nom);
		

		return userForm;


	}
	
	

	

	@DeleteMapping("/delete/{id}") 
	public boolean deleteUtilisateurs(@PathVariable Long  id) {
		try {
			AppUser appUser = userRepository.findById(id);
			appUser.setSupprime(true);
			appUser.setStatus(true);
			userRepository.saveAndFlush(appUser);
			return true;
		} catch (Exception e) {
			return false;
		}


	}
	
	@PostMapping("/bloquer-utilisateur/{id}") 
	public boolean bloquerUtilisateurs(@PathVariable Long  id, @RequestBody String raison) {
		System.out.println("ffff"+raison+"gffgfg");
		try {
			AppUser appUser = userRepository.findById(id);
			appUser.setAccountNonLocked(false);
			AppUser userBloquer = userRepository.saveAndFlush(appUser);
			Bloquer bloquage = new Bloquer();
			bloquage.setMotif(raison);
			bloquage.setAppUserBloquer(userBloquer);
			bloquerRepository.save(bloquage);
			return true;
		} catch (Exception e) {
			return false;
		}


	}
	
	@DeleteMapping("/debloquer-utilisateur/{id}") 
	public boolean debloquerUtilisateurs(@PathVariable Long  id) {
		try {
			AppUser appUser = userRepository.findById(id);
			appUser.setAccountNonLocked(true);
			userRepository.saveAndFlush(appUser);
			return true;
		} catch (Exception e) {
			return false;
		}


	}

	@GetMapping("/list-bloquages-by-Utilisateur/{id}")
	public List<Bloquer> getBloquageByAppUser(@PathVariable Long  id){
		return bloquerRepository.findByAppUserBloquer_Id(id);
	}



	@GetMapping("/listAppUsers")
	public List<AppUser> getAppUsers(){
		return userRepository.nomUtilisateurs();
	}
	@GetMapping("/listUtilisateurs/{id}")
	public AppUser getOneAppUser(@PathVariable Long id){
		return userRepository.listUserById(id);
	}



	@GetMapping("/confirm")
	public  ResponseEntity<String> confirmToken(@RequestParam("token") String token){
		AppUser appUser = accountService.findByConfirmationToken(token);
		if (appUser == null) {
			System.out.println("invalide token");
			return ResponseEntity.ok("invalide token");

		}else {
			System.out.println("valide token");
			Calendar cal = Calendar.getInstance();
			if ((appUser.getDateExpiration().getTime() - cal.getTime().getTime()) <= 0) {

				return ResponseEntity.ok("Votre token est expiré veillez reprendre. ecoute@atouts.org pour plus information");
			} else {
				// Set user to enabled
				appUser.setEnabled(true);
				userRepository.saveAndFlush(appUser);
				return ResponseEntity.ok("Vous avez correctement vérifié votre adresse e-mail. <a href=\"https://atouts.org/connexion\">Connexion</a>");

			}		

		}
	}

	
	/*@GetMapping(value="/confirm")
	    public ModelAndView confirmUserAccount(ModelAndView modelAndView, @RequestParam("token")String token)
	    {
	        //ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);
			AppUser appUser = accountService.findByConfirmationToken(token);
	        if(appUser != null)
	        {
	        	appUser.setEnabled(true);
				accountService.saveUser(appUser);
	            modelAndView.setViewName("accountVerified");
	        }
	        else
	        {
	            modelAndView.addObject("message","The link is invalid or broken!");
	            modelAndView.setViewName("error");
	        }

	        return modelAndView;
    }*/	
	
	
	/*@PostMapping("/confirm")
	public AppUser finalisationRegister(@RequestParam Map<String, String> requestParams ) {
		// Find the user associated with the reset token
		//AppUser appUser = accountService.findByConfirmationToken(requestParams.get("token"));
		AppUser appUser = accountService.findByConfirmationToken(requestParams.get("token"));
		Calendar cal = Calendar.getInstance();
		if ((appUser.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {

			System.err.println("Votre token est expiré veillez reprendre");
		} 
		// Set user to enabled
		appUser.setEnabled(true);
		accountService.saveUser(appUser);




		return appUser;
	}
*/
	
	@RequestMapping(value = "/export", method = RequestMethod.GET)
	public void export( HttpServletResponse response) throws IOException, JRException, SQLException {
		JasperPrint jasperPrint = null;

		response.setContentType("application/x-download");
		//response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", String.format("attachment; filename=\"users.pdf\""));

		OutputStream out = response.getOutputStream();
		jasperPrint = accountServiceImpl.exportPdfFile();
		JasperExportManager.exportReportToPdfStream(jasperPrint, out);
	}
	
	@GetMapping("/me/{username}")
    public AppUser getCurrentUser(@PathVariable String username) {
        AppUser meUser = userRepository.findByUsername(username);
        if(meUser.isEnabled() == false || meUser.isSupprime()== true) {
        	return null;
        }
        return  userRepository.findByUsername(username);
    }
	
	
	/* @RequestMapping(value = "/supplierpdf/{supplierId}", method = RequestMethod.GET)
		public ModelAndView ReorderPdftBySupplier(@PathVariable("supplierId") int id) {
		    JasperReportsPdfView view = new JasperReportsPdfView();
		    view.setUrl("classpath:ReorderReportPerSupplier.jrxml");
		    view.setApplicationContext(appContext);
		    JRBeanCollectionDataSource jrds = new JRBeanCollectionDataSource(SomeService.getSomeCollectionList());
		    return new ModelAndView(view, "productData", jrds);
		}*/

	
    @GetMapping(value = "/loggedUsers")
    public String getLoggedUsers(Locale locale, org.springframework.ui.Model model) {
        model.addAttribute("users", activeUserStore.getUsers());
        return "users";
    }
    
    
    /*@PostMapping("/login")
    public LoginRequest authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {      
        
        return loginRequest;
    }*/

    
    /* Les Méthode Statistique */
    @GetMapping(value = "/total-user")
    public List<AppUser> nombreUser() {
    	return userRepository.findAll();
    }
    @GetMapping(value = "/total-user-confirme")
    public List<AppUser> nombreUserConfirme() {
    	return userRepository.findByEnabledIsTrue();
    }
    @GetMapping(value = "/total-user-non-confirme")
    public List<AppUser> nombreUserNonConfirme() {
    	return userRepository.findByEnabledIsFalse();
    }
    
    
    
    @PostMapping("/urgent")
	@Transactional
	public AppUser urgent(@RequestBody AppUser userForm ) {
    	
		userForm.setDateExpiration(new Date(System.currentTimeMillis()+MesConstants.EXPIRATION_TIME_TOKEN));
		
		userForm.setConfirmationToken(UUID.randomUUID().toString());
		accountService.saveUser(userForm);
		
		System.out.println("Enregistrement avec Success !!!!");
		if(userForm.getRoles().isEmpty()) {
			accountService.addRoleToUse(userForm.getUsername(), "USER");
		}
		userForm.setEnabled(true);
		userRepository.saveAndFlush(userForm);  
		
		String msg = "Pour confirmer votre inscription, veuillez cliquer sur le lien atouts ci-dessous:\n"+"https://"+request.getServerName() + "/confirm?token=" + userForm.getConfirmationToken();
		String nom = userForm.getPrenom() +" "+userForm.getNom();
		emailService.sendMailHtml(userForm.getEmail(), "CONFIRMATION", msg, nom);	
		
		return userForm;
	}	 
}
