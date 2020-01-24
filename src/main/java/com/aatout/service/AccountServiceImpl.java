package com.aatout.service;

import com.aatout.dao.RoleRepository;    
import com.aatout.dao.UserRepository;
import com.aatout.model.AppRole;
import com.aatout.model.AppUser;
import com.aatout.service.AccountService;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AccountServiceImpl implements AccountService   {
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	 @Qualifier("jdbcTemplate")
	 private JdbcTemplate jdbcTemplate;

	 @Autowired
	 private ResourceLoader resourceLoader;
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public AppUser saveUser(AppUser user) {
		//System.out.println(user.getUsername()+""+user.getPassword());
		String hashPW = bCryptPasswordEncoder.encode(user.getPassword());
		user.setPassword(hashPW);
		System.out.println(user.getUsername()+ " :  " +user.getPassword());
		return userRepository.saveAndFlush(user);
	}

	@Override
	public AppRole saveRole(AppRole role) {
		// TODO Auto-generated method stub
		return roleRepository.save(role);
	}

	@Override
	public void addRoleToUse(String username, String roleName) {
		AppRole role = roleRepository.findByRoleName(roleName);
		AppUser user = userRepository.findByUsername(username);
		user.getRoles().add(role);
		
	}

	@Override
	public AppUser findUserByUsername(String username) {
		// TODO Auto-generated method stub
		return userRepository.findByUsername(username);
	}


	@Override
	public AppUser findByConfirmationToken(String confirmationToken) {
		return userRepository.findByConfirmationToken(confirmationToken);
	}

	@Override
	public AppUser findById(Long id) {
		return userRepository.findById(id);
               
	}

	/*@Override
	public void addRoleToUse(String username, AppRole appRole) {
		AppRole role=roleRepository.findAppRole(appRole);
		AppUser user=userRepository.findByUsername(username);
		user.getRoles().add(role);		
		
	}*/
	

	 public JasperPrint exportPdfFile() throws SQLException, JRException, IOException {
	  Connection conn = jdbcTemplate.getDataSource().getConnection();

	  String path = resourceLoader.getResource("classpath:folder/rpt_users.jrxml").getURI().getPath();

	  JasperReport jasperReport = JasperCompileManager.compileReport(path);

	  // Parameters for report
	  Map<String, Object> parameters = new HashMap<String, Object>();

	  JasperPrint print = JasperFillManager.fillReport(jasperReport, parameters, conn);

	  return print;
	 }
	 public JasperPrint exportPdfFiles() throws SQLException, JRException, IOException {
		  return exportPdfFile();
		 }
}
