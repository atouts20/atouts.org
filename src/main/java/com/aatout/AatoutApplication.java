package com.aatout;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.aatout.dao.PaysDao;
import com.aatout.model.AppUser;
import com.aatout.model.Pays;
import com.aatout.operation.OperationService;
import com.aatout.parametre.model.ActiveUserStore;
import com.aatout.property.FileStorageProperties;
import com.aatout.service.AccountService;
import com.aatout.web.StorageService;


@SpringBootApplication
@EnableJpaAuditing
@EnableConfigurationProperties({
	FileStorageProperties.class
})
public class AatoutApplication extends SpringBootServletInitializer implements CommandLineRunner{

	@Resource
	StorageService storageService;   
	
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private PaysDao paysDao;
	
	@Autowired
	private OperationService operationService;
	
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(AatoutApplication.class);
    }
	
	
	public static void main(String[] args) {
		SpringApplication.run(AatoutApplication.class, args);
	}
	@Bean
	public ActiveUserStore activeUserStore(){
	    return new ActiveUserStore();
	}
	@Bean
	public BCryptPasswordEncoder getBCPE() {
		return new BCryptPasswordEncoder();
	}
	
	 @Bean
	    public PasswordEncoder BCryptPasswordEncoder() {
	        return new BCryptPasswordEncoder();
	    }

	@Override
	public void run(String... args) throws Exception {
		/*String uuid = UUID.randomUUID().toString();
		int t=uuid.length();
		String chaine = uuid.substring(32,t);
		System.out.println("********");
		System.out.println(chaine.toUpperCase());
		System.out.println("********");*/
		
		//accountService.saveUser(new AppUser("admin", "poisson21", "AMADOU DIKO", "Moucharafou", "BEROUBOUAY", "Borgou", "adimouch21@gmail.com"));
		//accountService.addRoleToUse("admin", "ADMIN");
		//accountService.addRoleToUse("admin", "USER");
		//accountService.saveRole(new AppRole(null, "USER"));
		//accountService.saveRole(new AppRole(null, "ADMIN"));
		/*accountService.saveUser(new AppUser("admin", "1234", "admin", "Moucharafou", dt.parse("12/04/2015"), "parakou", "adimoduch214400@gmail.com", "Developpeur", "+22969928707", "masculin", true,false, "azerty",dt.parse("12/04/2015"), null));
		accountService.saveUser(new AppUser("user", "1234", "user", "Moucharafou", dt.parse("12/04/2015"), "parakou", "adimouch2144@gmail.com", "Developpeur", "+22966998177", "masculin", true,false, "azerty",dt.parse("12/04/2015"), null));
		accountService.saveUser(new AppUser("user1", "1234", "admin", "Moucharafou", dt.parse("12/04/2015"), "parakou", "adimoduch2144@gmail.com", "Developpeur", "+22669952877", "femme", false,true, "azerty",dt.parse("12/04/2015"), null));
		accountService.saveUser(new AppUser("user2", "1234", "goo", "Moucharafou", dt.parse("12/04/2015"), "parakou", "adimoduch2144@1gmail.com", "Developpeur", "+226699286770", "femme", false,true, "azerty",dt.parse("12/04/2015"), null));
		accountService.saveUser(new AppUser("user3", "1234", "goo", "Moucharafou", dt.parse("12/04/2015"), "parakou", "adimoduch2144@10gmail.com", "Developpeur", "+226699278771", "femme", true,true, "azerty",dt.parse("12/04/2015"), null));
		*/
		//accountService.findAll().forEach(c->{System.out.println(c);});
		/*
		accountService.saveUser(new AppUser("adimouch21", "1234", "AMADOU DIKO", "Moucharafou", dt.parse("11/11/2001"), "+2045897", null));
		accountService.saveUser(new AppUser("admin", "1234", "amadou diko", "Moucharafou", dt.parse("12/04/2014"), "cotonou", "Litoral", "adimouch21gmail.com", "Informatique", "+229975478240", "+22854754896", "+321458975", "beroubouay", "physique", "Beninoise", "CNI", "12345698754", dt.parse("14/08/2018"), dt.parse("12/02/2019"), "AMADOU DIKO Adam", "Lafia Bio", "+29942586714", "+229875492", "Celibataire", "TOLODE", 2, "Gogo", "Jaja", "Tuteur", "Masculin", null, true));
		accountService.saveUser(new AppUser("user", "1234", "amadou diko", "Moucharafou", dt.parse("12/04/2014"), "cotonou", "Litoral", "adimouch21gmail1.com", "Informatique", "+229975478241", "+22854754896", "+321458975", "beroubouay", "physique", "Beninoise", "CNI", "12345698754", dt.parse("14/08/2018"), dt.parse("12/02/2019"), "AMADOU DIKO Adam", "Lafia Bio", "+29942586714", "+229875492", "Celibataire", "TOLODE", 2, "Gogo", "Jaja", "Tuteur", "Masculin", null, false));
		accountService.saveUser(new AppUser("autorisateur", "1234", "amadou diko", "Moucharafou", dt.parse("12/04/2014"), "cotonou", "Litoral", "adimouch21gmail2.com", "Informatique", "+229975478245", "+22854754896", "+321458975", "beroubouay", "physique", "Beninoise", "CNI", "12345698754", dt.parse("14/08/2018"), dt.parse("12/02/2019"), "AMADOU DIKO Adam", "Lafia Bio", "+29942586714", "+229875492", "Celibataire", "TOLODE", 2, "Gogo", "Jaja", "Tuteur", "Masculin", null, true));
		
		accountService.saveRole(new AppRole(null,"ADMIN"));
		accountService.saveRole(new AppRole(null,"USER"));
		accountService.saveRole(new AppRole(null,"AUTORISATEUR"));
		
		accountService.addRoleToUse("admin", "ADMIN");
		accountService.addRoleToUse("admin", "USER");
		accountService.addRoleToUse("user", "USER"); 
		accountService.addRoleToUse("user", "ADMIN");*/
		
		//accountService.addRoleToUse("user", "ADMIN");
		
		//accountService.saveUser(new AppUser("adimouch21", "1234", "AMADOU DIKO", "Moucharafou", dt.parse("11/11/2001"), "+2045897", null));
		
			//storageService.deleteAll();
			//storageService.init();
		/*RandomString gen = new RandomString(8, ThreadLocalRandom.current());
		
		System.out.println(" ====== "+gen);
		
		RandomString session = new RandomString();
		System.out.println(" ====== securisé "+session);
		
		String easy = RandomString.digits + "ACEFGHJKLMNPQRUVWXYabcdefhijkprstuvwx";
		RandomString tickets = new RandomString(23, new SecureRandom(), easy);
		System.out.println(" ====== securisé "+toString());
		
		SecureRandom sec = new SecureRandom();
		System.out.println(sec);
		System.out.println("**********************");
		sec.toString();
		System.out.println("**********************");
		Random rcd = new Random();
		int valeurMin = 10000;
		int valeurMax = 99999999;
		int valeur = valeurMin + rcd.nextInt(valeurMax - valeurMin);

		System.out.println(rcd.nextInt(valeur));
		
		int n = (int)(Math.random() * valeur);
		System.out.println(" _____________: "+n);
		UUID uuid = UUID.randomUUID();
		String s = Long.toString(uuid.getMostSignificantBits(), 36) + Long.toString(uuid.getLeastSignificantBits(), 36);
		System.out.println("=====String s=======+++++: "+s);
		
		Date date = new Date();
		System.out.println("======Date date = new Date()=======+++++: "+date.toInstant().getNano());
	
		
		 
		 long time = date.getTime();
		     System.out.println("Time in Milliseconds: " + time);
		 
		 Timestamp ts = new Timestamp(time);
		 System.out.println("Current Time Stamp: " + ts);
		 
		 RandomString y = new RandomString(100);
		 
		 System.out.println("RandomString y = new RandomString(): " +y);
	*/
		
		/*AppUser unUser = new AppUser();
		unUser.setUsername("admin");
		unUser.setPassword("poisson21");
		unUser.setEmail("adimouch21@gmail.com");
		unUser.setPhoneNumber("+22996094442");
		unUser.setNationalite(paysDao.findBySortOrderIs("1") );
		
		accountService.saveUser(unUser);
		
		accountService.addRoleToUse("admin", "ADMIN");
		accountService.addRoleToUse("admin", "USER");*/
		
	}
}
